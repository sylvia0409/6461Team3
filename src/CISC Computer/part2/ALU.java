package part2;

public class ALU {

    public long add(long value1, long value2){
        long result = value1 + value2;
        if(result > (2^15 - 1)){
            return result - (2^16);
        }
        if(result < -(2^15)){
            return result + (2^16);
        }
        return result;
    }

    public long subtract(long value1, long value2){
        long result = value1 - value2;
        if(result > (2^15 - 1)){
            return result - (2^16);
        }
        if(result < -(2^15)){
            return result + (2^16);
        }
        return result;
    }

    public long multiply(long value1, long value2){
        return value1 * value2;
    }

    public long[] divide(long value1, long value2){
        long[] result = new long[2];
        result[0] = value1 / value2;
        result[1] = value1 % value2;

        return result;
    }

    public String AND(String value1, String value2){
        if(value2.length() > value1.length()){
            for (int i = 0; i < value2.length() - value1.length(); i++) {
                value1 = "0"+value1;
            }
        } else {
            for (int i = 0; i < value1.length() - value2.length(); i++) {
                value2 = "0"+value2;
            }
        }
        String[] v1 = value1.split("");
        String[] v2 = value2.split("");
        String result = "";
        for (int i = 0; i < value1.length(); i++) {
            if(v1[i].equals("1")&&v2[i].equals("1")){
                result = result + "1";
            } else {
                result = result + "0";
            }
        }
        return result;
    }

    public String ORR(String value1, String value2){
        if(value2.length() > value1.length()){
            for (int i = 0; i < value2.length() - value1.length(); i++) {
                value1 = "0"+value1;
            }
        } else {
            for (int i = 0; i < value1.length() - value2.length(); i++) {
                value2 = "0"+value2;
            }
        }
        String[] v1 = value1.split("");
        String[] v2 = value2.split("");
        String result = "";
        for (int i = 0; i < value1.length(); i++) {
            if(v1[i].equals("0")&&v2[i].equals("0")){
                result = result + "0";
            } else {
                result = result + "1";
            }
        }
        return result;
    }

    public String NOT(String value){
        String[] v = value.split("");
        String result = "";
        for (int i = 0; i < value.length(); i++) {
            if(v[i].equals("1")){
                result = result + "0";
            } else {
                result = result + "1";
            }
        }
        return result;
    }

    public String logicalLeftShift(String value, int count){
        String result = value.substring(count, value.length());
        for (int i = 0; i < count; i++) {
            result = result + "0";
        }
        return result;
    }

    public String logicalRightShift(String value, int count){
        String result = value.substring(0, value.length() - count);
        for (int i = 0; i < count; i++) {
            result = "0" + result;
        }
        return result;
    }

    public String arithmeticLeftShift(String value, int count){
        String result = value.substring(count, value.length());
        for (int i = 0; i < count; i++) {
            result = result + "0";
        }
        return result;
    }

    public String arithmeticRightShift(String value, int count){
        String result = value.substring(0, value.length() - count);
        for (int i = 0; i < count; i++) {
            result = "1" + result;
        }
        return result;
    }

    public String rotateRight(String value, int count){
        for (int i = 0; i < count; i++) {
            value = value.substring(value.length()-1, value.length()) + value.substring(0, value.length()-1);
        }
        return value;
    }

    public String rotateLeft(String value, int count){
        for (int i = 0; i < count; i++) {
            value = value.substring(1, value.length()) + value.substring(0, 1);
        }
        return value;
    }
}
