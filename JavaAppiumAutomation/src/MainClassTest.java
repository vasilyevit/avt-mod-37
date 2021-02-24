import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    @Test
    public void testGetLocalNumber(){
        Assert.assertEquals("getLocalNumber вернул число отличное от 14", 14, (int) new MainClass().getLocalNumber());
    }
}
