/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpu;

import memory.Memory;

/**
 *
 * @author yiqian
 */
public class CPU {
    /**
    When we fetch the value from memory, the data type which need to store values
    from memory are set to string type, the data type which related with memory
    location or address are set to int.
    The int implies its value is decimal number, the string implies binary number
     */
    private int PC = 0;     //program counter
    private int cc=0; //condition code
    private int IR = 0; //Instruction Register, store string format of the binary number
    private int MAR = 0;    //Memory Address Register
    private String MBR = "";//Memory Buffer Register
    private String MSR = "0";//Machine Status Register
    private int MFR = 0;    //Machine Fault Register, contains Fault ID
    private int IAR = 0;    //Internal Address Register
    private String IRR = "0";//Internal Result Register
    private int FR0=0;
    private int FR1=0;
    Memory mainMemoryStore;

    public int opcode, registerSelect, IX, I, address;
    public int clock;
    //create 4 general registers
    int[] R = new int[4];

    //create 3 index registers
    int[] X = new int[3];

    
    //inital the value of reg
    public void setFR0(int num){
        this.FR0=num;
    }
    
    public void setFR1(int num){
        this.FR1=num;
    }
    
    public void setPC(int PCin){
        PC = PCin;
    }
    
    public void setMAR(int MARin){
        this.MAR = MARin;
    }
    
    public void setMBR(String MBRin){
        this.MBR = MBRin;
    }
    
    public void setMBR(int MBRin){
        this.MBR = String.valueOf(MBRin);
    }
    
    public void setMFR(int MFRin){
        this.MFR = MFRin;
    }
    
    public void setIR(int IRin){
        this.IR = IRin;
    }
    
    public int getPC(){
        return this.PC;
    }

    public int getMAR(){
        return this.MAR;
    }    
    
    public String getMBR(){
        return (MBR);
    }
    
    public int getIntMBR(){
        return Integer.parseInt(MBR);
    }
    
    public String getBinaryStringOfIR(){
        if (this.IR <= 0xffff) {
            return String.format("%16s", Integer.toBinaryString(this.IR)).replace(" ", "0");
        }
        return null;       
    }
    
    public int getBitLengthByName(String name){
        if (name.equals("CC"))
            return 4;
        if (name.equals("R0"))
            return 16;
        if (name.equals("R1"))
            return 16;
        if (name.equals("R2"))
            return 16;
        if (name.equals("R3"))
            return 16;
        if (name.equals("IR"))
            return 16;
        if (name.equals("MAR"))
            return 16;
        if (name.equals("MBR"))
            return 16;
        if (name.equals("MFR"))
            return 4;
        if (name.equals("MSR"))
            return 16;
        if (name.equals("PC"))
            return 12;
        if (name.equals("X1"))
            return 16;
        if (name.equals("X2"))
            return 16;
        if (name.equals("X3"))
            return 16;
        if (name.equals("FR0"))
            return 16;
        if (name.equals("FR1"))
            return 16;
        return 0;       
    }
    
    public int getRegByName(String name){
        if (name.equals("CC"))
            return this.cc;
        if (name.equals("R0"))
            return this.R[0];
        if (name.equals("R1"))
            return this.R[1];
        if (name.equals("R2"))
            return this.R[2];
        if (name.equals("R3"))
            return this.R[3];
        if (name.equals("IR"))
            return this.IR;
        if (name.equals("MAR"))
            return this.MAR;
        if (name.equals("MBR"))
            return Integer.parseInt(this.MBR);
        if (name.equals("MFR"))
            return this.MFR;
        if (name.equals("MSR"))
            return Integer.parseInt(this.MSR);
        if (name.equals("PC"))
            return this.PC;
        if (name.equals("X1"))
            return this.X[0];
        if (name.equals("X2"))
            return this.X[1];
        if (name.equals("X3"))
            return this.X[2];
        if (name.equals("FR0"))
            return this.FR0;
        if (name.equals("FR1"))
            return this.FR1;
        return 0;
    }
    
    public int getIR(){
        return this.IR;
    }
    
    public int getMFR(){
        return this.MFR;
    }
        
