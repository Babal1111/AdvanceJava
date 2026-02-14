import main.com.tyss.StudentEligible;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class StudentEligibleTest {
    StudentEligible stu;
    @BeforeEach
    public void setup() {
        stu = new StudentEligible();
    }

    @Test

    public  void isEligible(){
        Assertions.assertTrue(stu.isElegible(20));
    }



}
