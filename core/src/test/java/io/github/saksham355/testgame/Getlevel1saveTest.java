package io.github.saksham355.testgame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class Getlevel1saveTest {

   String TEST_FILE = "C:\\Users\\DELL\\OneDrive\\Documents\\sakshamsGame\\core\\src\\test\\java\\io\\github\\saksham355\\testgame\\testsave1.ser";

    @Test
    public void testGetlevel1State() {
        Level1Save loadedData = Getlevel1save.getlevel1State(TEST_FILE);
        assertNotNull(loadedData, "Loaded data should not be null");
    }
}
