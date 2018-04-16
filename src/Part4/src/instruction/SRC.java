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
public class SRC extends Abstractinstruction {
    int AL;
    int LR;
    int Ct;
    int r;

    public void execute(String instruction, CPU cpu, MCU mcu) throws MachineFaultException {
        // -----------------------------------
        // 031: SRC -> Shift Register by Count,
        // c(r) is shifted left (L/R =1) or right (L/R = 0) either logically
        // (A/L = 1) or arithmetically (A/L = 0)


        // -----------------------------------
        // TODO Auto-generated method stub
        this.AL = StringUtil.binaryToDecimal(instruction.substring(8, 9));
        this.LR = StringUtil.binaryToDecimal(instruction.substring(9, 10));
        this.Ct = StringUtil.binaryToDecimal(instruction.substring(12, 16));
        this.r = StringUtil.binaryToDecimal(instruction.substring(6, 8));
        int Bd = cpu.getRnByNum(r);
        if (AL == 0) {
            if (LR == 0) {
                Bd = Bd >> Ct;
            }
            if (LR == 1) {
                Bd = Bd << Ct;
            }
        }
        if (AL == 1) {
            if (LR == 0) {
                if (Bd >= 0)
                    Bd = (Bd >>> Ct);
                else {
                    String x = Integer.toBinaryString(Bd >>> Ct);
                    x = x.replace("1111111111111111", "");
                    Bd = Integer.parseInt(x, 2);
                }
            }
            if (LR == 1) {
                Bd = Bd << Ct;
            }
        }

        cpu.setRnByNum(r, Bd);
        cpu.increasePC();
    }

    public String getExecuteMessage() {
        // TODO Auto-generated method stub
        return "SRC " + r + ", " + Ct + ", " + LR + ", " + AL;
    }

}
