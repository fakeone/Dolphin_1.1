package view;

import model.Element;

import javax.swing.*;
import java.util.*;

public class MySecondTableModel extends MyFirstTableModel {

    private List<Element> elements = new ArrayList<>(); // Элементы готовой площадки.

    public MySecondTableModel() {
    }

    public MySecondTableModel(Collection<Element> elements) {
        this.elements.addAll(new HashSet<Element>(elements));
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public int getRowCount() {
        return elements.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Element element = elements.get(rowIndex);
        switch (columnIndex) {
            case MyFirstTableModel.IMAGE_COLUMN:
                try {
                    return new ImageIcon(element.getImage());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(new JFrame(), "Отсутствует изображение для " + element.getName() + ".\n"+
                                    "Программа будет закрыта, добавьте изображение и перезапустите программу.",
                            "Отсутствует изображение!",
                            JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
            case MyFirstTableModel.NAME_COLUMN:
                return element.getName();
            case MyFirstTableModel.SIZE_COLUMN:
                return element.getSize();
            case MyFirstTableModel.CODE_COLUMN:
                return element.getCode();
            case MyFirstTableModel.PRICE_COLUMN:
                return element.getPrice();
            case MyFirstTableModel.COUNT_COLUMN:
                return element.getCount();
        }
        return "";
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {

    }

    public void removeRow(int selectedRow) {
        elements.remove(selectedRow);
    }

}
