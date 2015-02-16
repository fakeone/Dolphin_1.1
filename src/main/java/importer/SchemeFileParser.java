package importer;

import model.Element;
import model.Playground;
import service.BaseExcel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SchemeFileParser {

    private final List<String> ignoreCodes;
    private BaseExcel excel;

    public SchemeFileParser(List<String> ignoreCodes, BaseExcel excel) {
        this.ignoreCodes = ignoreCodes;
        this.excel = excel;
    }

    public Playground parseFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("File " + filePath + " doesn't exists");
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<Element> elements = new ArrayList<>();

        String line = reader.readLine();
        while (line != null) {
            if (ignoreLine(line)) {//ignore lines which represent tiles elements
                line = reader.readLine();
                continue;
            }

            Element element = parseLine(line);
            if (element != null) {
                if (elements.contains(element)) {//TODO fix equals method
                    int index = elements.indexOf(element);
                    Element elementFromList = elements.get(index);
                    int count = elementFromList.getCount() + 1;
                    elementFromList.setCount(count);
                } else {
                    element.setCount(1);
                    elements.add(element);
                }
            }

            line = reader.readLine();
        }
        Playground playground = new Playground();
        playground.setElements(elements);
        return playground;
    }

    private Element parseLine(String line) {
        String[] strings = line.split(" ");
        if (strings.length < 2) {
            return null;
        }

        String shortCode = getShortCode(strings);
        return getElementByShortCode(shortCode);
    }

    private String getShortCode(String[] strings) {
        //if it's element name of which consists of two words
        if (strings.length == 5) {
            return strings[0] + " " + strings[1];
        }

        return strings[0];
    }

    private Element getElementByShortCode(String shortCode) {
        if (ignoreElement(shortCode)) {
            return null;
        }

        //TODO load data for element from file by it's code

        return excel.getElementByShortCode(shortCode);
    }

    //TODO deside what to do with ignore elem. like "rezina blue", with whitespaces
    private boolean ignoreElement(String code) {
        return ignoreCodes.contains(code);
    }

    private boolean ignoreLine(String line) {
        //TODO implement
        return line == null || line.isEmpty();
    }
}
