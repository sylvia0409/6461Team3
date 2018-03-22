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
public class FSUB extends Abstractinstruction {

	int fr;
	int ix;
	int address;
	int i;

	@Override
	public void execute(String instruction, CPU cpu, MCU mcu) throws MachineFaultException {
		// -----------------------------------
		// 034: FSUB -> Floating Subtract Memory from Register
		// c(FR) <- c(FR) - c(EA)
		// c(FR) <- c(FR) - c(c(EA)), if l bit set
		// -----------------------------------
		fr = StringUtil.binaryToDecimal(instruction.substring(6, 8));
		ix = StringUtil.binaryToDecimal(instruction.substring(8, 10));
		i = StringUtil.binaryToDecimal(instruction.substring(10, 11));
		address = StringUtil.binaryToDecimal(instruction.substring(11, 16));

		int effectiveAddress = EffectiveAddress.computeEffectiveAddress(ix, address, i, mcu, cpu);

		// first, we store the effective address in memory address register
		cpu.setMAR(effectiveAddress);

		// storing what we fetched from memory into the memory buffer
		// register
		cpu.setMBR(mcu.fetchFromCache(cpu.getMAR()));

		int result = cpu.getFRByNum(fr) - cpu.getIntMBR();

		// we check if we have an overflow
		int MAX_VALUE = 2^6;
		int MIN_VALUE = -2^6-1;
		if (result > MAX_VALUE && result < MIN_VALUE) {
			cpu.setCCElementByBit(Const.ConditionCode.OVERFLOW.getValue(), true);
		} else {
			// if we do not have an overflow, we update the value of
			// register
			cpu.setFRByNum(fr, result);
		}

		cpu.increasePC();
	}

	@Override
	public String getExecuteMessage() {
		// TODO Auto-generated method stub
		return "FSUB " + fr + ", " + ix + ", " + address + ", " + i;
	}

}

