/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pipeline;

import java.util.concurrent.SynchronousQueue;
import cpu.CPU;
import instruction.Abstractinstruction;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import memory.MCU;
import util.Const;
import util.MachineFaultException;

/**
 *
 * @author yiqian
 */
public class pipeline_control {
    
    private int PCstart;
    private int PCend;
    private CPU cpu;
    private MCU mcu;
    
    // pipeline thread
    private Thread IF_thread;
    private Thread ID_thread;
    private Thread EX_thread;
    private Thread MEM_thread;
    private Thread WB_thread;
    
    private Queue<Integer> PC;
    private Queue<String> Instruction;
    private Queue<Abstractinstruction> Order;
    private Queue<String> Instruction2;
    private Queue<Integer> Address1;
    private Queue<Integer> Address2;
    private Queue<Integer> Content;
    
    
    
    public pipeline_control(int PCstart, int PCend, CPU cpu, MCU mcu ){
        this.PC = new LinkedList<Integer>();
        this.Instruction = new LinkedList<String>();
        this.Order = new LinkedList<Abstractinstruction>();
        this.Instruction2 = new LinkedList<String>();
        this.Address1 = new LinkedList<Integer>();
        this.Address2 = new LinkedList<Integer>();
        this.Content = new LinkedList<Integer>();
        this.PCstart = PCstart;
        this.PCend = PCend;
        this.cpu = cpu;
        this.mcu = mcu;
        
        PC.offer(PCstart);
        
        run();
        
    }
    
    private void run(){
        
        IF_thread.start();
        ID_thread.start();
        EX_thread.start();
        MEM_thread.start();
        WB_thread.start();
        
        while((!Content.isEmpty()) || (!PC.isEmpty()) || (!Instruction.isEmpty()) || (!Order.isEmpty()) || (!Instruction2.isEmpty()) || Address1.size()!=0 || !Address2.isEmpty()){
            
            try {
                IF_thread.join();
                ID_thread.join();
                EX_thread.join();
                MEM_thread.join();
                WB_thread.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(pipeline_control.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    

    
    // Fetch task
    private class Fetch implements Runnable{

        @Override
        public void run() {
        
            int PC_temp;
            System.out.println("Thread Id: " + Thread.currentThread().getId() + " --- fetch start");
            try{
                if(VisitIF_ID(0)){
                    PC_temp = PC.remove();
                    cpu.setPC(PC_temp);
                    cpu.setMAR(cpu.getPC());
                    cpu.setMBR(mcu.fetchFromCache(cpu.getMAR()));
                    cpu.setIR(cpu.getIntMBR());
                    Instruction.add(cpu.getBinaryStringOfIR());
                    System.out.println("Thread Id: " + Thread.currentThread().getId() + " --- get instruction from (PC): " + PC_temp);                    
                }else{
                    Thread.sleep(200);
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Thread Id: " + Thread.currentThread().getId() + " --- fetch end");
        }
    
    }
    
    private class Decode implements Runnable{

        @Override
        public void run() {
            
            String instruction_temp;
            Abstractinstruction instr;
            System.out.println("Thread Id: " + Thread.currentThread().getId() + " --- decode start");
            try{
                if(VisitID_EX(0)){
                    instruction_temp = Instruction.remove();
                    String opCode = instruction_temp.substring(0, 6);
                    try {
                        if (Const.OPCODE.containsKey(opCode)) {
                            instr = (Abstractinstruction) Class
                                    .forName("instruction." + Const.OPCODE.get(opCode)).newInstance();
                            Order.add(instr);
                            Instruction2.add(instruction_temp);
                            System.out.println("Thread Id: " + Thread.currentThread().getId() + " --- decode instruction: " + instruction_temp);                             

                        } else {
                            // we don't have that kind of instruction
                            throw new MachineFaultException(Const.FaultCode.ILL_OPRC.getValue(),
                                    Const.FaultCode.ILL_OPRC.getMessage());
                        }
                    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (MachineFaultException t) {
                        // handle the machine fault

                        t.printStackTrace();
                        //handleMachineFault(t.getFaultCode(), t.getMessage());
                    }                   
                }else{
                    Thread.sleep(200);
                }
            }catch(InterruptedException e){
                e.printStackTrace();               
            }
            System.out.println("Thread Id: " + Thread.currentThread().getId() + " --- decode end");            

        }
        
    }
    
    private class Execute implements Runnable{

        @Override
        public void run() {
            Abstractinstruction instr;
            String instruction;
            System.out.println("Thread Id: " + Thread.currentThread().getId() + " --- execute start");
            try{
                if(VisitEX(0)){
                    instr = Order.remove();
                    instruction = Instruction2.remove();
                    try {
                        instr.execute(instruction, cpu, mcu);
                        System.out.println("Thread Id: " + Thread.currentThread().getId() + " --- execute instruction: " + instruction); 
                    } catch (MachineFaultException ex) {
                        Logger.getLogger(pipeline_control.class.getName()).log(Level.SEVERE, null, ex);
                    }
                                                    
                }else{
                    Thread.sleep(200);
                }
            }catch(InterruptedException e){
                e.printStackTrace();               
            }
            System.out.println("Thread Id: " + Thread.currentThread().getId() + " --- execute end");  
        }
        
    }
    
    private class MEM implements Runnable{

        @Override
        public void run() {
            
            int Address_temp1;
            int Address_temp2;
            
            if(VisitMEM(0)){
                Address_temp1 = Address1.remove();
                Address_temp2 = mcu.fetchFromMemory(Address_temp1);
                Address2.add(Address_temp2);
            }else{
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(pipeline_control.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
    private class WB implements Runnable{

        @Override
        public void run() {
            
            int Address_temp;
            int Content_temp;
            
            if(VisitWB(0)){
                Address_temp = Address2.remove();
                Content_temp = Content.remove();
                mcu.storeInMemory(Address_temp, Content_temp);
                
                
            }else{
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(pipeline_control.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    
    }
 
    private synchronized boolean VisitIF_ID(int sign){
        if(sign==0){
            if(PC.size()==0){
                return false;
            } else{
                return true;
            }
        }else{
            return true;
        }
    }
        
    private synchronized boolean VisitID_EX(int sign){
        if(sign==0){
            if(Instruction.size()==0){
                return false;
            } else{
                return true;
            }
        }else{
            return true;
        }        
    }
    
    private synchronized boolean VisitEX(int sign){
        if(sign==0){
            if(Order.size()==0 || Instruction2.size()==0){
                return false;
            } else{
                return true;
            }
        }else{
            return true;
        }        
    }    
    
    private synchronized boolean VisitMEM(int sign){
        if(sign ==0){
            if(Address1.size()==0){
                return false;
            }else{
                return true;
            }
        }else{
            return true;
        }
    }
    
    private synchronized boolean VisitWB(int sign){
        if(sign==0){
            if(Address2.size()==0 || Content.size()==0){
                return false;
            }else{
                return true;
            }
        }else{
            return true;
        }
        
    }
}
