/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.HashMap;

/**
 *
 * @author yiqian
 */
public class Const {
    
    /**
     *    Const in system
     */
    public static final Integer CACHE_LINES = 16;
    public static final Integer PG1_BASE_1 = 300;
    public static final Integer PG1_END_1 = 370;
    public static final Integer PG1_BASE_2 = 500;
    public static final Integer PG1_END_2 = 871;
    public static final Integer PG1_BASE_3 = 1800;
    public static final Integer PG1_END_3= 1862;
    
    public static final Integer PG2_BASE1= 1600;
    public static final Integer PG2_END1= 1624;
    public static final Integer PG2_BASE2= 1700;
    public static final Integer PG2_END2= 1724;    
    public static final Integer PG2_BASE3= 900;
    public static final Integer PG2_END3= 989;
    public static final Integer PG2_BASE4= 1000;
    public static final Integer PG2_END4= 1062;
    public static final Integer PG2_BASE5= 1100;
    public static final Integer PG2_END5= 1162;
    
    public static final Integer Memory_Bound = 2048;
    public static final Integer Memory_Expand_Bound = 4096;
    
    public static final Integer PC_BASE=8;
    
    public enum Fault {
	ILL_MEM_RSV(0, "Illegal Memory Address to Reserved Locations"), ILL_TRPC(1, "Illegal TRAP code"), ILL_OPRC(2,
	"Illegal Operation Code"), ILL_MEM_BYD(3, "Illegal Memory Address beyond 2048 (memory installed)");
	int value;
	String messsage;

	private Fault(int value, String message) {
		this.value = value;
		this.messsage = message;
	}

	public int getValue() {
            return this.value;
	}

	public String getMessage() {
            return this.messsage;
	}
    }
    
    public enum ConditionCode {
	OVERFLOW(0), UNDERFLOW(1), DIVZERO(2), EQUALORNOT(3);
	int value;

	private ConditionCode(int value) {
            this.value = value;
	}

	public int getValue() {
            return this.value;
	}
    }
    
    public enum DevId {
	KEYBOARD(0), PRINTER(1), CARD(2);
	int value;

	private DevId(int value) {
	this.value = value;
	}

	public int getValue() {
            return this.value;
	}	
    }
    
    public static final HashMap<String, String> OPCODE = new HashMap<String, String>();
	static {
                OPCODE.put("000000", "HLT");
		OPCODE.put("011110", "TRAP");
		OPCODE.put("000001", "LDR");
		OPCODE.put("000010", "STR");
		OPCODE.put("000011", "LDA");
		OPCODE.put("101001", "LDX");
		OPCODE.put("101010", "STX");
		OPCODE.put("111101", "IN");
		OPCODE.put("111110", "OUT");
		OPCODE.put("001010", "JZ");
		OPCODE.put("001011", "JNE");
		OPCODE.put("001100", "JCC");
		OPCODE.put("001101", "JMA");
		OPCODE.put("001110", "JSR");
		OPCODE.put("001111", "RFS");
		OPCODE.put("010000", "SOB");
		OPCODE.put("010001", "JGE");
		OPCODE.put("011111", "SRC");
		OPCODE.put("100000", "RRC");
		OPCODE.put("000100", "AMR");
		OPCODE.put("000101", "SMR");
		OPCODE.put("010111", "AND");
		OPCODE.put("011000", "ORR");
		OPCODE.put("011001", "NOT");
		OPCODE.put("000110", "AIR");
		OPCODE.put("000111", "SIR");
		OPCODE.put("010100", "MLT");
		OPCODE.put("010101", "DVD");
		OPCODE.put("010110", "TRR");
		OPCODE.put("111111", "CHK");
		OPCODE.put("100001", "FADD");
		OPCODE.put("100010", "FSUB");
		OPCODE.put("100011", "VADD");
		OPCODE.put("100100", "VSUB");
		OPCODE.put("100101", "CNVRT");
		OPCODE.put("110010", "LDFR");
		OPCODE.put("110011", "STFR");
	}
        
    public static final HashMap<String, String> BASE_PROGRAM = new HashMap<String, String>();
    static{
        
    }
    
    /**
     * Machine Fault</br>
     * 0 - ILL_MEM_RSV: Illegal Memory Address to Reserved Locations</br>
     * 1 - ILL_TRPC: Illegal TRAP code</br>
     * 2 - ILL_OPRC: Illegal Operation Code</br>
     * 3 - ILL_MEM_BYD: Illegal Memory Address beyond 2048 (memory installed)
     */
    public enum FaultCode {
        ILL_MEM_RSV(0, "Illegal Memory Address to Reserved Locations"), ILL_TRPC(1, "Illegal TRAP code"), ILL_OPRC(2,
			"Illegal Operation Code"), ILL_MEM_BYD(3, "Illegal Memory Address beyond 2048 (memory installed)");
                        
        int value;
	String messsage;

        private FaultCode(int value, String message) {
            this.value = value;
            this.messsage = message;
	}

        public int getValue() {
            return this.value;
        }

	public String getMessage() {
            return this.messsage;
	}
    }
}
