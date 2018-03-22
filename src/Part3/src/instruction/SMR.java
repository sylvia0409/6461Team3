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
public class SMR extends Abstractinstruction {

	int r;
	int ix;
	int address;
	int i;

	@Override
	public void execute(String instruction, CPU cpu, MCU mcu) throws MachineFaultException {
		// -----------------------------------
		// 005: SMR -> Subtract Memory From Register
		// r <- c(r) - c(EA)
		// -----------------------------------
		r = StringUtil.binaryToDecimal(instruction.substring(6, 8));
		ix = StringUtil.binaryToDecimal(instruction.substring(8, 10));
		i = StringUtil.binaryToDecimal(instruction.substring(10, 11));
		address = StringUtil.binaryToDecimal(instruction.substring(11, 16));

		int effectiveAddress = EffectiveAddress.computeEffectiveAddress(ix, address, i, mcu, cpu);

		// first, we store the effective address in memory address register
		cpu.setMAR(effectiveAddress);

		// storing what we fetched from memory into the memory buffer
		// register
		cpu.setMBR(mcu.fetchFromCache(cpu.getMAR()));

		double result = cpu.getRnByNum(r) - cpu.getIntMBR();

		// we check if we have an overflow
		if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
			cpu.setCCElementByBit(Const.ConditionCode.OVERFLOW.getValue(), true);
		} else {
			// if we do not have an overflow, we update the value of
			// register
			cpu.setRnByNum(r, (int) result);
		}

		cpu.increasePC();
	}

	@Override
	public String getExecuteMessage() {
		// TODO Auto-generated method stub
		return "SMR " + r + ", " + ix + ", " + address + ", " + i;
	}

}
