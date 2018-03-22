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
public class CNVRT extends Abstractinstruction {

	int r;
	int ix;
	int address;
	int i;
	int F;

	@Override
	public void execute(String instruction, CPU cpu, MCU mcu) throws MachineFaultException {
		// TODO Auto-generated method stub
		r = StringUtil.binaryToDecimal(instruction.substring(6, 8));
		ix = StringUtil.binaryToDecimal(instruction.substring(9, 11));
		i = StringUtil.binaryToDecimal(instruction.substring(8, 9));
		address = StringUtil.binaryToDecimal(instruction.substring(11, 16));

		int effectiveAddress = EffectiveAddress.computeEffectiveAddress(ix, address, i, mcu, cpu);
		F = cpu.getRnByNum(r);
		cpu.setMAR(effectiveAddress);
		cpu.setMBR(mcu.fetchFromCache(cpu.getMAR()));

		if (F == 0) {

			cpu.setRnByNum(r, Math.round(cpu.getIntMBR()));
			
		}
		if (F == 1) {

			cpu.setConvertFRByNum(0, cpu.getIntMBR());
			
		}
		cpu.increasePC();
	}

	@Override
	public String getExecuteMessage() {
		// TODO Auto-generated method stub
		return "CNVRT" + r + ", " + ix + ", " + address + "," + i;
	}

}
