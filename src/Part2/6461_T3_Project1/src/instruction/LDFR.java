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
public class LDFR extends Abstractinstruction{

	int fr;
	int ix;
	int address;
	int i;

	@Override
	public void execute(String instruction, CPU cpu, MCU mcu) throws MachineFaultException {
		// TODO Auto-generated method stub
		fr = StringUtil.binaryToDecimal(instruction.substring(6, 8));
		ix = StringUtil.binaryToDecimal(instruction.substring(9, 11));
		i = StringUtil.binaryToDecimal(instruction.substring(8, 9));
		address = StringUtil.binaryToDecimal(instruction.substring(11, 16));
		int effectiveAddress = EffectiveAddress.computeEffectiveAddress(ix, address, i, mcu, cpu);
		
		String exp="0000000";
                String man="00000000";
	    
		cpu.setMAR(effectiveAddress);
		cpu.setMBR(mcu.fetchFromCache(cpu.getMAR()));
		int expI=cpu.getIntMBR();
		cpu.setMAR(effectiveAddress+1);
		cpu.setMBR(mcu.fetchFromCache(cpu.getMAR()));
		int manI=cpu.getIntMBR();

		

		String temp=Integer.toString(expI);
		exp=exp.substring(0,7-temp.length())+temp;
		String temp1=Integer.toString(manI);
		man=temp1+man.substring(temp1.length());

		

		
		String frs=exp+man;
		
		cpu.setFRByNum(fr, Integer.parseInt(frs,2));
		
		cpu.increasePC();
	}

	@Override
	public String getExecuteMessage() {
		// TODO Auto-generated method stub
		return "LDFR"+ fr + ", " + ix + ", " + address + "," +i;
	}

}
