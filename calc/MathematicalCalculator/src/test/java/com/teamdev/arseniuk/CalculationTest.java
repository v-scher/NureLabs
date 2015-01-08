package com.teamdev.arseniuk;

import com.teamdev.arseniuk.impl.Calculator;
import com.teamdev.arseniuk.impl.State;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class CalculationTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testNumber() throws CalculationException {
        final double expected = 200;
        final String input = "200";
        Calculator calculator = new Calculator();
        assertEquals(expected, calculator.calculate(input).get(0), 0);
    }

    @Test
    public void testDotFirstNumber() throws CalculationException {
        final double expected = .200;
        final String input = ".200";
        Calculator calculator = new Calculator();
        assertEquals(expected, calculator.calculate(input).get(0), 0);
    }

    @Test
    public void testSimpleAddition() throws CalculationException {
        final double expected = 200;
        final String input = "100 + 100";
        Calculator calculator = new Calculator();
        assertEquals(expected, calculator.calculate(input).get(0), 0);
    }

    @Test
    public void testSimpleSubtraction() throws CalculationException {
        final double expected = 50;
        final String input = "150 - 100";
        Calculator calculator = new Calculator();
        assertEquals(expected, calculator.calculate(input).get(0), 0);
    }

    @Test
    public void testSimpleDivision() throws CalculationException {
        final double expected = 30;
        final String input = "90 / 3";
        Calculator calculator = new Calculator();
        assertEquals(expected, calculator.calculate(input).get(0), 0);
    }

    @Test
    public void testSimpleMultiplication() throws CalculationException {
        final double expected = 100;
        final String input = "20 * 5";
        Calculator calculator = new Calculator();
        assertEquals(expected, calculator.calculate(input).get(0), 0);
    }

    @Test
    public void testSimpleInvolution() throws CalculationException {
        final double expected = 1024;
        final String input = "2 ^ 10";
        Calculator calculator = new Calculator();
        assertEquals(expected, calculator.calculate(input).get(0), 0);
    }

    @Test
    public void testCalculationWithDifferentPriority() throws CalculationException {
        final double expected = 70;
        final String input = "10 + 20 * 3";
        Calculator calculator = new Calculator();
        assertEquals(expected, calculator.calculate(input).get(0), 0);
    }

    @Test
    public void testCalculationWithParenthesis() throws CalculationException {
        final double expected = 180;
        final String input = "2 * (10 + 20)* 3";
        Calculator calculator = new Calculator();
        assertEquals(expected, calculator.calculate(input).get(0), 0);
    }

    @Test
    public void testCalculationWithInnerParenthesis() throws CalculationException {
        final double expected = -21;
        final String input = "(10 + (1*3) - 20 )* 3";
        Calculator calculator = new Calculator();
        assertEquals(expected, calculator.calculate(input).get(0), 0);
    }

    @Test
    public void testAssociativity() throws CalculationException {
        final double expected = 256;
        final String input = "2^2^3";
        Calculator calculator = new Calculator();
        assertEquals(expected, calculator.calculate(input).get(0), 0);
    }


    @Test
    public void testExceptionRightParenthesisMissed() throws CalculationException {
        final String input = "(10 + (1*3) - 20 * 3";
        expectedException.expect(CalculationException.class);
        expectedException.expectMessage("Right parenthesis missed at position " + input.length());
        Calculator calculator = new Calculator();
        calculator.calculate(input);
    }

    @Test
    public void testExceptionLeftParenthesisMissed() throws CalculationException {
        final String input = "10 + (1*3) - 20 * 3)";
        expectedException.expect(CalculationException.class);
        expectedException.expectMessage("Left parenthesis missed at position " + (input.length() - 1));
        Calculator calculator = new Calculator();
        calculator.calculate(input);
    }

    @Test
    public void testExceptionDeadlock() throws CalculationException {
        final String input = "10 + + 3";
        expectedException.expect(CalculationException.class);
        expectedException.expectMessage("Deadlock in state " + State.BINARY_OPERATION.toString() + " at position " + 5);
        Calculator calculator = new Calculator();
        calculator.calculate(input);
    }

    @Test
    public void testSqrtFunction() throws CalculationException {
        final double expected = 3;
        final String input = "sqrt(9)";
        Calculator calculator = new Calculator();
        assertEquals(expected, calculator.calculate(input).get(0), 0);
    }

    @Test
    public void testMinFunction() throws CalculationException {
        final double expected = 3;
        final String input = "min(9,6,3)";
        Calculator calculator = new Calculator();
        assertEquals(expected, calculator.calculate(input).get(0), 0);
    }

    @Test
    public void testMaxFunction() throws CalculationException {
        final double expected = 9;
        final String input = "max(9,6,3)";
        Calculator calculator = new Calculator();
        assertEquals(expected, calculator.calculate(input).get(0), 0);
    }

    @Test
    public void testSumFunction() throws CalculationException {
        final double expected = 16;
        final String input = "sum(9,6,1)";
        Calculator calculator = new Calculator();
        assertEquals(expected, calculator.calculate(input).get(0), 0);
    }

    @Test
    public void testFunctionInDifficultExpression() throws CalculationException {
        final double expected = 23;
        final String input = "1+sum(9,1,(6),2*(1+2))";
        Calculator calculator = new Calculator();
        assertEquals(expected, calculator.calculate(input).get(0), 0);
    }


}
