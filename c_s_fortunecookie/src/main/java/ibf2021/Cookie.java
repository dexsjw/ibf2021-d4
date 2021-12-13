package ibf2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cookie {
    
    private String cookieStr;

    public String getCookie(String filePath) {
        List<String> cookie = new ArrayList<>();
        try {
            Path cookieFilePath = Paths.get(filePath);
            if (Files.isReadable(cookieFilePath)) {
                cookie = Files.readAllLines(cookieFilePath);        //stores lines as List<String>
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        Collections.shuffle(cookie);
        cookieStr = cookie.get(0);
        
        return cookieStr;

    }
}
