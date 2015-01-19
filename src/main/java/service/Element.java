package service;

import view.MainWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by ������� on 27.07.14.
 *
 * ������ ����� ��������� �������������� �������� ������� ��������.
 * ����:
 * - ��������;
 * - ����;
 * - ����������� ��������.
 */
public class Element {

    private String name = "��� �����";
    private String code = "���";
    private String size = "������";
    private double price = 0;
    private BufferedImage imageBuff = null;
    private int count = 0;

    public Element()  {
        try {
        imageBuff = ImageIO.read(new File(MainWindow.dolphinImage));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "����������� ���� � ������������ C://DolphinBase/1dolphin.png" ,
                    "����������� �����������!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public Element(String name, double price, String imageURL) {
        this.name = name;
        this.price = price;

        try {
            this.imageBuff = ImageIO.read(new File(imageURL));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(new JFrame(), "����������� ����������� ��� " + name,
                    "����������� �����������!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public BufferedImage getImage() {
        return imageBuff;
    }

    public void setImage(String imageURL) {

        try {
            imageBuff = ImageIO.read(new File(imageURL));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(new JFrame(), "����������� ����������� ��� " + name,
                    "����������� �����������!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}