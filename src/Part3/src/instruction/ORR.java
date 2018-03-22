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
public class ORR extends Abstractinstruction {

	int rx;
	int ry;

	@Override
	public void execute(String instruction, CPU cpu, MCU mcu) throws MachineFaultException {
		// -----------------------------------
		// 024: ORR -> Logical Or of Register and Register
		// c(rx) <- c(rx) OR c(ry)
		// -----------------------------------
		// TODO Auto-generated method stub
		this.rx = StringUtil.binaryToDecimal(instruction.substring(6, 8));
		this.ry = StringUtil.binaryToDecimal(instruction.substring(8, 10));
		int x = cpu.getRnByNum(rx);
		int y = cpu.getRnByNum(ry);
		x = x | y;
		cpu.setRnByNum(rx, x);

		cpu.increasePC();
	}

	@Override
	public String getExecuteMessage() {
		// TODO Auto-generated method stub
		return "OR " + rx + ", " + ry;
	}

}
