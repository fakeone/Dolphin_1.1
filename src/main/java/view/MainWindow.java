package view;

import service.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.ArrayList;

public class MainWindow extends JFrame {

    private static String helpInfo = "�� ���� �������� ���������� �� �������� (066) 093-19-00 �������";
    private static String information = "������ �������� OOO ������ͻ - ������������ �������������� ������������ ���������� \n" +
            "��� ������� ������ ��������������� � ����������������.\n" +
            "\n" +
            "������ ��?\n" +
            "��� ������! ��� ������ � ���� �� ��������� ����� ������������ ���:\n" +
            "* ������� ����� ������������ ������������ �� ������� ������� � ���������� ��������;\n" +
            "* ����������� � �������� ���������� ������� �� ������� ��������;\n" +
            "* ������������ ������� ���������� ������� ��������;\n" +
            "* �� ������ ������ ����������� ������ ������ ������� � ��������� �� ������������, \n" +
            "��� ������������� ����� �������� � ������������;\n" +
            "* �� ������ ��������� �� ����� ��������� � ������ ��������� ������������� �������;\n" +
            "* ���� �������� ���������� ��������� ���� ����������� ��������� (������, ������� �����, \n" +
            "������� ���������, ���������� � ������� ��������).\n" +
            "������������ ����� ������� �������� - ��� ����������� �� �������������� ����� � ��������� ���������,\n" +
            "�������� ��������������� �������� ������� ��������� � ������ ��������� �����! \n" +
            "\n" +
            "� ����� ��������� ����� ��������!\n" +
            "* � ��� ���� ��������� � ���������, ��� ������� ������������� ������� �������� � ������ ������������ \n" +
            "������ ���������. ��� �������� �������������� ������.\n" +
            "* ����������������������� ����������� � ��������� ���������� ������ ������� ����������� �� ������� ��������.\n" +
            "* �� ������� ����������� ����������� �������������� ������� ������� ���� � ��������� ��������� �� \n" +
            "����������� �������������.\n" +
            "* �� ����� ������ ��������� � �������� ����� ���� ������� � ��������� ������� �������� � ����� ������ \n" +
            "����� ������. \n" +
            "���������������� ������ ����� ������� �������������� ������������ �������� ���� �������� � ��������� \n" +
            "������������� ������ �������� ������. �������� � ������������ �������� �������� ��������, � ����� \n" +
            "��������� ����������� ������ ������ ������� ��������� � ������������ � ���������� ����������.\n" +
            "\n" +
            "������� ��� ����� ����� � �� ��������� - ���� ������� �������. ������� ������ ������� ������!";

    private static JScrollPane sPane2 = new JScrollPane();
    private static int count = 0;
    private static Playground playground = new Playground();
    private static JTextField JTFFilter = new JTextField();
    private static JLabel fullPrise = new JLabel();
    private static final Font font = new Font("Verdana", Font.PLAIN, 11);
    private static final Font font2 = new Font("Verdana", Font.PLAIN, 18);
    private static MySecondTableModel secondTable = new MySecondTableModel();
    private static JTable playGroundTable = new JTable();
    public static final String dolphinImage = "/DolphinBase/1dolphin.png";

