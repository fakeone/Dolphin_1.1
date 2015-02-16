package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class FirstTableRenderer extends DefaultTableCellRenderer {


    //FIXME it doesnt't fills collumns that aren't editable
    @Override
    public Component getTableCellRendererComponent(JTable table,
                                                   Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

        Integer count = (Integer) table.getModel().getValueAt(row, MyFirstTableModel.COUNT_COLUMN);
        if (count != null && count > 0) {
            setBackground(Color.YELLOW);
        } else {
            setBackground(table.getBackground());
        }
        return this;
    }
}
