package ua.nure.arseniuk.dmytro;


public class BrainfuckVM {
    private byte[] memory;
    private Integer index;
    private StringBuilder stringBuilder;

    public BrainfuckVM(int memorySize) {
        index = 0;
        memory = new byte[memorySize];
        stringBuilder = new StringBuilder();
    }

    public BrainfuckVM() {
        this(30000);
    }

    public void increaseCurrentCell(int count) {
        memory[index] += count;
    }

    public void decreaseCurrentCell(int count) {
        memory[index] -= count;
    }

    public void increaseIndex(int count) {
        index += count;
    }

    public void decreaseIndex(int count) {
        index -= count;
    }

    public byte getCurrentCell() {
        return memory[index];
    }

    public void printInBuilder(char input) {
        stringBuilder.append(input);
    }

    public String getBuilderStrings() {
        return stringBuilder.toString().trim();
    }

}
