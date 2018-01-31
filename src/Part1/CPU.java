package Part1;

public class CPU {
    /**
    When we fetch the value from memory, the data type which need to store values
    from memory are set to string type, the data type which related with memory
    location or address are set to int.
    The int implies its value is decimal number, the string implies binary number
     */
    int PC = 0;     //program counter
    String CC = ""; //condition code
    String IR = ""; //Instruction Register, store string format of the binary number
    int MAR = 0;    //Memory Address Register
    String MBR = "";//Memory Buffer Register
    String MSR = "";//Machine Status Register
    int MFR = 0;    //Machine Fault Register, contains Fault ID
    int IAR = 0;    //Internal Address Register
    String IRR = "";//Internal Result Register
    int clock = 0;

    //create 4 general registers
    String[] R = new String[4];

    //create 3 index registers
    int[] X = new int[3];

    public void execute(Memory mainMemory, int memoryLocation){
        //initialize PC
        PC = memoryLocation;
        if(mainMemory.getValue(PC) == null){
            return;
        }
        //put location from PC to MAR ,it needs 1 clock
        MAR = PC;
        clock++;
        //MCU uses the address in the MAR to fetch a word from memory and place it in MBR
        MBR = mainMemory.getValue(MAR);
        clock++;
        //The contents of MBR are moved to the IR. This takes 1 cycle.
        IR = MBR;
        clock++;
        //extract the opcode, R(registerSelect), IX, I, address from the IR.
        //we transfer long to int for saving space of memory,
        //instructions below show the transfer process: string of binary number -> long -> int
        int opcode = Integer.parseInt(String.valueOf(Long.valueOf(IR)/100000/100000),2);
        int registerSelect = Integer.parseInt(String.valueOf(Long.valueOf(IR)/100000000%100),2);
        int IX = Integer.parseInt(String.valueOf(Long.valueOf(IR)/1000000%100),2);
        int I = Integer.parseInt(String.valueOf(Long.valueOf(IR)/100000%10));
        int address = Integer.parseInt(String.valueOf(Long.valueOf(IR)%100000),2);
        clock++;
        //determine the class of opcode
        switch (opcode){
            case 1: LDR(mainMemory, registerSelect, IX, I, address);
            break;
            case 2: STR(mainMemory, registerSelect, IX, I, address);
            break;
            case 3: LDA(mainMemory, registerSelect, IX, I, address);
            break;
            case 41: LDX(mainMemory, IX, I, address);
            break;
            case 42: STX(mainMemory, IX, I, address);
            break;

        }
        PC++;
        execute(mainMemory, PC);
    }

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
    public void setIndexRegister(int registerSelect, int content){
        X[registerSelect] = content;
    }


}
