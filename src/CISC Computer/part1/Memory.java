/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package part1;

/**
 *
 * @author yiqian
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Memory {
    //all the locations of memory are set to integer,
    //all the contents are set to the string format of binary number.
    Map<Integer,String> memoryMap = new HashMap<>();
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
    
    
    public void setValue(int key, String value){
        memoryMap.put(key, value);
    }

    public String getValue(int key){
        return memoryMap.get(key);
    }

    public void clear(int key){ //clear one cell of memory
        memoryMap.put(key, "");
    }


}
