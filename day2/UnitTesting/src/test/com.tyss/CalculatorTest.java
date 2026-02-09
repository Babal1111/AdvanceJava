package com.tyss;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @Test
    public void testSubstract() {
        Calculator c = new Calculator();
        assertEquals(2, c.subtract(5, 3));
    }
}
