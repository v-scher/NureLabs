package ua.nure.arseniuk.dmytro;

public class Application {
    public static void main(String[] args) {
        /**
         * [Outputs square numbers from 0 to 10000.
         * Daniel B Cristofani (cristofdathevanetdotcom)
         * http://www.hevanet.com/cristofd/brainfuck/]
         */
        String input = "++++[>+++++<-]>[<+++++>-]+<+[\n" +
                "    >[>+>+<<-]++>>[<<+>>-]>>>[-]++>[-]+\n" +
                "    >>>+[[-]++++++>>>]<<<[[<++++++++<++>>-]+<.<[>----<-]<]\n" +
                "    <<[>>>>>[>>>[-]+++++++++<[>-<-]+++++++++>[-[<->-]+[<<<]]<[>+<-]>]<<-]<<-\n" +
                "]";

        BrainfuckCompiler compiler = new BrainfuckCompiler();
        BrainfuckExecutor executor = new BrainfuckExecutor();
        executor.execute(compiler.compile(input));
    }
}
