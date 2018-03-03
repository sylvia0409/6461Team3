/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import memory.Memory;

import memory.Cache.CacheLine;
import util.Const;

/**
 *
 * @author yiqian
 */
public class MCU {
    
        ArrayList<Integer> memory;
    	Cache cache;

	String printerBuffer;
	String keyboardBuffer;
	String cardBuffer;
    
        /**
         * inital MCU
         */
        public MCU(){
            this.cache = new Cache();
            this.memory = new ArrayList<Integer>(Const.Memory_Bound);
		for (int i = 0; i < Const.Memory_Bound; i++) {
			this.memory.add(0);
		}
        }
        
        public Cache getCache(){
            return cache;
        }
        
        public String getKeyboardBuffer() {
            return keyboardBuffer;
	}

	public void setKeyboardBuffer(String keyboardBuffer) {
            this.keyboardBuffer = keyboardBuffer;
	}
        
        public String getCardBuffer() {
            return cardBuffer;
	}

	public void setCardBuffer(String cardBuffer) {
            this.cardBuffer = cardBuffer;
	}
        
        public String getPrinterBuffer() {
            return printerBuffer;
	}

	public void setPrinterBuffer(String printerBuffer) {
            this.printerBuffer = printerBuffer;
	}
        
    public void expandMemorySize(){
        if(this.memory!=null && this.memory.size()>0){
            this.memory.ensureCapacity(Const.Memory_Expand_Bound);
            for (int currentSize = memory.size(); currentSize<Const.Memory_Expand_Bound; currentSize++){
                this.memory.add(0);
            }
        }
        System.out.println("Memory size has been expanded to "+ memory.size());
    }
        
    public int getCurrentMemorySize(){
        if(this.memory != null){
            return this.memory.size();
        }
        return 0;
    }
        
    public void storeInMemory(int address, int value){
        if(this.memory!=null){
            this.memory.set(address, value);
        }
    }
    
    public int fetchFromMemory(int address){
        return this.memory.get(address);
    }
        /**
	 * 
	 * fetch a word from cache. If the word is not in cache, fetch it from
	 * memory, then store it into cache.
	 * 
	 * @param address
	 * @return
	 */
	public int fetchFromCache(int address) {
		for (CacheLine line : cache.getCacheLines()) { // check every block
														// whether the tag is
														// already exist
			if (address == line.getTag()) {
				return line.getData(); // tag exist, return the data of the
										// block
			}
		}
		// tag not exist
		int value = fetchFromMemory(address);
		cache.add(address, value);
		return value;
	}       
    
	/**
	 * store into cache with replacement. Also store into memory at same time.
	 * 
	 * @param address
	 * @param value
	 */
    	public void storeIntoCache(int address, int value) {
		storeInMemory(address, value);
		for (CacheLine line : cache.getCacheLines()) { // check every block the
														// tag is already exist
			if (address == line.getTag()) {
				line.setData(value); // replace the block
				return;
			}
		}
		// tag not exist
		cache.add(address, value);
	}
    
    
	/**
	 * Load program into Cache by address, value form
	 * 
	 * @param HashMap<String, Integer>
	 */
    	public void loadProgram(HashMap<String, Integer> program) {
		if (program != null) {
			for (Map.Entry<String, Integer> entry : program.entrySet()) {
				int address = Integer.parseInt(entry.getKey());
				int value = entry.getValue();
				storeIntoCache(address, value);
			}
		}
	}
}
