package com.tyss;


import main.com.tyss.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import org.junit.jupiter.api.extension.TestWatcher;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

//import java.util.Optional;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;



public class CalculatorTest {
    Calculator calc;
    @BeforeEach
    public void setup() {
        calc = new Calculator();
    }

    @Test
    public void testAdd() {
        Assertions.assertEquals(3, calc.add(1,2));
    }

    @Test
    public void testSubs(){
        Assertions.assertEquals(-1, calc.subtract(1,2));
    }

    @Test
    public void testIsEven() {
        Assertions.assertTrue(calc.isEven(8));
        Assertions.assertFalse(calc.isEven(5));
    }

//    @Test
// CSV file tests
//

    @ParameterizedTest
    @CsvSource({
            "2, 3, 5",
            "0, 0, 0",
            "-5, 10, 5",
            "100, 200, 300"
    })
    public void testAddParamaterValue(int a, int b, int expected) {
        assertEquals(expected, calc.add(a, b),a + " + " + b + " : Does Not Match");
    }

    // 2️⃣ @ValueSource (Single parameter)
    // Used when only ONE input is needed
    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 10, 0, -2, -4})
    public void testIsEvenReturnsTrue(int number) {
        assertTrue(calc.isEven(number));
    }

    // 3️⃣ @ValueSource (False cases)
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 9, -1, -3, -5})
    public void testIsEvenReturnsFalse(int number) {
        assertFalse(calc.isEven(number));
    }

    // 4️⃣ @MethodSource
    // Used for dynamic / complex test data
    private static Stream<Arguments> provideDivisionTestCases() {
        return Stream.of(
                Arguments.of(20, 4, 5),
                Arguments.of(10, 2, 5),
                Arguments.of(15, 5, 3),
                Arguments.of(0, 7, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideDivisionTestCases")
    public void testDivideWithMethodSource(int a, int b, int expected) {
        assertEquals(expected, calc.divide(a, b));
    }

    // 5️⃣ @CsvFileSource
    // Reads test data from external CSV file
    @ParameterizedTest
    @CsvFileSource(resources = "/resources/calculator-data.csv", numLinesToSkip = 0)
    void testAddUsingCsvFile(int a, int b, int expected) {
        assertEquals(expected, calc.add(a, b));
    }

    // 6️⃣ @NullSource
    // Supplies null values
    @ParameterizedTest
    @NullSource
    void testNullSource(Integer value) {
        assertNull(value);
    }

    // 7️⃣ @EmptySource
    // Supplies empty values
    @ParameterizedTest
    @EmptySource
    void testEmptySource(String value) {
        assertTrue(value.isEmpty());
    }

    // 8️⃣ @NullAndEmptySource
    // Supplies both null and empty values
    @ParameterizedTest
    @NullAndEmptySource
    void testNullAndEmptySource(String value) {
        assertTrue(value == null || value.isEmpty());
    }

    // 9️⃣ @EnumSource
    // Used when testing enum values
    enum Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }

    @ParameterizedTest
    @EnumSource(Operation.class)
    void testEnumSource(Operation operation) {
        assertNotNull(operation);
    }



    // ================================
    // NORMAL TESTS
    // ================================

    @Test
    void testAssertEquals() {
        assertEquals(5, calc.add(2, 3));
    }

    @Test
    void testAssertNotEquals() {
        assertNotEquals(10, calc.subtract(8, 3));
    }

    @Test
    void testAssertTrue() {
        assertTrue(calc.multiply(2, 3) == 6);
    }

    @Test
    void testAssertFalse() {
        assertFalse(calc.multiply(2, 3) == 5);
    }

    @Test
    void testAssertNull() {
        Calculator obj = null;
        assertNull(obj);
    }

    @Test
    void testAssertNotNull() {
        assertNotNull(calc);
    }

    @Test
    void testAssertSame() {
        Calculator ref1 = calc;
        Calculator ref2 = calc;
        assertSame(ref1, ref2);
    }

    @Test
    void testAssertNotSame() {
        Calculator ref1 = new Calculator();
        Calculator ref2 = new Calculator();
        assertNotSame(ref1, ref2);
    }

    @Test
    void testAssertThrows() {
        assertThrows(ArithmeticException.class, () -> {
            calc.divide(10, 0);
        });
    }

    @Test
    @DisplayName("Division Success Test")
    void testDivide() {
        assertEquals(2, calc.divide(10, 5));
    }

    @RepeatedTest(2)
    void testRepeated() {
        assertEquals(4, calc.add(2, 2));
    }

    @Disabled
    @Test
    void disabledTest() {
        fail("This test should be skipped");
    }


    // Runs AFTER each test case
    @AfterEach
    void tearDown() {
        calc = null;
    }

    // Runs ONCE after all test cases
//    @AfterAll
//    static void afterAllTests() {
//        System.out.println("--  Completed All Test --\nResult Summary : ");
//        System.out.println("PASSED TESTS = " + passed);
//        System.out.println("FAILED TESTS = " + failed);
//        System.out.println("SKIPPED/DISABLED TESTS = " + skipped);
//    }

}

