/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instruction;

import memory.MCU;
import cpu.CPU;

/**
 *
 * @author yiqian
 */
public class HLT extends Abstractinstruction {
	@Override
	public void execute(String instruction, CPU cpu, MCU mcu){
		// -----------------------------------
		// 000: HALT -> Stops the machine.
		// -----------------------------------
		// TODO Auto-generated method stub
		if(instruction.substring(8,16).equals("00000000")){
			//System.out.println("HALT!");
			//JOptionPane.showMessageDialog(null, "Program stop!");
		}
	}

	@Override
	public String getExecuteMessage() {

		// TODO Auto-generated method stub
		return "HALT";

	}    
}
