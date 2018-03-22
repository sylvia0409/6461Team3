/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instruction;

import memory.MCU;
import util.StringUtil;
import cpu.CPU;
import util.EffectiveAddress;
import util.MachineFaultException;

/**
 *
 * @author yiqian
 */
public class LDX extends Abstractinstruction {
	int r;
	int ix;
	int address;
	int i;

	@Override
	public void execute(String instruction, CPU cpu, MCU mcu) throws MachineFaultException {
		// -----------------------------------
		// 41: LDX -> Load Index Register from Memory
		// -----------------------------------
		r = StringUtil.binaryToDecimal(instruction.substring(6, 8));
		ix = StringUtil.binaryToDecimal(instruction.substring(8, 10));
		i = StringUtil.binaryToDecimal(instruction.substring(10, 11));
		address = StringUtil.binaryToDecimal(instruction.substring(11, 16));

		int effectiveAddress = EffectiveAddress.computeEffectiveAddress(ix, address, i, mcu, cpu);

		// first, we read the content of selected Index Register using [IX]
		cpu.setMAR(effectiveAddress);
		cpu.setMBR(mcu.fetchFromCache(cpu.getMAR()));
		cpu.setXnByNum(ix, cpu.getIntMBR());

		cpu.increasePC();
	}

	@Override
	public String getExecuteMessage() {
		// TODO Auto-generated method stub
		return "LDX " + r + ", " + ix + ", " + address + ", " + i;
	}    
}
