package service;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
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
    private double price = 0;
    private BufferedImage imageBuff = null;
    private int count = 0;
    private boolean choice = false;

    public Element()  {
        try {
        imageBuff = ImageIO.read(new File("/DolphinBase/1dolphin.png"));
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

    public void setImage(byte[] data) {

        try {
//            imageBuff = ImageIO.read(new File(imageURL));
            imageBuff = ImageIO.read(new ByteArrayInputStream(data));
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

    public boolean isChoice() {
        return choice;
    }

    public void setChoice(boolean choice) {
        this.choice = choice;
    }
}