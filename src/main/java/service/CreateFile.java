package service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import model.*;
import model.Element;
import util.FileUtil;

import javax.swing.*;
import java.io.*;

/**
 * Created by Дмитрий on 17.12.14.
 */
public class CreateFile {

    private static final String rootDir = FileUtil.getRootFolder();
    private final Playground playground;

    public CreateFile(Playground playground) {
        this.playground = playground;
    }


    public void createPDF() {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(rootDir + "/Playground.pdf")).setStrictImageSequence(true);
            document.open();

            com.itextpdf.text.Image imageTop = com.itextpdf.text.Image.getInstance(rootDir + "/Top.jpg");
            imageTop.scaleToFit(525, 200);
            document.add(imageTop);
            document.add(new Paragraph("  "));

            PdfPTable table = createPdfPTable();
            document.add(table);

            document.add(new Paragraph("  "));
            com.itextpdf.text.Image imageBottom = com.itextpdf.text.Image.getInstance(rootDir + "/Bottom.jpg");
            imageBottom.scaleToFit(525, 200);
            document.add(imageBottom);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Файл Playground.pdf невозможно создать, проверьте наличие доступа к корневому диску.",
                    "Невозможно создать файл!",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();

        }

        viewPdf();
    }

    private void viewPdf() {
        try {
            //TODO check correctness of path
            Runtime.getRuntime().exec("cmd /C " + rootDir + "/Playground.pdf");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Файл Playground.pdf невозможно открыть, проверьте наличие доступа файлу.",
                    "Невозможно открыть файл!",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private PdfPTable createPdfPTable() throws DocumentException, IOException {
        PdfPTable table = new PdfPTable(7);
        table.setTotalWidth(400);
        table.setWidthPercentage(100);

        BaseFont font = BaseFont.createFont(FileUtil.getRootFolder() + "/fonts/times.ttf", "cp1251", BaseFont.EMBEDDED);
        createTableTopRow(table, font);

        int elementNumber = 0;
        for (Element element : playground.getElements()) {
            Object row[] = {
                    new Phrase("" + (1 + elementNumber), new Font(font, 10)),
                    Image.getInstance(rootDir + "/images/" + element.getCode() + ".png"),
                    new Phrase(element.getName(), new Font(font, 10)),
                    new Phrase(element.getCode(), new Font(font, 10)),
                    new Phrase(String.valueOf(element.getCount()), new Font(font, 10)),
                    new Phrase(String.valueOf(element.getPrice()), new Font(font, 10)),
                    new Phrase(String.valueOf(element.getCount() * element.getPrice()), new Font(font, 10))
            };
            elementNumber++;

            for (int columnIndex = 0; columnIndex < row.length; columnIndex++) {
                if (columnIndex == 1) {
                    Image img = Image.getInstance(rootDir + "/images/" + element.getCode() + ".png");
                    img.scaleToFit(74, 74);
                    PdfPCell cell = new PdfPCell(img);
                    table.addCell(cell);
                } else {
                    Phrase phrase = (Phrase) row[columnIndex];
                    PdfPCell cell = new PdfPCell(phrase);
                    cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                    table.addCell(cell);
                }
            }
        }

        addPriceInfo(table, font);

        PdfPCell addressCell = new PdfPCell(new Phrase("Адресс доставки: " + playground.getPosition(), new Font(font, 10)));
        addressCell.setColspan(7);
        table.addCell(addressCell);
        return table;
    }

    private void createTableTopRow(PdfPTable table, BaseFont times) throws DocumentException, IOException {
        String topTableNames[] = {"№ п/п", "Изображение", "Наименование", "Код", "Кол-во (шт.)", "Цена с НДС (грн.)",
                "Сумма с НДС (грн.)"};
        for (String columnName : topTableNames) {
            PdfPCell cell = new PdfPCell(new Phrase(columnName, new Font(times, 10)));
            cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            cell.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            table.addCell(cell);
        }
    }

    private void addPriceInfo(PdfPTable table, BaseFont times) {
        String[] inform = getInfo();
        Double[] priceInfo = getPriceInfo();

        int i = 0;
        while (inform.length - i > 0) {
            Double price = priceInfo[i];
            if (price != 0.0) {
                PdfPCell cell = new PdfPCell(new Phrase(inform[i], new Font(times, 10)));
                cell.setColspan(6);
                cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
                cell.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(String.valueOf(price), new Font(times, 10)));
                cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                cell.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                table.addCell(cell);
            }
            i++;
        }
    }

    private String[] getInfo() {
        return new String[]{"Оборудование (грн.):",
                "Монтажные работы \"Dolphin\" (" + playground.getWorkPricePercent() + "%" + ") (грн):",
                "Скидка (" + playground.getDiscount() + "%):",
                "Доставка (грн.):",
                "Всего (грн.):"

        };
    }

    private Double[] getPriceInfo() {
        return new Double[]{
                playground.getPlayGroundPrise(),
                playground.getWorkPrice(),
                playground.getDiscountPrice(),
                playground.getPriceDelivery(),
                playground.getFullPrice()
        };
    }

    //    public void сreateTXT(){
//        Writer writer = null;
//        try {
//            writer = new BufferedWriter(new OutputStreamWriter(
//                    new FileOutputStream(new File(rootDir + "/Playground.txt")), "utf-8"));
//
//            writer.write(
//                    "Общая цена площадки = " + playground.getPlayGroundPrise() + " грн." + "\n" + "\n" +
//                            "Площадка состоит из " + playground.getElemNumber() + " элементов: " + "\n" + "\n"
//            );
//            // Открываем сохраненный файл.
//            Runtime.getRuntime().exec("cmd /C " + "/DolphinBase/Playground.txt");
//
//        } catch (IOException ex) {
//            JOptionPane.showMessageDialog(new JFrame(), "Файл Playground.txt невозможно создать, проверьте наличие доступа к корневому диску.",
//                    "Невозможно создать файл!",
//                    JOptionPane.ERROR_MESSAGE);
//        } finally {
//            try {
//                writer.close();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//    }

//    public void createDOC(){
//        Writer writer = null;
//        try {
//            writer = new BufferedWriter(new OutputStreamWriter(
//                    new FileOutputStream(new File("/DolphinBase/Playground.doc")), "utf-8"));
//
//            writer.write(
//                    "Общая цена площадки = " + playground.getPlayGroundPrise() + " грн." + "\n" + "\n" +
//                            "Площадка состоит из " + playground.getElemNumber() + " элементов: " + "\n" + "\n"
//            );
//            // Открываем сохраненный файл.
//            Runtime.getRuntime().exec("cmd /C " + "/DolphinBase/Playground.doc");
//
//        } catch (IOException ex) {
//            JOptionPane.showMessageDialog(new JFrame(), "Файл Playground.doc невозможно создать, проверьте наличие доступа к корневому диску.",
//                    "Невозможно создать файл!",
//                    JOptionPane.ERROR_MESSAGE);
//        } finally {
//            try {
//                writer.close();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//    }


}
