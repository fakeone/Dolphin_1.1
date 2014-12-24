package view;

import service.Element;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MySecondTableModel extends MyFirstTableModel {

    private static final int IMAGE_COLUMN = 0;
    private static final int NAME_COLUMN = 1;
    private static final int CODE_COLUMN = 2;
    private static final int PRICE_COLUMN = 3;
    private static final int COUNT_COLUMN = 4;

    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
    private List<Element> elements; // Элементы готовой площадки.

    public MySecondTableModel() {
    }

    public MySecondTableModel(List<Element> elements) {
        this.elements = elements;
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
            case IMAGE_COLUMN:
                try {
                    return new ImageIcon(element.getImage());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(new JFrame(), "Отсутствует изображение для " + element.getName() + ".\n"+
                            "Программа будет закрыта, добавьте изображение и перезапустите программу.",
                            "Отсутствует изображение!",
                            JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
            case NAME_COLUMN:
                return element.getName();
            case CODE_COLUMN:
                return element.getCode();
            case PRICE_COLUMN:
                return element.getPrice();
            case COUNT_COLUMN:
                return element.getCount();
        }
        return "";
    }

}

