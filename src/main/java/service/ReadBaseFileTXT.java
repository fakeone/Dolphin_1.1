package service;

import javax.swing.*;
import java.io.*;

public class ReadBaseFileTXT {

    private String line;
    private BufferedReader fin;
    private static Base base = new Base();

    // ����� ������ ���������� �� ����� �://DolphinBase/.
    // � ����� ����� ��������� �������� ���������.
    // � ����� Base.txt ��������� ���������� � ���������.
    public void readFileBase() throws IOException {

        File f = new File("/DolphinBase/Base.txt"); // ��������� ���� �� Base.txt.
        try {
            fin = new BufferedReader(new FileReader(f)); // ������ ��� ����� ����� � �����.
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(new JFrame(), "����������� ���� � ����� " + ".\n"+
                    "��������� ����� �������, �������� ���� � ����� C://DolphinBase/Base.txt � ������������� ���������.",
                    "���� �� ������!",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        while ((line = fin.readLine()) != null) {
            Element elem = new Element(); // ������� �������.
            elem.setName(line); // ���������� �������� ��������.
            line = fin.readLine(); // ������ ��������� ������.
            elem.setCode(line); // ���������� ��� ��������.
            line = fin.readLine(); // ������ ��������� ������.
            elem.setPrice(Double.parseDouble(line)); // ���������� ���� ��������.
            //elem.setImage("/DolphinBase/" + elem.getName() + ".jpg"); // ���������� �������� ��������.
            base.addElemInBase(elem); // ��������� ������� � ����.
        }
        fin.close(); // ��������� �����
    }

    public Base getFullBase() {
        return base;
    }
}

