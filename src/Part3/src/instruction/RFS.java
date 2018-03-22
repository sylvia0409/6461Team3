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
public class RFS extends Abstractinstruction {

    int immed;

    @Override
    public void execute(String instruction, CPU cpu, MCU mcu) throws MachineFaultException {
        // -----------------------------------
        // 015: RFS -> Return From Subroutine w/ return code as Immed portion
        // (optional) stored in the instruction's address field
        // cpu.setR0(Immed));
        // -----------------------------------
        immed = StringUtil.binaryToDecimal(instruction.substring(11, 16));
        cpu.setRnByNum(0, immed);
        cpu.setPC(cpu.getRnByNum(3));
    }

    @Override
    public String getExecuteMessage() {
        // TODO Auto-generated method stub
        return "RFS " + immed;
    }

}
