package lesson05;

import org.junit.*;
import loc.linux.webapp.WebAppExeption;

import static org.junit.Assert.*;

public class CalculatorTest {

    static Calculator calc;

    static {
        calc = new Calculator();
    }

    @Before
    public void setUp() throws Exception {

    }

    @Test(expected = Error.class)
    public void testAbs() throws Exception {
        assertEquals(6, calc.abs(-5));
        throw new WebAppExeption("");
    }
}