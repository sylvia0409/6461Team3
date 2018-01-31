package Part1;

public class ControlPanel {

    public static void main(String[] args) {
        Memory mainMemory = new Memory();
        CPU cpu = new CPU();

        //put the instructions and required data into main memory
        cpu.setIndexRegister(2, 1000);//initialize index register
        cpu.setIndexRegister(1, 700);
        cpu.setIndexRegister(0, 500);
        mainMemory.setValue(6, "0000011100011111");//store instruction LDR 3,0,31
        mainMemory.setValue(31, "1110011001101101");//store the required data to location 31
        mainMemory.setValue(7, "0000101101010101");//store instruction STR 3,0,21
        mainMemory.setValue(8, "0000111001010001");//store instruction LDA 2,0,17
        mainMemory.setValue(9, "1010010010001100");//store instruction LDX 2,12
        mainMemory.setValue(712, "0000001110011001");//store the required data to location 712
        mainMemory.setValue(10, "1010100001011001");//store instruction STX, 1, 25
        //put the instruction from memory to cpu and execute
        cpu.execute(mainMemory,6);

        //todo: GUI and ROM Loader program

    }
}
