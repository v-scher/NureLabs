package other;

public class Bracers {
    public static void main(String[] args) {
        char[][] combinations = new char[255][8];

        for (byte j = -128; j < 127; j++)
            for (int i = 0; i < Byte.SIZE; i++)
                combinations[128 + j][i] = ((1 & (j >> i)) == 1 ? '(' : ')');

        for (char[] equ : combinations)
            if (isOk(equ))
                System.out.println(equ);
    }

    public static boolean isOk(char[] equ) { // array of bracers
        int stack = 0;
        for (int i = 0; i < equ.length; i++)
            if ((stack += (equ[i] == '(') ? 1 : -1) < 0)
                return false;
        return (stack == 0);
    }
}