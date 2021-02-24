import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    @Test
    public void testGetLocalNumber(){
        Assert.assertEquals("getLocalNumber вернул число отличное от 14", 14, (int) new MainClass().getLocalNumber());
    }

    @Test
    public void testGetClassNumber(){
        Assert.assertTrue("getClassNumber вернуло значение меньше 45", new MainClass().getClassNumber() > 45);
    }

    @Test
    public void testGetClassString(){
        Assert.assertTrue("getClassString вернул строку, которая не содержит hello или Hello",
                new MainClass().getClassString().contains("hello")
                        || new MainClass().getClassString().contains("Hello"));
    }
}
