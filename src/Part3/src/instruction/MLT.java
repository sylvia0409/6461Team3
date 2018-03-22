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
public class MLT extends Abstractinstruction {

	int rx;
	int ry;

	@Override
	public void execute(String instruction, CPU cpu, MCU mcu) throws MachineFaultException {
		// ------------------------------------------------------
		// 020: MLT -> Multiply Register by Register
		// rx, rx+1 <- c(rx) * c(ry)
		// rx must be 0 or 2
		// ry must be 0 or 2
		// rx contains the high order bits, rx+1 contains the low order bits of
		// the result
		// Set OVERFLOW flag, if overflow
		// ------------------------------------------------------

		rx = StringUtil.binaryToDecimal(instruction.substring(6, 8));
		ry = StringUtil.binaryToDecimal(instruction.substring(8, 10));

		// first we should check the below condition:
		// rx must be 0 or 2
		// AND
		// ry must be 0 or 2
		if ((rx == 0 || rx == 2) && (ry == 0 || ry == 2)) {

			// doing the multiplication
			double result = cpu.getRnByNum(rx) * cpu.getRnByNum(ry);

			// we check if we have an overflow
			if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) { // TODO
																			// not
																			// sure
				cpu.setCCElementByBit(Const.ConditionCode.OVERFLOW.getValue(), true);
			} else {
				// rx contains the high order bits of the result
				cpu.setRnByNum(rx, getHighOrderBits((int) result));

				// rx+1 contains the low order bits of the result
				cpu.setRnByNum(rx + 1, getLowOrderBits((int) result));
			}
		}

		cpu.increasePC();

	}

	@Override
	public String getExecuteMessage() {
		return "MLT " + rx + ", " + ry;
	}

	// getting the low 16 bits of an integer
	private int getLowOrderBits(int x) {
		return (x & 0xFFFF);
	}

	// getting the high 16 bits of an integer
	private int getHighOrderBits(int x) {
		return x >> 16;
	}

}
