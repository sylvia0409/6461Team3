/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package part2;

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
    private String IR = "0"; //Instruction Register, store string format of the binary number
    private int MAR = 0;    //Memory Address Register
    private String MBR = "";//Memory Buffer Register
    private String MSR = "0";//Machine Status Register
    private int MFR = 0;    //Machine Fault Register, contains Fault ID
    private int IAR = 0;    //Internal Address Register
    private String IRR = "0";//Internal Result Register

    public int opcode, registerSelect, IX, I, address;
    public int clock;
    //create 4 general registers
    String[] R = new String[4];

    //create 3 index registers
    int[] X = new int[3];

    //create Condition Code Register. every item stands for 1 bit
    //OVERFLOW, UNDERFLOW, DIVZERO, EQUALORNOT
    int[] CC = new int[4];

    //create ALU
    ALU alu = new ALU();

    //create Register Stack
    String[] registerStack = new String[4];

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
        if(R[registerSelect] != null){
            registerStack[registerSelect] = R[registerSelect];
            clock++;
        }
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
        //restore the register with registerStack
        R[registerSelect] = registerStack[registerSelect];
        clock++;
    }

    public void LDA(Memory mainMemory, int registerSelect, int IX, int I, int address){
        //compute the effective address which need to be stored in register
        IAR = computeEffectiveAddress(mainMemory, address, IX, I);
        //transfer the int to string in order to store in R
        if(R[registerSelect] != null){
            registerStack[registerSelect] = R[registerSelect];
            clock++;
        }
        R[registerSelect] = Integer.toBinaryString(IAR);
        clock++;
    }

    public void LDX(Memory mainMemory, int IX, int I, int address){
        //Load Index Register from Memory
        IAR = computeEffectiveAddress(mainMemory, address, IX, I);
        MAR = IAR;
        clock++;
        MBR = mainMemory.getValue(MAR);
        clock++;
        if(Long.parseLong(MBR,2) > 4096){
            System.err.println("value return from memory > 4096, cannot load to index registers");
        } else {
            IRR = MBR;
            clock++;
            X[IX-1] = Integer.valueOf(String.valueOf(Long.parseLong(IRR,2)));
            clock++;
        }
    }

    public void STX(Memory mainMemory, int IX, int I, int address){
        //Store Index Register to Memory
        //transfer decimal number to string format of binary number
        IRR = Integer.toBinaryString(X[IX-1]);
        clock++;
        MBR = IRR;
        clock++;
        IAR = computeEffectiveAddress(mainMemory, address, IX, I);
        MAR = IAR;
        clock++;
        mainMemory.setValue(MAR, MBR);
        clock++;
    }

    public void JZ(Memory mainMemory, int registerSelect, int IX, int I, int address){
        //Jump If Zero: If c(r) = 0, then PC <− EA
        if(R[registerSelect].equals("0")){
            IAR = computeEffectiveAddress(mainMemory, address, IX, I);
            MAR = IAR;
            clock++;
            PC = MAR - 1;
            clock++;
        }
    }

    public void JNE(Memory mainMemory, int registerSelect, int IX, int I, int address){
        //Jump If Not Equal: If c(r) != 0, then PC <−- EA
        if(!R[registerSelect].equals("0")){
            IAR = computeEffectiveAddress(mainMemory, address, IX, I);
            MAR = IAR;
            clock++;
            PC = MAR - 1;
            clock++;
        }
    }

    public void JCC(Memory mainMemory, int registerSelect, int IX, int I, int address){
        //Jump If Condition Code specific bit == 1
        if(CC[registerSelect] == 1){
            IAR = computeEffectiveAddress(mainMemory, address, IX, I);
            MAR = IAR;
            clock++;
            PC = MAR - 1;
            clock++;
        }
    }

    public void JMA(Memory mainMemory, int IX, int I, int address){
        //Unconditional Jump To Address
        IAR = computeEffectiveAddress(mainMemory, address, IX, I);
        MAR = IAR;
        clock++;
        PC = MAR - 1;
        clock++;
    }

    public void JSR(Memory mainMemory,  int IX, int I, int address){
        //Jump and Save Return Address, we specify R3 as return address storage, so we
        //omit the input registerSelect
        MAR = PC;
        clock++;
        IAR = MAR;
        clock++;
        if(R[3] != null){
            registerStack[3] = R[3];
            clock++;
        }
        R[3] = String.valueOf(IAR);
        clock++;
        IAR = computeEffectiveAddress(mainMemory, address, IX, I);
        MAR = IAR;
        clock++;
        PC = MAR - 1;
        clock++;
    }

    public void RFS(int address){
        //Return code From Subroutine, we specify R0 as the Immediate portion storage, so we
        //omit the registerSelect
        IRR = String.valueOf(address);
        if(R[0] != null){
            registerStack[0] = R[0];
            clock++;
        }
        R[0] = IRR;
        clock++;
        IAR = Integer.parseInt(String.valueOf(Long.valueOf(R[3])),2);
        //restore Register3 with the content of stack3
        R[3] = registerStack[3];
        clock++;
        clock++;
        PC = IAR;
        clock++;
    }

    public void SOB(Memory mainMemory, int registerSelect, int IX, int I, int address){
        //Subtract One and Branch, it's used for loop times, so the value is nonnegative number
        int value = Integer.valueOf(String.valueOf(Long.parseLong(R[registerSelect],2)));
        R[registerSelect] = Integer.toBinaryString(value - 1);
        if(value - 1 > 0){
            IAR = computeEffectiveAddress(mainMemory, address, IX, I);
            MAR = IAR;
            clock++;
            PC = MAR - 1;
            clock++;
        }
    }

    public void JGE(Memory mainMemory, int registerSelect, int IX, int I, int address){
        //we consider the value in the register here as the signed number, so we catch
        //the first digit to judge if it is negative
        String[] digit = R[registerSelect].split("");
        if(digit[0].equals("0")){
            IAR = computeEffectiveAddress(mainMemory, address, IX, I);
            MAR = IAR;
            clock++;
            PC = MAR - 1;
            clock++;
        }
    }

    public void AMR(Memory mainMemory, int registerSelect, int IX, int I, int address){
        //Add Memory To Register
        IAR = computeEffectiveAddress(mainMemory, address, IX, I);
        MAR = IAR;
        clock++;
        MBR = mainMemory.getValue(MAR);
        clock++;
        IRR = MBR;
        clock++;
        long value1 = transferToDecimal(R[registerSelect]);
        long value2 = transferToDecimal(IRR);
        long result = alu.add(value1, value2);
        clock++;
        //overflow or underflow, set conditional code
        if(value1 > 0 && value2 > 0 && result < 0){
            CC[0] = 1;
            System.out.println("add overflow");
        }
        if(value1 < 0 && value2 < 0 && result > 0){
            CC[1] = 1;
            System.out.println("add underflow");
        }
        R[registerSelect] = Long.toBinaryString(result);
    }

    public void SMR(Memory mainMemory, int registerSelect, int IX, int I, int address){
        //Subtract Memory From Register
        IAR = computeEffectiveAddress(mainMemory, address, IX, I);
        MAR = IAR;
        clock++;
        MBR = mainMemory.getValue(MAR);
        clock++;
        IRR = MBR;
        clock++;
        long value1 = transferToDecimal(R[registerSelect]);
        long value2 = transferToDecimal(IRR);
        long result = alu.subtract(value1, value2);
        clock++;
        if(value1 > 0 && value2 < 0 && result < 0){
            CC[0] = 1;
            System.out.println("subtract overflow");
        }
        if(value1 < 0 && value2 > 0 && result > 0){
            CC[1] = 1;
            System.out.println("subtract underflow");
        }
        R[registerSelect] = String.valueOf(Long.toBinaryString(result));
    }

    public void AIR(int registerSelect, int address){
        //Add  Immediate to Register
        if(address != 0){
            long value = transferToDecimal(R[registerSelect]);
            long result = alu.add(value, address);
            clock++;
            if(value > 0 && address > 0 && result < 0){
                CC[0] = 1;
                System.out.println("add overflow");
            }
            if(value < 0 && address < 0 && result > 0){
                CC[1] = 1;
                System.out.println("add underflow");
            }
            R[registerSelect] = Long.toBinaryString(result);
        }
    }

    public void SIR(int registerSelect, int address){
        //Subtract  Immediate  from Register
        if(address != 0){
            long value = transferToDecimal(R[registerSelect]);
            long result = alu.subtract(value, address);
            clock++;
            if(value > 0 && address < 0 && result < 0){
                CC[0] = 1;
                System.out.println("subtract overflow");
            }
            if(value < 0 && address > 0 && result > 0){
                CC[1] = 1;
                System.out.println("subtract underflow");
            }
            R[registerSelect] = Long.toBinaryString(result);
        }
    }

    public void MLT(int x, int y){
        //Multiply Register by Register
        if(x != 0 || x != 2 || y != 0 || y != 2){
            System.err.println("when multiply, index of register must be 0 or 2");
        }
        long value1 = transferToDecimal(R[x]);
        long value2 = transferToDecimal(R[y]);
        long result = alu.multiply(value1, value2);
        clock++;
        String number = Long.toBinaryString(result);
        if(number.length() > 16){
            CC[0] = 1;
            String firstPart = number.substring(number.length()-16, number.length());
            String secondPart = number.substring(0,number.length()-16);
            R[x] = firstPart;
            clock++;
            R[x+1] = secondPart;
            clock++;
        } else {
            R[y] = number;
            clock++;
            R[x] = "0";
            clock++;
        }

    }

    public void DVD(int x, int y){
        //Divide Register by Register
        if(x != 0 || x != 2 || y != 0 || y != 2){
            System.err.println("when divide, Rx must be 0 or 2");
        }
        long value1 = transferToDecimal(R[x]);
        long value2 = transferToDecimal(R[y]);
        if(value2 == 0){
            CC[2] = 1;
            System.err.println("cannot divide zero");
        }
        long result[] = alu.divide(value1, value2);
        clock++;
        R[x] = Long.toBinaryString(result[0]);
        clock++;
        R[x+1] = Long.toBinaryString(result[1]);
        clock++;

    }

    public void TRR(int x, int y){
        if(R[x].equals(R[y])){
            CC[3] = 1;
        } else {
            CC[3] = 0;
        }
    }

    public void AND(int x, int y){
        //Logical And of Register and Register
        String result = alu.AND(R[x], R[y]);
        clock++;
        R[x] = result;
        clock++;
    }

    public void ORR(int x, int y){
        //Logical Or of Register and Register
        String result = alu.ORR(R[x], R[y]);
        clock++;
        R[x] = result;
        clock++;
    }

    public void NOT(int x){
        //Logical Not of Register To Register
        String result = alu.NOT(R[x]);
        clock++;
        R[x] = result;
        clock++;
    }

    public void SRC(int registerSelect, int AL, int LR, int count){
        //Shift Register by Count
        if(count == 0){
            return;
        }
        String value = R[registerSelect];
        String result = "";
        if(AL == 1 && LR == 1){
            result = alu.logicalLeftShift(value, count);
            clock++;
        } else if(AL == 1 && LR == 0){
            result = alu.logicalRightShift(value, count);
            clock++;
        } else if(AL == 0 && LR == 0){
            result = alu.arithmeticRightShift(value, count);
            clock++;
        } else  if(AL == 0 && LR == 1){
            result = alu.arithmeticLeftShift(value, count);
            clock++;
        }
        R[registerSelect] = result;
        clock++;
    }

    public void RRC(int registerSelect, int LR, int count){
        //Rotate Register by Count
        String value = R[registerSelect];
        String result = "";
        if(LR == 0){
            result = alu.rotateRight(value, count);
            clock++;
        } else {
            result = alu.rotateLeft(value, count);
            clock++;
        }
        R[registerSelect] = result;
    }

    public void IN(int registerSelect, int devid){
        if(R[registerSelect] != null){
            registerStack[registerSelect] = R[registerSelect];
            clock++;
        }
        if(devid == 0){
            //todo: store Console Keyboard value to register
            clock++;
        } else if (devid == 1){
            //todo: store Console Printer value to register
            clock++;
        } else if (devid == 2){
            //todo: store Card Reader value to register
            clock++;
        } else {
            //todo: store Console Registers, switches, etc to register
            clock++;
        }

    }

    public void OUT(int registerSelect, int devid){
        if(devid == 0){
            //todo:  output to Console Keyboard value
            clock++;
        } else if (devid == 1){
            //todo: output to Console Printer value
            clock++;
        } else if (devid == 2){
            //todo: output to Card Reader value
            clock++;
        } else {
            //todo: output to Console Registers, switches, etc
            clock++;
        }
        R[registerSelect] = registerStack[registerSelect];
        clock++;
    }



    private long transferToDecimal(String number){
        String[] digits = number.split("");
        long result = 0;
        if(digits[0].equals("1")){
            result = -(2^15);
        }
        for (int i = 1; i < digits.length; i++) {
            result = result + Integer.valueOf(digits[i]) * 2^(15-i);
        }
        return result;
    }

    public int computeEffectiveAddress(Memory mainMemory, int address, int IX, int I){
        //move the operand address from the IR to IAR
        IAR = address;
        clock++;
        //no indirect addressing:
        //If the operand is indexed, add the contents of the specified index register to the IAR
        if(IX != 0 && I == 0){
            IAR = IAR + X[IX-1];
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
            if(MAR > 4096){
                System.err.println("memory address > 4096 when compute indirect address");
            } else {
                MBR = mainMemory.getValue(MAR);
                clock++;
            }
            if(Long.parseLong(MBR, 2) > 4096){
                System.err.println("the value from memory > 4096, cannot use as address");
            } else {
                IAR = Integer.valueOf(String.valueOf(Long.parseLong(MBR, 2)));
                clock++;
            }
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
        String result = String.valueOf(CC[0])+CC[1]+String.valueOf(CC[2])+CC[3];
        return result;
    }

}
