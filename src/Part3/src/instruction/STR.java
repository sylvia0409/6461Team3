/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instruction;

import memory.MCU;
import util.StringUtil;
import util.EffectiveAddress;
import util.MachineFaultException;
import cpu.CPU;

/**
 *
 * @author yiqian
 */
public class STR extends Abstractinstruction {
	int r;
	int ix;
	int address;
	int i;

	@Override
	public void execute(String instruction, CPU cpu, MCU mcu) throws MachineFaultException {
		// -----------------------------------
		// 02:STR -> Store Register to Memory
		// -----------------------------------
		r = StringUtil.binaryToDecimal(instruction.substring(6, 8));
		ix = StringUtil.binaryToDecimal(instruction.substring(8, 10));
		i = StringUtil.binaryToDecimal(instruction.substring(10, 11));
		address = StringUtil.binaryToDecimal(instruction.substring(11, 16));

		int effectiveAddress = EffectiveAddress.computeEffectiveAddress(ix, address, i, mcu, cpu);

		// reading the content of selected register using [R] in the
		// instruction
		cpu.setMAR(effectiveAddress);
		cpu.setMBR(cpu.getRnByNum(r));
                
                mcu.storeIntoCache(cpu.getMAR(), cpu.getIntMBR());

		cpu.increasePC();
	}

	@Override
	public String getExecuteMessage() {
		// TODO Auto-generated method stub
		return "STR " + r + ", " + ix + ", " + address + ", " + i;
	}    
}
