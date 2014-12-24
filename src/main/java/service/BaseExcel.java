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
 * Created by ������� on 23.12.14.
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
        int rows = sheet.getPhysicalNumberOfRows(); //�������� ���������� ����� �����

        for(int r = 15; r < rows; r++) {
            row = sheet.getRow(r); //����� ������
            if((row != null) ) {
                cell = row.getCell(0);
                if(cell != null){
                    Element elem = new Element();
                    cell = row.getCell(1); //����� 2-� ������
                    if(cell != null) {
                        elem.setName(cell.getStringCellValue()); // ���������� ������� ��������.
                    }
                    cell = row.getCell(4); //����� 5-� ������
                    if(cell != null) {
                        elem.setCode(cell.getStringCellValue()); // ���������� ��� ��������.
                    }
                    cell = row.getCell(5); //����� 6-� ������
                    if(cell != null) {
                        elem.setPrice(cell.getNumericCellValue()); // ���������� ���� ��������.
                    }
                    if(elem.getPrice() != 0.0){
                        base.addElemInBase(elem);
                    }
                //cell.setCellValue("Modified " + r); //������ �������� ������
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
