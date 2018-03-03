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
public class TRR extends Abstractinstruction {

    int rx;
    int ry;

    @Override
    public void execute(String instruction, CPU cpu, MCU mcu) throws MachineFaultException {
        // ------------------------------------------------------
        // 022: TRR -> Test the equality of Register and Register
        // if c(rx) = c(ry), set cc(4) <- 1; else, cc(4) <- 0
        // ------------------------------------------------------

        rx = StringUtil.binaryToDecimal(instruction.substring(6, 8));
        ry = StringUtil.binaryToDecimal(instruction.substring(8, 10));

        if (cpu.getRnByNum(rx) == cpu.getRnByNum(ry)) {
            cpu.setCCElementByBit(Const.ConditionCode.EQUALORNOT.getValue(), true);
        } else {
            cpu.setCCElementByBit(Const.ConditionCode.EQUALORNOT.getValue(), false);
        }

        cpu.increasePC();
    }

    @Override
    public String getExecuteMessage() {
        return "TRR " + rx + ", " + ry;
    }
}
