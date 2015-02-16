package model;

import view.MainWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Дмитрий on 27.07.14.
 *
 * Данный класс описывает характеристики элемента детской площадки.
 * поля:
 * - название;
 * - цена;
 * - изображение элемента.
 */
public class Element {

    private String name = "Без имени";
    private String code = "Код";
    private String size = "Размер";
    private double price = 0;
    private BufferedImage imageBuff = null;
    private int count = 0;
    private String shortCode;

    public Element()  {
        String dolphinImage = MainWindow.dolphinImage;
        try {
            imageBuff = ImageIO.read(new File(dolphinImage));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JFrame(), "Отсутствует файл с дельфинчиком " + dolphinImage,
                    "Отсутствует изображение!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

//    public Element(String name, double price, String imageURL) {
//        this.name = name;
//        this.price = price;
//
//        try {
//            this.imageBuff = ImageIO.read(new File(imageURL));
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(new JFrame(), "Отсутствует изображение для " + name,
//                    "Отсутствует изображение!",
//                    JOptionPane.ERROR_MESSAGE);
//        }
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        BigDecimal bd = new BigDecimal(price).setScale(2, RoundingMode.HALF_EVEN);
        price = bd.doubleValue();
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
            JOptionPane.showMessageDialog(new JFrame(), "Отсутствует изображение для " + name,
                    "Отсутствует изображение!",
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
        shortCode = code.replace("-", "");
    }

    public String getShortCode() {
        return shortCode;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Element element = (Element) o;

        if (!code.equals(element.code)) return false;
        if (!name.equals(element.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + code.hashCode();
        return result;
    }
}