    String exp="0000000";
    String man="00000000";
    String output=null;
    public void setConvertFRByNum(int num, int fr) {
    	String input=null;
    	
    	if(num==0){
    		if(fr>=0){
    			input = Integer.toBinaryString(fr);
    			man=input+man.substring(input.length());
    			String temp=Integer.toBinaryString(input.length());
    			exp=exp.substring(0, 7-temp.length())+temp;
    			output="0"+exp+man;
        	
    		}else{
        	fr=-1*fr;
        	input=Integer.toBinaryString(fr);
        	
        	char[] opp=input.toCharArray();
        	int k;
        	for(int i=0; i<input.length();i++){
        		
        		if (opp[i]=='0'){
        			opp[i]='1';
        		}else{
        			opp[i]='0';
        		}
        		
        	}
        	for(k=input.length()-1;k>=0;k--){
        	if(opp[k]=='0'){
        		opp[k]='1';
        		break;
        	}else {
        		opp[k]='0';
        		continue;
        	}
        	}	
        	String valid=new String(opp);	
        	man=valid+man.substring(input.length());
        	
        	String temp=Integer.toBinaryString(input.length());
        	exp=exp.substring(0, 7-temp.length())+temp;
        	output="1"+exp+man;
        }
        	this.FR0 = Integer.parseInt(output,2);
        }
        if (num == 1){
        	if(fr>=0){
                input = Integer.toBinaryString(fr);
            	man=input+man.substring(input.length());
            	String temp=Integer.toBinaryString(input.length());
            	exp=exp.substring(0, 7-temp.length())+temp;
            	output="0"+exp+man;
            	
            }else{
            	fr=-1*fr;
            	input=Integer.toBinaryString(fr);
            	
            	char[] opp=input.toCharArray();
            	int k;
            	for(int i=0; i<input.length();i++){
            		
            		if (opp[i]=='0'){
            			opp[i]='1';
            		}else{
            			opp[i]='0';
            		}
            		
            	}
            	for(k=input.length()-1;k>=0;k--){
            	if(opp[k]=='0'){
            		opp[k]='1';
            		break;
            	}else {
            		opp[k]='0';
            		continue;
            	}
            	}	
            	String valid=new String(opp);	
            	man=valid+man.substring(input.length());
            	
            	String temp=Integer.toBinaryString(input.length());
            	exp=exp.substring(0, 7-temp.length())+temp;
            	output="1"+exp+man;
            }
            this.FR1 = Integer.parseInt(output,2);
        }
    }
    
    public int getFRByNum(int num){
        if(num==0){
            return this.FR0;
        }
        if(num==1){
            return this.FR1;
        }
        return 0;
    }
    
    public void setFRByNum(int num, int value){
        if(num==0){
            this.FR0=value;
        }
        if(num==1){
            this.FR1=value;
        }
    }
    
    public void setCCElementByBit(int bitNum, boolean flag) {
        if (flag) {
            this.cc = (this.cc | (1 << bitNum));
        } else {
            int mask = ~(1 << bitNum);
            this.cc = this.cc & mask;
        }
    }
    
    public boolean getCCElementByBit(int bitNum) {
        return ((this.cc & (1 << bitNum)) != 0);
    }
    
    public int getCC(){
        return this.cc;
    }
    
    public void setRnByNum(int num, int value){
        if (num == 0)
            this.R[0]=value;
        if (num == 1)
            this.R[1]=value;
        if (num == 2)
            this.R[2]=value;
        if (num == 3)
            this.R[3]=value;
    }
    
    public int getRnByNum(int num){
        if (num == 0)
            return this.R[0];
        if (num == 1)
            return this.R[1];
        if (num == 2)
            return this.R[2];
        if (num == 3)
            return this.R[3];
        return 0;    
    }
    
    public int getXnByNum(int num) {
        if (num == 1)
            return this.X[0];
        if (num == 2)
            return this.X[1];
        if (num == 3)
            return this.X[2];
        return 0;
    }
    
    public void setXnByNum(int num, int value){
        if (num == 1)
            this.X[0]=value;
        if (num == 2)
            this.X[1]=value;
        if (num == 3)
            this.X[2]=value;       
    }
    
    public void increasePC(){
        this.PC++;
    }
}
