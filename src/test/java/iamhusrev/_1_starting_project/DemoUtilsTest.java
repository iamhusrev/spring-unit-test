package iamhusrev._1_starting_project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DemoUtilsTest {

    DemoUtils demoUtils;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        System.out.println("Running @BeforeAll");
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
    void testEqualsAndNotEquals() {

        System.out.println("Running test: testEqualsAndNotEquals");

        DemoUtils demoUtils = new DemoUtils();

        assertEquals(6, demoUtils.add(2, 4), "2+4 must be 6");
        Assertions.assertNotEquals(6, demoUtils.add(1, 9), "1+9 must not be 6");
    }


    @Test
    void testNullAndNotNull() {

        System.out.println("Running test: testNullAndNotNull");
        DemoUtils demoUtils = new DemoUtils();

        String str1 = null;
        String str2 = "iamhusrev";

        assertNull(demoUtils.checkNull(str1), "Object should be null");
        assertNotNull(demoUtils.checkNull(str2), "Object should not be null");
    }


}