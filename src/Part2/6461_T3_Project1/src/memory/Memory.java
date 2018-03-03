/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

/**
 *
 * @author yiqian
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import util.Const;

import java.util.ArrayList;

public class Memory {
    
    ArrayList<Integer> memory;
    
    public void Memory(){
        this.memory = new ArrayList<Integer> (Const.Memory_Bound);
        for (int i=0; i<Const.Memory_Bound; i++){
            this.memory.add(0);
        }
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
    
    public void add(int num){
        this.memory.add(num);
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


    //all the locations of memory are set to integer,
    //all the contents are set to the string format of binary number.
    /*Map<String,Integer> memoryMap = new HashMap<>();
    String fileName = "MainMem.txt";
    
    
    public void readFile(int indexLine){
        File file = new File(fileName);
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = indexLine;
            while ((tempString = reader.readLine()) != null){
                line++;
                setValue(line, tempString);
            }
            reader.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    
    public void setValue(String key, int value){
        memoryMap.put(key, value);
    }

    public int getValue(String key){
        return memoryMap.get(key);
    }

    public void clear(int key){ //clear one cell of memory
        memoryMap.put(key, "");
    }*/


}
