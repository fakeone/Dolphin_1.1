package service;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ������� on 23.12.14.
 */
public class BaseExcel {

    private String baseLink = "/DolphinBase/Price.xls";
    private static ArrayList<Element> baseList = new ArrayList(); // ������ ���������
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


    public void readBaseExcel () throws IOException {
        fs = new POIFSFileSystem(new FileInputStream(baseLink));
        wb = new HSSFWorkbook(fs);
        sheet = wb.getSheetAt(0);
        row = null;
        cell = null;
        rows = sheet.getPhysicalNumberOfRows(); //�������� ���������� ����� �����

        for(int r = 15; r < rows; r++) {
            row = sheet.getRow(r); //����� ������
            if((row != null) ) {
                cell = row.getCell(0);
                if(cell != null){
                    elem = new Element();
                    cell = row.getCell(nameColumn); //����� 2-� ������
                    if(cell != null) {
                        elem.setName(cell.getStringCellValue()); // ���������� �������� ��������.
                    }
                    cell = row.getCell(sizeColumn); //����� 3-� ������
                    if(cell != null) {
                        elem.setSize(cell.getStringCellValue()); // ���������� �������� ��������.
                    }
                    cell = row.getCell(codeColumn); //����� 5-� ������
                    if(cell != null) {
                        elem.setCode(cell.getStringCellValue()); // ���������� ��� ��������.
                    }
                    cell = row.getCell(priceColumn); //����� 6-� ������
                    if(cell != null) {
                        elem.setPrice(cell.getNumericCellValue()); // ���������� ���� ��������.
                    }
                    if(elem.getPrice() != 0.0){
                        elem.setImage("/DolphinBase/images/" + elem.getCode() + ".png");
                        baseList.add(elem);
                    }
                }
            }
        }
    }

    public void changePrice() throws IOException {

        for(int r = 15; r < rows; r++) {
            row = sheet.getRow(r); //����� ������
            if((row != null) ) {
                cell = row.getCell(codeColumn); //����� 5-� ������
                //System.out.println("��� �������� - " + cell.getStringCellValue() +" = " + baseList.get(0).getCode());
                if(cell != null) {
                    for(int i = 0; i < baseList.size(); i++){
                        if(cell.getStringCellValue() == baseList.get(i).getCode()) {
                            HSSFCell cellPrice = row.getCell(priceColumn); //����� 6-� ������
                            //System.out.println("5� ������ - " + cellPrice.getStringCellValue());
                            if(cellPrice != null) {
                                cellPrice.setCellValue(baseList.get(i).getPrice()); //������ �������� ������
                            }
                        }
                    }
                }
            }
        }

        FileOutputStream fileOut = new FileOutputStream(baseLink);
        wb.write(fileOut);
        fileOut.close();
    }

    public ArrayList<Element> getBaseList() {
        return baseList;
    }
//    public void addElement(List<Element> elements) throws IOException {
//
//        //sheet.shiftRows(sheet.getPhysicalNumberOfRows(), sheet.getLastRowNum(), 1); // r - ������, cell - ������
//        sheet.createRow(sheet.getPhysicalNumberOfRows()-12).createCell(1).setCellValue(elements.get(elements.size()-1).getName());
//        //cell.setCellValue("Modified " + r); //������ �������� ������
//
//    }


}
