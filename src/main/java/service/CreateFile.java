package service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.io.*;

/**
 * Created by Дмитрий on 17.12.14.
 */
public class CreateFile {

    private Playground playground;
    private int count;

    public CreateFile(Playground playground, int count) {
        this.playground = playground;
        this.count = count;
    }


    public void createPDF() {
        Document document = new Document();
        try{
            BaseFont times = BaseFont.createFont("c:/windows/fonts/times.ttf","cp1251", BaseFont.EMBEDDED);
            PdfWriter.getInstance(document, new FileOutputStream("/DolphinBase/Playground.pdf")).setStrictImageSequence(true);
            document.open();

            com.itextpdf.text.Image imageTop = com.itextpdf.text.Image.getInstance("/DolphinBase/Top.jpg");
            imageTop.scaleToFit(525, 200);
            document.add(imageTop);
            document.add(new Paragraph("  "));

            PdfPTable table = new PdfPTable(7);
            table.setTotalWidth(400);
            table.setWidthPercentage(100);

            PdfPCell cell;

            String topTableName [] = {"№ п/п", "Изображение", "Наименование", "Код", "Кол-во (шт.)", "Цена с НДС (грн.)",
                    "Сумма с НДС (грн.)"};
            int lenght = 0;
            while(topTableName.length - lenght > 0) {
                cell = new PdfPCell(new Phrase(topTableName[lenght], new com.itextpdf.text.Font(times, 10)));
                cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                cell.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                table.addCell(cell);
                lenght++;
            }
            lenght = 0;

            while(playground.getpGroundList().size() > count) {
                Object column [] = {new Phrase("" + (1 + count), new com.itextpdf.text.Font(times, 10)),
                        com.itextpdf.text.Image.getInstance("/DolphinBase/images/" + playground.getpGroundList().get(count).getCode() + ".png"),
                        new Phrase(playground.getpGroundList().get(count).getName(), new com.itextpdf.text.Font(times, 10)),
                        new Phrase(playground.getpGroundList().get(count).getCode(), new com.itextpdf.text.Font(times, 10)),
                        new Phrase(String.valueOf(playground.getpGroundList().get(count).getCount()), new com.itextpdf.text.Font(times, 10)),
                        new Phrase(String.valueOf(playground.getpGroundList().get(count).getPrice()), new com.itextpdf.text.Font(times, 10)),
                        new Phrase(String.valueOf(playground.getpGroundList().get(count).getCount() * playground.getpGroundList().get(count).getPrice()), new com.itextpdf.text.Font(times, 10))};
                while(column.length - lenght > 0) {
                    if(lenght == 1){
                        Image img = com.itextpdf.text.Image.getInstance("/DolphinBase/images/" + playground.getpGroundList().get(count).getCode() + ".png");
                        img.scaleToFit(74,74);
                        cell = new PdfPCell(img);
                        table.addCell(cell);
                    } else {
                        cell = new PdfPCell((Phrase)column[lenght]);
                        cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        cell.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                        table.addCell(cell);
                    }
                    lenght++;
                }
                lenght = 0;
                count++;
            }

            String inform[] = {"Оборудование (грн.):",
                    "Монтажные работы \"Dolphin\" ("+ playground.getWorkPricePercent()+ "%" + ") (грн):",
                    "Скидка (" + playground.getDiscount() +"%):",
                    "Доставка (грн.):",
                    "Всего (грн.):"

            };

            Object priceInfo[] = {
                    playground.getPlayGroundPrise(),
                    playground.getWorkPrice(),
                    playground.getDiscountPrice(),
                    playground.getPriceDelivery(),
                    playground.getFullPrice()
            };


            int i = 0;
            while (inform.length - i > 0) {
                if ((Double)priceInfo[i] == 0.0){

                } else {
                    cell = new PdfPCell(new Phrase(inform[i], new com.itextpdf.text.Font(times, 10)));
                    cell.setColspan(6);
                    cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
                    cell.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                    table.addCell(cell);
                    cell = new PdfPCell(new Phrase(String.valueOf(priceInfo[i]), new com.itextpdf.text.Font(times, 10)));
                    cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                    table.addCell(cell);
                }
                i++;
            }
            cell = new PdfPCell(new Phrase("Адресс доставки: " + playground.getPosition(), new com.itextpdf.text.Font(times, 10)));
            cell.setColspan(7);
            table.addCell(cell);

            document.add(table);
            document.add(new Paragraph("  "));
            com.itextpdf.text.Image imageBottom = com.itextpdf.text.Image.getInstance("/DolphinBase/Bottom.jpg");
            imageBottom.scaleToFit(525, 200);
            document.add(imageBottom);
            count = 0;

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
        try {
            Runtime.getRuntime().exec("cmd /C " + "/DolphinBase/Playground.pdf");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Файл Playground.pdf невозможно открыть, проверьте наличие доступа файлу.",
                    "Невозможно открыть файл!",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }

    //    public void сreateTXT(){
//        Writer writer = null;
//        try {
//            writer = new BufferedWriter(new OutputStreamWriter(
//                    new FileOutputStream(new File("/DolphinBase/Playground.txt")), "utf-8"));
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
