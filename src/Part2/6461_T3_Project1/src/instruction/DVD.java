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
public class DVD extends Abstractinstruction {

    int rx;
    int ry;

    @Override
    public void execute(String instruction, CPU cpu, MCU mcu) throws MachineFaultException {
        // ------------------------------------------------------
        // 021: DVD -> Divide Register by Register
        // rx, rx+1 <- c(rx)/c(ry)
        // rx must be 0 or 2
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

            // if c(ry) = 0, it means that we have a divide by zero
            if (cpu.getRnByNum(ry) == 0) {
                cpu.setCCElementByBit(Const.ConditionCode.DIVZERO.getValue(), true);
                // TODO: I think it is a trap and we should handle it.
            } else {

                // doing the division: result is the same as quotient
                int result = cpu.getRnByNum(rx) / cpu.getRnByNum(ry);

                // first we check if we have an overflow
                if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
                    cpu.setCCElementByBit(Const.ConditionCode.OVERFLOW.getValue(), true);
                } else {

                    // if we do not have an overflow, we continue updating the
                    // value of cpu
                    int remainder = cpu.getRnByNum(rx) % cpu.getRnByNum(ry);

                    // saving the quotient in rx
                    cpu.setRnByNum(rx, result);

                    // saving the remainder in rx+1
                    cpu.setRnByNum(rx + 1, remainder);
                }
            }

            cpu.increasePC();
        }

    }

    @Override
    public String getExecuteMessage() {
        // TODO Auto-generated method stub
        return "DVD " + rx + ", " + ry;
    }

}
