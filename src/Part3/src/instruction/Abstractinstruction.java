/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instruction;

import cpu.CPU;
import memory.MCU;
import util.MachineFaultException;


/**
 *
 * @author yiqian
 */
public abstract class Abstractinstruction {
    
    public abstract void execute(String instruction, CPU cpu, MCU mcu) throws MachineFaultException;

    public abstract String getExecuteMessage();    
}
