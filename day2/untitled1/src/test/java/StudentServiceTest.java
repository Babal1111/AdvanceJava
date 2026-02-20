import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    private final StudentService studentService = new StudentService();


    @Test
    void testCalculateGrade_Distinction() {
        String grade = studentService.calculateGrade(80);
        assertEquals("Distinction", grade);
    }

    @Test
    void testCalculateGrade_FirstClass() {
        String grade = studentService.calculateGrade(65);
        assertEquals("First Class", grade);
    }

    @Test
    void testCalculateGrade_SecondClass() {
        String grade = studentService.calculateGrade(55);
        assertEquals("Second Class", grade);
    }

    @Test
    void testCalculateGrade_Fail() {
        String grade = studentService.calculateGrade(40);
        assertEquals("Fail", grade);
    }


    @Test
    void testIsPassed_WhenMarksAbove50_ShouldReturnTrue() {
        boolean result = studentService.isPassed(75);
        assertTrue(result);
    }

    @Test
    void testIsPassed_WhenMarksBelow50_ShouldReturnFalse() {
        boolean result = studentService.isPassed(45);
        assertFalse(result);
    }



    @Test
    void testCalculateGrade_WhenMarksNegative_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            studentService.calculateGrade(-10);
        });
    }

    @Test
    void testCalculateGrade_WhenMarksAbove100_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            studentService.calculateGrade(120);
        });
    }



    @Test
    void testCalculateGrade_ShouldNotReturnNull() {
        String grade = studentService.calculateGrade(70);
        assertNotNull(grade);
    }
}