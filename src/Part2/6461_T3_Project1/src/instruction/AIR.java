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
public class AIR extends Abstractinstruction {

    int r;
    int immed;

    @Override
    public void execute(String instruction, CPU cpu, MCU mcu) throws MachineFaultException {
        // -----------------------------------
        // 006: AIR -> Add Immediate to Register, r = 0..3
        // r <- c(r) + immed
        // Note:
        // 1. if immed = 0, does nothing
        // 2. if c(r) = 0, loads r with immed
        // IX and I are ignored in this instruction
        // -----------------------------------

        r = StringUtil.binaryToDecimal(instruction.substring(6, 8));

        // this is the immediate operand.
        immed = StringUtil.binaryToDecimal(instruction.substring(11, 16));

        // if immed = 0, we do nothing
        if (immed != 0) {
            if (cpu.getRnByNum(r) == 0) {

                // if c(r) = 0, we load r with immed
                cpu.setRnByNum(r, immed);

            } else {

                // r <- c(r) + immed

                int result = cpu.getRnByNum(r) + immed;
                
                // we check if we have an overflow
                if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
                    cpu.setCCElementByBit(Const.ConditionCode.OVERFLOW.getValue(), true);
                } else {
                    // if we do not have an overflow, we update the value of
                    // register
                    cpu.setRnByNum(r, result);
                }
            }
        }

        cpu.increasePC();
    }

    @Override
    public String getExecuteMessage() {
        return "AIR " + r + ", " + immed;
    }

}