    public static void createGUI() throws IOException {

        // ������� ������� ����.
            final MainWindow frame = new MainWindow();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ������ ���� ����� ������� � ��������� ����������.
            frame.setTitle("Dolphin - ������� ��������!"); // ��������� ����.
        // ������ �������� ��������� �������� ����.
            Image im =Toolkit.getDefaultToolkit().getImage(dolphinImage);
            frame.setIconImage(im);

        // ������� panel � ������ ��������.
            final JPanel panel = new JPanel();
            BorderLayout layout = new BorderLayout();
            panel.setLayout(layout);
        // ��������� ���� �� �����.
            BaseExcel baseExcel = new BaseExcel();
            baseExcel.readBaseExcel("/DolphinBase/Price.xls");
            baseExcel.readBaseExcel("/DolphinBase/MODPrice.xls");
        // ������� ������ ������� � �����.
            final MyFirstTableModel myFirstTableModel = new MyFirstTableModel(baseExcel.getBaseList());
            final JTable baseTable = new JTable(myFirstTableModel);
        // ���������� �� ��������.
            final TableRowSorter<MyFirstTableModel> sorter = new TableRowSorter<MyFirstTableModel>(myFirstTableModel);
            baseTable.setRowSorter(sorter);
            baseTable.setRowHeight(80);
            final JScrollPane sPane = new JScrollPane(baseTable);
            panel.add(sPane, BorderLayout.CENTER);
        // ����� ��������� � ������� � �����.
            panel.add(JTFFilter, BorderLayout.SOUTH);
            JTFFilter.getDocument().addDocumentListener(new Search(JTFFilter, sorter));
        // ������� ����.
            JMenuBar menuBar = new JMenuBar(); // ������� ��� ����.
            JMenu menu = new JMenu("����"); // ������� ������� ����.
            menu.setFont(font); // ��������� �����.

        panel.remove(sPane2);
        sPane2 = new JScrollPane(playGroundTable);
        panel.remove(fullPrise);
        panel.add(fullPrise, BorderLayout.NORTH);
        sPane.updateUI();
        panel.add(sPane2, BorderLayout.EAST);
        panel.updateUI();


//        baseTable.setDefaultRenderer(TableColumn.class, new DefaultTableCellRenderer() {
//            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//                // value - ��������, ���. �������� � ������
//                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//                if ((Integer)value > 0) {
//                    component.setBackground(Color.red);
//                }
//                return component;
//            }
//        });

//        final DefaultTableCellRenderer renderer = new DefaultTableCellRenderer(){
//            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
//                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//                //if(column == 5) {
//                    if((Integer)value > 0){
//                        cell.setBackground(Color.RED);
//                    }
//                //}
//                return cell;
//            }
//        };
//        for(int i=0; i<baseTable.getColumnCount(); i++){
//
//        }

        // ���������� ������� �������� ��� ����� ����� ������.
        baseTable.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
                    public void valueChanged(ListSelectionEvent event) {
                        ArrayList<Element> chouseElem = new ArrayList<Element>();
                        // �������� �������� � �������, ���������� ������� �� ����� ����.
                        while (myFirstTableModel.getElements().size() > count) {
                            if (myFirstTableModel.getElements().get(count).getCount() > 0) {
                                chouseElem.add(myFirstTableModel.getElements().get(count));
                               //baseTable.getCellRenderer(count,5).getTableCellRendererComponent(baseTable, 0, false, false, 5, 1).setBackground(Color.BLUE);

                                //baseTable.getColumnModel().getColumn(5).setCellRenderer(renderer);
                            }

                                count++;
                            }

                        count = 0;
                        // ��������� �������� � ����������� ������ ���� � ��������.
                        playground.setpGroundList(chouseElem);
                        // ������� ������ ������� ��� �������� � ������� �� �����.
                        secondTable = new MySecondTableModel(playground.getpGroundList());
                        playGroundTable = new JTable(secondTable);

                        playGroundTable.addFocusListener(new FocusListener() {
                            @Override
                            public void focusGained(FocusEvent e) {
                                JTFFilter.setText("");
                                if (playGroundTable.getSelectedRow() != -1) {
                                    int response = JOptionPane.showConfirmDialog(null, "�� ������������� ������ ������ ������� �� ��������?");
                                    if (response == 0) {
                                        String a = (String) secondTable.getValueAt(playGroundTable.getSelectedRow(), MyFirstTableModel.CODE_COLUMN);

                                        for (int i = 0; i <= myFirstTableModel.getElements().size()-1; i++) {
                                            if (a == baseTable.getValueAt(i, MyFirstTableModel.CODE_COLUMN)) {
                                                baseTable.setValueAt(0, i, MyFirstTableModel.COUNT_COLUMN);
                                                secondTable.removeRow(playGroundTable.getSelectedRow());
                                            }
                                        }

//                                    TableRowSorter<MySecondTableModel> sorter2 = new TableRowSorter<MySecondTableModel>(secondTable);
//                                    playGroundTable.setRowSorter(sorter2);
//                                    playGroundTable.setRowHeight(80);
                                    panel.remove(sPane2);
                                    sPane2 = new JScrollPane(playGroundTable);
                                    panel.remove(fullPrise);
                                    fullPrise = new JLabel("����� ���� ��������: " + Double.toString(playground.getPlayGroundPrise()) + "���.", JLabel.CENTER);
                                    fullPrise.setFont(font2);
                                    panel.add(fullPrise, BorderLayout.NORTH);

                                    sPane.updateUI();
                                    panel.add(sPane2, BorderLayout.EAST);
                                    panel.updateUI();
                                    baseTable.requestFocusInWindow();
                                    } else {
                                        baseTable.requestFocusInWindow();
                                    }
                                }
                            }

                            @Override
                            public void focusLost(FocusEvent e) {
//                                if (playGroundTable.getSelectedRow() != -1) {
////                                    sPane.updateUI();
////                                    panel.updateUI();
//                                }

                            }
                        });

                        TableRowSorter<MySecondTableModel> sorter2 = new TableRowSorter<MySecondTableModel>(secondTable);
                        playGroundTable.setRowSorter(sorter2);
                        playGroundTable.setRowHeight(80);
                        panel.remove(sPane2);
                        sPane2 = new JScrollPane(playGroundTable);
                        panel.remove(fullPrise);
                        fullPrise = new JLabel("����� ���� ��������: " + Double.toString(playground.getPlayGroundPrise()) + "���.", JLabel.CENTER);
                        fullPrise.setFont(font2);
                        panel.add(fullPrise, BorderLayout.NORTH);
                        sPane.updateUI();
                        panel.add(sPane2, BorderLayout.EAST);
                        panel.updateUI();
                    }
                });


            JMenuItem openPrice = new JMenuItem("������� �����");
            openPrice.setFont(font);
            menu.add(openPrice);
            openPrice.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        Runtime.getRuntime().exec("cmd /C " + "/DolphinBase/Price.xls");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            });

        menu.addSeparator();

            JMenuItem exitItem = new JMenuItem("Exit");
            exitItem.setFont(font);
            menu.add(exitItem);
            // ��� �������� ��������� �� ����.
            exitItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            JMenu file = new JMenu("��������� � ����");
            file.setFont(font);

            final CreateFile createFile = new CreateFile(playground, count);


            JMenuItem pdf = new JMenuItem("PDF");
            pdf.setFont(font);
            file.add(pdf);
            pdf.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    final JFrame frame2 = new JFrame();
                    Panel panel2 = new Panel();
                    GridLayout gridLayout = new GridLayout(5,2);
                    panel2.setLayout(gridLayout);
                    frame2.setTitle("���������� ��� PDF �����."); // ��������� ����.

                    panel2.add(new Label("����� ��������:"));
                    final JTextField adressField = new JTextField();
                    adressField.setText("���������");
                    panel2.add(adressField);
                    panel2.add(new Label("���� �������� (���.):"));
                    final JTextField priseSheepField = new JTextField();
                    panel2.add(priseSheepField);
                    priseSheepField.setText("0");
                    panel2.add(new Label("������ (%):"));
                    final JTextField discount = new JTextField();
                    discount.setText("0");
                    panel2.add(discount);
                    panel2.add(new Label("������ (%):"));
                    final JTextField workPrice = new JTextField();
                    workPrice.setText("15");
                    panel2.add(workPrice);
                    JButton ok = new JButton("��");
                    panel2.add(ok);
                    ok.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            playground.setPosition(adressField.getText());
                            playground.setPriceDelivery(Double.parseDouble(priseSheepField.getText()));
                            playground.setDiscount(Double.parseDouble(discount.getText()));
                            playground.setWorkPricePercent(Integer.parseInt(workPrice.getText()));

                            createFile.createPDF();
                            frame2.setVisible(false);
                        }
                    });
                    JButton cancel = new JButton("������");
                    panel2.add(cancel);
                    cancel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            frame2.setVisible(false);
                        }
                    });

                    frame2.getContentPane().add(panel2);
                    frame2.setPreferredSize(new Dimension(450, 150));
                    frame2.pack();
                    frame2.setLocationRelativeTo(null);
                    frame2.setVisible(true);
                }
            });

            JMenu info = new JMenu("����������");
            info.setFont(font);

            JMenuItem about = new JMenuItem("� ���");
            about.setFont(font);
            info.add(about);
            about.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    JOptionPane.showMessageDialog(frame, information);
                }
            });

            menuBar.add(menu);
            menuBar.add(file);
            menuBar.add(info);
            frame.setJMenuBar(menuBar);
            frame.getContentPane().add(panel);
            frame.setPreferredSize(new Dimension(1320, 700));
            frame.pack(); // ��� ���� ��� �� ��� �������� ������ � ����.
            frame.setLocationRelativeTo(null);   //���� ���������� � ������ ������, � �� �����
            frame.setVisible(true);
        }
}

