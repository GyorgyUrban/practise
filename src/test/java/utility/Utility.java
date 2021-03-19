package utility;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;

public class Utility {
    private static final String PATH = "src/test/resources/";

    public static String readFile(String fileName) {
        try {
            FileInputStream fis = new FileInputStream(PATH +fileName);
            return IOUtils.toString(fis, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
