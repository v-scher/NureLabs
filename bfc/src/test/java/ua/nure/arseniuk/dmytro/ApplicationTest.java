package ua.nure.arseniuk.dmytro;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ApplicationTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ApplicationTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(ApplicationTest.class);
    }

    /**
     * Brainfuck compiler test without loops.
     */
    public void testHelloWorldWithoutLoops() {

        final String expected = "Hello World!";
        final String input = "+++++++++++++++++++++++++++++++++++++++++++++" +
                "+++++++++++++++++++++++++++.+++++++++++++++++" +
                "++++++++++++.+++++++..+++.-------------------" +
                "---------------------------------------------" +
                "---------------.+++++++++++++++++++++++++++++" +
                "++++++++++++++++++++++++++.++++++++++++++++++" +
                "++++++.+++.------.--------.------------------" +
                "---------------------------------------------" +
                "----.-----------------------.";
        BrainfuckCompiler compiler = new BrainfuckCompiler();
        BrainfuckExecutor executor = new BrainfuckExecutor();
        executor.execute(compiler.compile(input));
        assertEquals(expected, executor.getOutput());
    }

    /**
     * Brainfuck compiler test with loops.
     */
    public void testHelloWorldLoops() {

        final String expected = "Hello World!";
        final String input = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++\n" +
                " .>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.\n" +
                " ------.--------.>+.>.";
        BrainfuckCompiler compiler = new BrainfuckCompiler();
        BrainfuckExecutor executor = new BrainfuckExecutor();
        executor.execute(compiler.compile(input));
        assertEquals(expected, executor.getOutput());
    }
}
