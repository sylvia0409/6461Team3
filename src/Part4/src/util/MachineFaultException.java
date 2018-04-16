/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author yiqian
 */
public class MachineFaultException extends Exception{
	
	int faultCode;
	String message;
	
	/**
	 * @param faultCode
	 * @param message
	 */
	public MachineFaultException(int faultCode, String message){
		this.faultCode = faultCode;
		this.message = message;
	}
	
	public MachineFaultException(int faultCode){
		this.faultCode = faultCode;
	}
	
	/**
	 * @return
	 */
	public int getFaultCode(){
		return this.faultCode;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	public String getMessage(){
		return this.message;
	}
	

}
