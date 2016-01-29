package lesson05;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    static Calculator calc;
    @Before
    public void setUp() throws Exception {
        calc = new Calculator();
    }

    @Test
    public void testAbc() throws Exception {
            assertEquals(5, calc.abc(-5));

    }
}