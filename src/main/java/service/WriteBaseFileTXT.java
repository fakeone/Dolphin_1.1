package service;

import javax.swing.*;
import java.io.*;
import java.util.List;

/**
 * Created by ������� on 28.11.14.
 */
public class WriteBaseFileTXT {

    private Writer writer = null;
    private int counter = 0;
    // ����� ���������� ���������� � ����� �://DolphinBase/.
    // � ����� ����� ��������� �������� ���������.
    // � ����� Base.txt ��������� ���������� � ���������.
    public void WriteFileBase(List<Element> elements) throws IOException {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("/DolphinBase/Base.txt")));
            while(elements.size() != counter){
                writer.write(elements.get(counter).getName() + "\n" + elements.get(counter).getCode() + "\n" +
                        elements.get(counter).getPrice() + "\n");
                counter++;
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "���� Base.txt ���������� ��������, ��������� ������� ������� � ��������� �����.",
                    "���������� ������������ ����!",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            try {writer.close();} catch (Exception ex) {}
        }
    }
}
