package service;

import view.MyFirstTableModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;

/**
 * Created by Дмитрий on 19.12.14.
 */
public class Search implements DocumentListener {

    private JTextField JTFFilter;
    private  TableRowSorter<MyFirstTableModel> sorter;

    public Search(JTextField JTFFilter, TableRowSorter<MyFirstTableModel> sorter) {
        this.JTFFilter = JTFFilter;
        this.sorter = sorter;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        String text = JTFFilter.getText();

        if (text.trim().length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        String text = JTFFilter.getText();

        if (text.trim().length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }


            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Не поддерживается.");
            }
    }
