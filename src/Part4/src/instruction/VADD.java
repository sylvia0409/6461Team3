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
public class VADD extends Abstractinstruction {

	int fr;
	int ix;
	int i;
	int address_V1;
	int address_V2;

	@Override
	public void execute(String instruction, CPU cpu, MCU mcu) throws MachineFaultException {
		// -----------------------------------
		// 035: VADD -> Vector ADD
		// V1(i) <- V1(i) + V2(i)
		// -----------------------------------
		fr = StringUtil.binaryToDecimal(instruction.substring(6, 8));
		ix = StringUtil.binaryToDecimal(instruction.substring(8, 10));
		address_V1 = StringUtil.binaryToDecimal(instruction.substring(11, 16));
		address_V2 = address_V1 + 1;
		i = StringUtil.binaryToDecimal(instruction.substring(10, 11));
		int effectiveAddress_V1 = EffectiveAddress.computeEffectiveAddress(ix, address_V1, i, mcu, cpu);
		int effectiveAddress_V2 = EffectiveAddress.computeEffectiveAddress(ix, address_V2, i, mcu, cpu);
		fr = 3;
		for (int i = 0; i < fr; i++) {// first, we store the effective address
										// in memory address register
			cpu.setMAR(effectiveAddress_V1 + i);
			// storing what we fetched from memory into the memory buffer
			// register
			cpu.setMBR(mcu.fetchFromCache(cpu.getMAR()));
			int v1 = cpu.getIntMBR();
			// we get the first vector from EA_V1
			cpu.setMAR(effectiveAddress_V2 + i);
			cpu.setMBR(mcu.fetchFromCache(cpu.getMAR()));
			int v2 = cpu.getIntMBR();
			// we get the second vector from EA+1
			int result = v1 + v2;
			// we check if we have an overflow
			int MAX_VALUE = 2 ^ 6;
			int MIN_VALUE = -2 ^ 6 - 1;
			if (result < MAX_VALUE && result > MIN_VALUE) {
				cpu.setCCElementByBit(Const.ConditionCode.OVERFLOW.getValue(), true);
			} else {
				// if we do not have an overflow, we update the value of
				// register
				cpu.setMBR(result);
				cpu.setMAR(effectiveAddress_V1 + i);
				mcu.storeIntoCache(cpu.getMAR(), result);
			}
		}
		cpu.increasePC();
	}

	@Override
	public String getExecuteMessage() {
		// TODO Auto-generated method stub
		return "VADD " + fr + ", " + ix + ", " + address_V1 + ", " + address_V2 + ", " + i;
	}

}
