package generator;

import generator.Validate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.io.IOException;

public class ValidateTest {
    @Test
    public void testValidator() throws IOException {
        String testPassword1 = "";
        String testPassword2 = "popsmoke123";
        String testPassword3 = "Dbolarinwa201!";
        String testPassword4 = "SONY-1000XM4";
        String testPassword5 = "starwars";

        boolean result = Validate.validatePassword(testPassword1);
        // assert result
        Assertions.assertEquals(result, false);

        result = Validate.validatePassword(testPassword2);
        // assert result
        Assertions.assertEquals(result, false);

        result = Validate.validatePassword(testPassword3);
        // assert result
        Assertions.assertEquals(result, true);

        result = Validate.validatePassword(testPassword4);
        // assert result
        Assertions.assertEquals(result, true);

        result = Validate.validatePassword(testPassword5);
        // assert result
        Assertions.assertEquals(result, false);
    }
}
