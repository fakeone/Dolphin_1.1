package view;

import service.BaseExcel;
import service.Element;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyFirstTableModel implements TableModel  {

    public static final int IMAGE_COLUMN = 0;
    public static final int NAME_COLUMN = 1;
    public static final int SIZE_COLUMN = 2;
    public static final int CODE_COLUMN = 3;
    public static final int PRICE_COLUMN = 4;
    public static final int COUNT_COLUMN = 5;
    private BaseExcel baseExcel = new BaseExcel();
    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
    private List<Element> elements;

    public MyFirstTableModel() {}

    public MyFirstTableModel(List<Element> elements) {
        this.elements = elements;
    }
    @Override
    public void addTableModelListener(TableModelListener listener) {
        listeners.add(listener);
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case IMAGE_COLUMN:
                return ImageIcon.class;
            case NAME_COLUMN:
                return String.class;
            case SIZE_COLUMN:
                return String.class;
            case CODE_COLUMN:
                return String.class;
            case PRICE_COLUMN:
                return Double.class;
            case COUNT_COLUMN:
                return Integer.class;
        }
            return Object.class;

    }
    @Override
    public int getColumnCount() {
        return 6;
    }
    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case IMAGE_COLUMN:
                return "Изображение";
            case NAME_COLUMN:
                return "Название";
            case SIZE_COLUMN:
                return "Размеры(мм)";
            case CODE_COLUMN:
                return "Код";
            case PRICE_COLUMN:
                return "Цена(грн.)";
            case COUNT_COLUMN:
                return "Количество";
        }
        return "";
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
            case SIZE_COLUMN:
                return element.getSize();
            case CODE_COLUMN:
                return element.getCode();
            case PRICE_COLUMN:
                return element.getPrice();
            case COUNT_COLUMN:
                return element.getCount();
        }
        return "";
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == PRICE_COLUMN || columnIndex == COUNT_COLUMN ;
    }
    @Override
    public void removeTableModelListener(TableModelListener listener) {
        listeners.remove(listener);
    }
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Element element = elements.get(rowIndex);
        switch (columnIndex) {
            case NAME_COLUMN:
                element.setName((String)value);
                break;
            case PRICE_COLUMN:
                int response = JOptionPane.showConfirmDialog(null, "Подтвердите изменение цены элемента!");
                if (response == 0) {
                    element.setPrice((Double)value);
                    try {
                        baseExcel.changePrice();
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(new JFrame(), "Закройте Excel файл с базой ",
                                "Файл открыт!",
                                JOptionPane.ERROR_MESSAGE);
                        e.printStackTrace();
                    }
                }
                break;
            case COUNT_COLUMN:
                element.setCount((Integer)value);
                break;
        }
        //Запись "elements" в базу.

    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

}

