package io.github.saksham355.testgame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;



    public class Getlevel3saveTest {

        String TEST_FILE = "C:\\Users\\DELL\\OneDrive\\Documents\\sakshamsGame\\core\\src\\test\\java\\io\\github\\saksham355\\testgame\\testsave3.ser";

        @Test
        public void testGetlevel3State() {
            Level3Save loadedData = Getlevel3save.getlevel3State(TEST_FILE);
            assertNotNull(loadedData, "Loaded data should not be null");
        }
    }

