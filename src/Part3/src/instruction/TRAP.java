/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instruction;

import cpu.CPU;
import memory.MCU;
import util.Const;
import util.EffectiveAddress;
import util.MachineFaultException;
import util.StringUtil;

/**
 *
 * @author yiqian
 */
public class TRAP extends Abstractinstruction {
    int trapCode;

    @Override
    public void execute(String instruction, CPU cpu, MCU mcu) throws MachineFaultException {
        // TODO Auto-generated method stub
        // System.out.println("This is a TRAP instruction!");
        trapCode = StringUtil.binaryToDecimal(instruction.substring(12, 16));
        if(trapCode > 15 || trapCode < 0){
            throw new MachineFaultException(Const.FaultCode.ILL_TRPC.getValue(), Const.FaultCode.ILL_TRPC.getMessage());
        }
        // store pc+1 into memory 2
        cpu.setMAR(2);
        cpu.setMBR(cpu.getPC() + 1);
        mcu.storeIntoCache(cpu.getMAR(), cpu.getIntMBR());
        // goes to the routine whose address is in memory location 0
        cpu.setMAR(0);
        cpu.setMBR(mcu.fetchFromCache(cpu.getMAR()));
        int tableAddress = cpu.getIntMBR();

        cpu.setMAR(trapCode + tableAddress);
        cpu.setMBR(mcu.fetchFromCache(cpu.getMAR()));
        int routine = cpu.getIntMBR();
        cpu.setPC(routine);
        try {
            do {
                cpu.setMAR(cpu.getPC());
                cpu.setMBR(mcu.fetchFromCache(cpu.getMAR()));
                cpu.setIR(cpu.getIntMBR());
                String ins = StringUtil.decimalToBinary(cpu.getIR(), 16);
                String opCode = ins.substring(0, 6);
                if (Const.OPCODE.containsKey(opCode)) {
                    Abstractinstruction instr = (Abstractinstruction) Class
                            .forName("alu.instruction." + Const.OPCODE.get(opCode)).newInstance();
                    instr.execute(ins, cpu, mcu);
                }
            } while (cpu.getIR() != 0);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // return to the instruction
        cpu.setMAR(2);
        cpu.setMBR(mcu.fetchFromCache(cpu.getMAR()));
        cpu.setPC(cpu.getIntMBR());
    }

    @Override
    public String getExecuteMessage() {
        // TODO Auto-generated method stub
        return "TRAP " + trapCode;
    }    
}
