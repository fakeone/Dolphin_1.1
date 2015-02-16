package importer;

import model.Element;
import model.Playground;
import service.BaseExcel;
import util.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SchemeFileParserTest {

    @org.junit.Test
    public void testParseFile() throws Exception {
        List<String> ignoreCodes = new ArrayList<>();
        ignoreCodes.add("IGNORE_IT");
        BaseExcel excel = new BaseExcel();
        excel.readBaseExcel("/home/adv/misc/Dolphin/Price.xls");//TODO move reading logic to constructor?

        //action
        Playground playground = new SchemeFileParser(ignoreCodes, excel).parseFile("/home/adv/misc/Dolphin/src/main/resources/playground2015.scheme");
        List<Element> elements = playground.getElements();

        //asserts
        assertNotNull(elements);
        assertEquals(3, elements.size());
        for (Element element : elements) {
            String code = element.getCode();
            assertTrue(code.equals("C12") || code.equals("B33") || code.equals("A15"));
        }
    }

    @org.junit.Test
    public void testParseFile_ModifiedNamesOfElements() throws Exception {
        FileUtil.setRootFolder("/home/adv/misc/DolphinBase");

        List<String> ignoreCodes = new ArrayList<>();
        ignoreCodes.add("IGNORE_IT");
        ignoreCodes.addAll(IgnoreCodesReader.getIgnoreCodes());
        BaseExcel excel = new BaseExcel();
        excel.readBaseExcel("/home/adv/misc/Dolphin/Price.xls");//TODO move reading logic to constructor?

        //action
        Playground playground = new SchemeFileParser(ignoreCodes, excel).parseFile("/home/adv/misc/DolphinBase/schemes/playground2015.scheme");
        List<Element> elements = playground.getElements();

        //asserts
        assertNotNull(elements);
        assertEquals(3, elements.size());
        for (Element element : elements) {
            String code = element.getCode();
            assertTrue(code.equals("G-21") || code.equals("G-20") || code.equals("B-41"));
        }
    }

    //TODO move to other class
    @org.junit.Test
    public void testPahts() throws Exception {
        String path = new File(".").getAbsolutePath();

        System.out.println(path);

        System.out.println(System.getProperty("user.dir"));
    }
}