package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PropsUtil {

    private static final Properties properties;

    static {
        String propPath = FileUtil.getRootFolder() + "/program.properties";
        properties = new Properties();
        try {
            properties.load(new FileInputStream(propPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPropertyValue(String key) {
        return properties.getProperty(key);
    }

    public static List<String> getIgnoreCodesDirs() {
        List<String> dirs = new ArrayList<>(2);
        String rootOfProg = properties.getProperty("dolphin.pro.directory");
        dirs.add(rootOfProg + "/tiles");
        dirs.add(rootOfProg + "/tiles_tiny");
        return dirs;
    }

}
