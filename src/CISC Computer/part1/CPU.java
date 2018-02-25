/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package part1;

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
    private String CC = "0"; //condition code
    private String IR = "0"; //Instruction Register, store string format of the binary number
    private int MAR = 0;    //Memory Address Register
    private String MBR = "";//Memory Buffer Register
    private String MSR = "0";//Machine Status Register
    private int MFR = 0;    //Machine Fault Register, contains Fault ID
    private int IAR = 0;    //Internal Address Register
    private String IRR = "0";//Internal Result Register
    Memory mainMemoryStore;

    public int opcode, registerSelect, IX, I, address;
    public int clock;
    //create 4 general registers
    String[] R = new String[4];

    //create 3 index registers
    int[] X = new int[3];
    
    

    public void LDR(Memory mainMemory, int registerSelect, int IX, int I, int address){
        // use the address in instruction to compute the Effective Address
        IAR = computeEffectiveAddress(mainMemory, address, IX, I);
        //move the contents of the IAR to the MAR
        MAR = IAR;
        clock++;
        //fetch the contents of the word in memory specified by the MAR into the MBR.
        MBR = mainMemory.getValue(MAR);
        clock++;
        //move the data from the MBR to an Internal Result Register (IRR)
        IRR = MBR;
        clock++;
        //use Register Select to store IRR contents into the specified register
        R[registerSelect] = IRR;
        clock++;
    }

    public void STR(Memory mainMemory, int registerSelect, int IX, int I, int address){
        //Move the contents of the specified register using Register Select to the IRR.
        IRR = R[registerSelect];
        clock++;
        //move contents of IRR to MBR.
        MBR = IRR;
        clock++;
        //compute the Effective Address
        IAR = computeEffectiveAddress(mainMemory, address, IX, I);
        MAR = IAR;
        clock++;
        //On the next cycle, move contents of MBR to memory using address in MAR.
        mainMemory.setValue(MAR, MBR);
        clock++;
    }

    public void LDA(Memory mainMemory, int registerSelect, int IX, int I, int address){
        //compute the effective address which need to be stored in register
        IAR = computeEffectiveAddress(mainMemory, address, IX, I);
        //transfer the int to string in order to store in R
        R[registerSelect] = String.valueOf(Integer.toBinaryString(IAR));
        clock++;
    }

    public void LDX(Memory mainMemory, int IX, int I, int address){
        IAR = computeEffectiveAddress(mainMemory, address, IX, I);
        MAR = IAR;
        clock++;
        MBR = mainMemory.getValue(MAR);
        clock++;
        IRR = MBR;
        clock++;
        X[IX-1] = Integer.parseInt(String.valueOf(Long.valueOf(IRR)),2);
        clock++;
    }

    public void STX(Memory mainMemory, int IX, int I, int address){
        //transfer decimal number to string format of binary number
        MBR = String.valueOf(Integer.toBinaryString(X[IX-1]));
        clock++;
        IAR = computeEffectiveAddress(mainMemory, address, IX, I);
        MAR = IAR;
        clock++;
        mainMemory.setValue(MAR, MBR);
        clock++;
    }

    public int computeEffectiveAddress(Memory mainMemory, int address,int IX, int I){
        //move the operand address from the IR to IAR
        IAR = address;
        clock++;
        //no indirect addressing:
        //If the operand is indexed, add the contents of the specified index register to the IAR
        if(IX != 0 && I == 0){
            IAR = IAR + X[IX-1];//IX begin with 1, X array begin with 0,  so we need minus 1
            clock++;
        }
        //indirect addressing:
        if(I == 1){
            if(IX != 0 ){
                IAR = IAR + X[IX-1];
                clock++;
            }
            MAR = IAR;
            clock++;
            MBR = mainMemory.getValue(MAR);
            clock++;
            IAR = Integer.parseInt(String.valueOf(Long.valueOf(MBR)),2);
            clock++;
        }
        return IAR;
    }
    
    
    //inital the value of reg
    public void setIndexRegister(int registerSelect, int content){
        X[registerSelect] = content;
    }
    
    public void setPC(int PCin){
        PC = PCin;
    }
    
    public void setGeneralRegister(int index, String content){
        R[index] = content;
    }
    
    public void setMAR(int MARin){
        this.MAR = MARin;
    }
    
    public void setMBR(String MBRin){
        this.MBR = MBRin;
    }
    
    public void setMFR(int MFRin){
        this.MFR = MFRin;
    }
    
    public void setIR(String IRin){
        this.IR = IRin;
    }
    
    public int getPC(){
        return this.PC;
    }
    
    public String getR0(){
        return this.R[0];
    }
    
    public String getR1(){
        return this.R[1];
    }
    
    public String getR2(){
        return this.R[2];
    }
    
    public String getR3(){
        return this.R[3];
    }
    
    public int getX1(){
        return this.X[0];
    }
    
    public int getX2(){
        return this.X[1];
    }

    public int getX3(){
        return this.X[2];
    }

    public int getMAR(){
        return this.MAR;
    }    
    
    public String getMBR(){
        System.out.println(MBR);
        return MBR;
    }
    
    public String getIR(){
        System.out.println(IR);
        return this.IR;
    }
    
    public int getMFR(){
        return this.MFR;
    }
    
    public String getCC(){
        return this.CC;
    }

}
