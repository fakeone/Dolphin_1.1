package service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.io.*;

/**
 * Created by ������� on 17.12.14.
 */
public class CreateFile {

    private Playground playground;
    private int count;

    public CreateFile(Playground playground, int count) {
        this.playground = playground;
        this.count = count;
    }

    public void �reateTXT(){
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(new File("/DolphinBase/Playground.txt")), "utf-8"));

            writer.write(
                    "����� ���� �������� = " + playground.getPlayGroundPrise() + " ���." + "\n" + "\n" +
                            "�������� ������� �� " + playground.getElemNumber() + " ���������: " + "\n" + "\n" +
                            playground.getInfo()
            );

            //JOptionPane.showMessageDialog(new JFrame(), "���� ������ � �������� � �������� �����!");
            // ��������� ����������� ����.
            Runtime.getRuntime().exec("cmd /C " + "/DolphinBase/Playground.txt");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "���� Playground.txt ���������� �������, ��������� ������� ������� � ��������� �����.",
                    "���������� ������� ����!",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void createDOC(){
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(new File("/DolphinBase/Playground.doc")), "utf-8"));

            writer.write(
                    "����� ���� �������� = " + playground.getPlayGroundPrise() + " ���." + "\n" + "\n" +
                            "�������� ������� �� " + playground.getElemNumber() + " ���������: " + "\n" + "\n" +
                            playground.getInfo()
            );

            //JOptionPane.showMessageDialog(new JFrame(), "���� ������!");
            // ��������� ����������� ����.
            Runtime.getRuntime().exec("cmd /C " + "/DolphinBase/Playground.doc");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "���� Playground.doc ���������� �������, ��������� ������� ������� � ��������� �����.",
                    "���������� ������� ����!",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void createPDF(){
        Document document = new Document();
        try{
            BaseFont times = BaseFont.createFont("c:/windows/fonts/times.ttf","cp1251", BaseFont.EMBEDDED);
            PdfWriter.getInstance(document, new FileOutputStream("/DolphinBase/Playground.pdf"));

            document.open();

            com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance("/DolphinBase/1dolphin.png");
            document.add(image);
            document.add(new Paragraph("������� �������� ����� Dolphin. ����� ���� �������� = " + playground.getPlayGroundPrise() + " ���." + "\n" + "\n" +
                    "�������� ������� �� " + playground.getElemNumber() + " ���������: " + "\n" + "\n", new com.itextpdf.text.Font(times, 14) ));
            while(playground.getpGroundList().size() > count) {
                document.add(new Paragraph(playground.getpGroundList().get(count).getName() + " (" +
                        playground.getpGroundList().get(count).getCode() + "): " +
                        playground.getpGroundList().get(count).getPrice()+ " ���. * " +
                        playground.getpGroundList().get(count).getCount() + "��. = " +
                        playground.getpGroundList().get(count).getPrice() * playground.getpGroundList().get(count).getCount() +
                        " ���." + "\n"
                        , new com.itextpdf.text.Font(times, 14)));
                document.add(com.itextpdf.text.Image.getInstance("/DolphinBase/" + playground.getpGroundList().get(count).getName() + ".jpg"));
                count++;
            }
            count = 0;
            //JOptionPane.showMessageDialog(new JFrame(), "���� ������ � �������� � �������� �����!");
            // ��������� ����������� ����.
            Runtime.getRuntime().exec("cmd /C " + "/DolphinBase/Playground.pdf");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "���� Playground.pdf ���������� �������, ��������� ������� ������� � ��������� �����.",
                    "���������� ������� ����!",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

}
