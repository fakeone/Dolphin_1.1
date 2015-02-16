package importer;

import util.PropsUtil;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class IgnoreCodesReader {

    //TODO read path from file
    private static List<String> ignoreElementsDirs = PropsUtil.getIgnoreCodesDirs();

    public static List<String> getIgnoreCodes() {
        List<String> ignoreCodes = new ArrayList<>();
        ignoreCodes.addAll(getConstantIgnoreCodes());

        for (String ignoreElementsDir : ignoreElementsDirs) {
            File dir = new File(ignoreElementsDir);
            if (!dir.exists() || !dir.isDirectory()) {
                //TODO throw exception
                continue;//TODO tmp
            }

            FileFilter filter = File::isDirectory;
            File[] directories = dir.listFiles(filter);
            Arrays.asList(directories).forEach(d -> ignoreCodes.add(d.getName()));
            //alternative version - ignoreCodes.addAll(Arrays.asList(directories).stream().map(File::getName).collect(Collectors.toList()));
        }

        return ignoreCodes;
    }

    private static Collection<String> getConstantIgnoreCodes() {
        Collection<String> ignoreCodes = new ArrayList<>();
        ignoreCodes.add("TOP");
        return ignoreCodes;
    }
}
