package com.iamhusrev;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;


//@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
//@DisplayNameGeneration(DisplayNameGenerator.Standard.class)
//@DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)
//@TestMethodOrder(MethodOrderer.DisplayName.class)

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DemoUtilsTest {

    DemoUtils demoUtils;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        System.out.println("Running @BeforeAll");
        System.out.println();
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        System.out.println("Running @AfterAll");
    }

    @BeforeEach
    void setUpBeforeEach() {
        demoUtils = new DemoUtils();
        System.out.println("@BeforeEach executes before each test");
    }

    @AfterEach
    void tearDownAfterEach() {
        System.out.println("Running @AfterEach");
        System.out.println();
    }

    @Test
    @Order(1)
    @DisplayName("Equals and Not Equals")
    void testEqualsAndNotEquals() {
        System.out.println("Running test: testEqualsAndNotEquals");
        assertEquals(6, demoUtils.add(2, 4), "2+4 must be 6");
        Assertions.assertNotEquals(6, demoUtils.add(1, 9), "1+9 must not be 6");
    }

    @Test
    @Order(1)
    @DisplayName("Equals and Not Equals")
    void testMultiply() {
        System.out.println("Running test: testMultiply");
        assertEquals(6, demoUtils.multiply(2, 3), "2*4 must be 6");
    }

    @Test
    @DisplayName("Null and Not Null")
    void testNullAndNotNull() {
        System.out.println("Running test: testNullAndNotNull");
        String str1 = null;
        String str2 = "iamhusrev";
        assertNull(demoUtils.checkNull(str1), "Object should be null");
        assertNotNull(demoUtils.checkNull(str2), "Object should not be null");
    }

    @Test
    @DisplayName("Same and Not Same")
    void testSameAndNotSame() {
        System.out.println("Running test: testSameAndNotSame");
        String str1 = "iamhusrev";

        assertSame(demoUtils.getAcademy(), demoUtils.getAcademyDuplicate(), "Object should refer to the same");
        assertNotSame(str1, demoUtils.getAcademy(), "Object should not refer to the same");
    }

    @Test
    @DisplayName("True and False")
    void testTrueAndFalse() {
        System.out.println("Running test: testTrueAndFalse");
        int gradeOne = 10;
        int gradeTwo = 5;
        assertTrue(demoUtils.isGreater(gradeOne, gradeTwo), "Object should be greater");
        assertFalse(demoUtils.isGreater(gradeTwo, gradeOne), "Object should not be greater");
    }

    @Test
    @DisplayName("Array Equals")
    void testArrayEquals() {
        String[] stringArray = {"A", "B", "C"};
        System.out.println("Running test: testArrayEquals");
        assertArrayEquals(stringArray, demoUtils.getFirstThreeLettersOfAlphabet(), "Arrays should be the same");
    }

    @Test
    @DisplayName("Iterable equals")
    void testIterableEquals() {
        List<String> theList = List.of("luv", "2", "code");

        System.out.println("Running test: testIterableEquals");
        assertIterableEquals(theList, demoUtils.getAcademyInList(), "Expected list should be same as actual list");
    }

    @Test
    @DisplayName("Lines match")
    void testLinesMatch() {
        List<String> theList = List.of("luv", "2", "code");
        System.out.println("Running test: testLinesMatch");
        assertLinesMatch(theList, demoUtils.getAcademyInList(), "Lines should match");
    }


    @Test
    @DisplayName("Throws and Does Not Throw")
    void testThrowsAndDoesNotThrow() {
        System.out.println("Running test: testThrowsAndDoesNotThrow");
        assertThrows(Exception.class, () -> {
            demoUtils.throwException(-1);
        }, "Should throw exception");

        System.out.println("Running test: testThrowsAndDoesNotThrow");
        assertDoesNotThrow(() -> {
            demoUtils.throwException(5);
        }, "Should not throw exception");

    }

    @DisplayName("Timeout")
    @Test
    void testTimeout() {

        System.out.println("Running test: testTimeout");
        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> {
                demoUtils.checkTimeout();
            },
            "Method should execute in 3 seconds");
    }


}