package Part1;

import java.util.HashMap;
import java.util.Map;

public class Memory {
    //all the locations of memory are set to integer,
    //all the contents are set to the string format of binary number.
    Map<Integer,String> memoryMap = new HashMap<>();

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
