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
public class NOT extends Abstractinstruction {

	int rx;

	@Override
	public void execute(String instruction, CPU cpu, MCU mcu) throws MachineFaultException {
		// -----------------------------------
		// 025: NOT -> Logical Not of Register To Register
		// C(rx) <- NOT c(rx)
		// -----------------------------------
		// TODO Auto-generated method stub
		this.rx = StringUtil.binaryToDecimal(instruction.substring(6, 8));
		int Bd = cpu.getRnByNum(rx);

		Bd = ~Bd;
		cpu.setRnByNum(rx, Bd);

		cpu.increasePC();
	}

	@Override
	public String getExecuteMessage() {
		// TODO Auto-generated method stub
		return "NOT " + rx;
	}

}
