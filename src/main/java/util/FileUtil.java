package util;

import java.io.File;

public class FileUtil {
    private static String rootFolder = System.getProperty("user.dir");

    public static String getRootFolder() {
        return rootFolder;
    }

    //for test purposes
    public static void setRootFolder(String newRootFolder) {
        rootFolder = newRootFolder;
    }
}
