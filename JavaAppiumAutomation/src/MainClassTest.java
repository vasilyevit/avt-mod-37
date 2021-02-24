import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    @Test
    public void testGetLocalNumber(){
        Assert.assertEquals("getLocalNumber вернул число отличное от 14", 14, (int) new MainClass().getLocalNumber());
    }

    @Test
    public void testGetClassNumber(){
        Assert.assertTrue("getClassNumber вернуло значение меньше 45", (int) new MainClass().getClassNumber() > 45);
    }
}
