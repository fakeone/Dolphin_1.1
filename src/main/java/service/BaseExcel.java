package service;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Дмитрий on 23.12.14.
 */
public class BaseExcel {

    private String baseLink = "/DolphinBase/Price.xls";
    private Base base = new Base();

    public void readBaseExcel () throws IOException {
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(baseLink));
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row = null;
        HSSFCell cell = null;
        int rows = sheet.getPhysicalNumberOfRows(); //получаем актуальное число строк

        for(int r = 15; r < rows; r++) {
            row = sheet.getRow(r); //берем строку
            if((row != null) ) {
                cell = row.getCell(0);
                if(cell != null){
                    Element elem = new Element();
                    cell = row.getCell(1); //берем 2-ю ячейку
                    if(cell != null) {
                        elem.setName(cell.getStringCellValue()); // Записываем назване элемента.
                    }
                    cell = row.getCell(4); //берем 5-ю ячейку
                    if(cell != null) {
                        elem.setCode(cell.getStringCellValue()); // Записываем код элемента.
                    }
                    cell = row.getCell(5); //берем 6-ю ячейку
                    if(cell != null) {
                        elem.setPrice(cell.getNumericCellValue()); // Записываем цену элемента.
                    }
                    if(elem.getPrice() != 0.0){
                        base.addElemInBase(elem);
                    }
                //cell.setCellValue("Modified " + r); //задаем значение ячейки
            }
        }
        FileOutputStream fileOut = new FileOutputStream("/outputTest.xls");
        wb.write(fileOut);
        fileOut.close();
    }
    }

    

    public Base getBase() {
        return base;
    }

}
