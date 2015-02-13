package service;

import model.Element;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import util.FileUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дмитрий on 23.12.14.
 */
public class BaseExcel {

    private static List<Element> baseList = new ArrayList(); // Список элементов
    private static POIFSFileSystem fs;
    private static HSSFWorkbook wb;
    private static HSSFSheet sheet;
    private static HSSFRow row;
    private static HSSFCell cell;
    private static Element elem;
    private static int rows;
    private final static int nameColumn = 1;
    private final static int sizeColumn = 2;
    private final static int codeColumn = 4;
    private final static int priceColumn = 5;


    public void readBaseExcel(String linkExcel) throws IOException {
        fs = new POIFSFileSystem(new FileInputStream(linkExcel));
        wb = new HSSFWorkbook(fs);
        sheet = wb.getSheetAt(0);
        row = null;
        cell = null;
        rows = sheet.getPhysicalNumberOfRows(); //получаем актуальное число строк

        for(int r = 15; r < rows; r++) {
            row = sheet.getRow(r); //берем строку
            if((row != null) ) {
                cell = row.getCell(0);
                if(cell != null){
                    elem = new Element();
                    cell = row.getCell(nameColumn); //берем 2-ю ячейку
                    if(cell != null) {
                        elem.setName(cell.getStringCellValue()); // Записываем название элемента.
                    }
                    cell = row.getCell(sizeColumn); //берем 3-ю ячейку
                    if(cell != null) {
                        elem.setSize(cell.getStringCellValue()); // Записываем название элемента.
                    }
                    cell = row.getCell(codeColumn); //берем 5-ю ячейку
                    if(cell != null) {
                        elem.setCode(cell.getStringCellValue()); // Записываем код элемента.
                    }
                    cell = row.getCell(priceColumn); //берем 6-ю ячейку
                    if(cell != null) {
                        elem.setPrice(cell.getNumericCellValue()); // Записываем цену элемента.
                    }
                    if(elem.getPrice() != 0.0){
                        //TODO current dir + "/images/"
                        elem.setImage(FileUtil.getRootFolder() + "/images/" + elem.getCode() + ".png");
                        baseList.add(elem);
                    }
                }
            }
        }
    }

    public void changePrice(String excelFile) throws IOException {

        for(int r = 15; r < rows; r++) {
            row = sheet.getRow(r); //берем строку
            if((row != null) ) {
                cell = row.getCell(codeColumn); //берем 5-ю ячейку
                //System.out.println("Код элемента - " + cell.getStringCellValue() +" = " + baseList.get(0).getCode());
                if(cell != null) {
                    for(int i = 0; i < baseList.size(); i++){
                        if(cell.getStringCellValue() == baseList.get(i).getCode()) {
                            HSSFCell cellPrice = row.getCell(priceColumn); //берем 6-ю ячейку
                            //System.out.println("5я строка - " + cellPrice.getStringCellValue());
                            if(cellPrice != null) {
                                cellPrice.setCellValue(baseList.get(i).getPrice()); //задаем значение ячейки
                            }
                        }
                    }
                }
            }
        }

        FileOutputStream fileOut = new FileOutputStream(excelFile);
        wb.write(fileOut);
        fileOut.close();
    }

    public Element getElementByShortCode(String shortCode) {
//        //TODO try lambda
        for (Element element : baseList) {
            if (shortCode.equals(element.getShortCode())) {
                return element;
            }
        }
        return null;
    }

    public List<Element> getBaseList() {
        return baseList;
    }
//    public void addElement(List<Element> elements) throws IOException {
//
//        //sheet.shiftRows(sheet.getPhysicalNumberOfRows(), sheet.getLastRowNum(), 1); // r - строка, cell - ячейка
//        sheet.createRow(sheet.getPhysicalNumberOfRows()-12).createCell(1).setCellValue(elements.get(elements.size()-1).getName());
//        //cell.setCellValue("Modified " + r); //задаем значение ячейки
//
//    }


}
