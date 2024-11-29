package io.github.saksham355.testgame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class Getlevel2saveTest {

    String TEST_FILE = "C:\\Users\\DELL\\OneDrive\\Documents\\sakshamsGame\\core\\src\\test\\java\\io\\github\\saksham355\\testgame\\testsave2.ser";

    @Test
    public void testGetlevel2State() {
        Level2Save loadedData = Getlevel2save.getlevel2State(TEST_FILE);
        assertNotNull(loadedData, "Loaded data should not be null");
    }
}
