package view;

import service.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public static void createGUI() throws IOException {

        // ������� ������� ����.
            final MainWindow frame = new MainWindow();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ������ ���� ����� ������� � ��������� ����������.
            frame.setTitle("Dolphin - ������� ��������!"); // ��������� ����.
        // ������ �������� ��������� �������� ����.
            Image im=Toolkit.getDefaultToolkit().getImage("/DolphinBase/1dolphin.png");
            frame.setIconImage(im);
        // ������� panel � ������ ��������.
            final JPanel panel = new JPanel();
            BorderLayout layout = new BorderLayout();
            panel.setLayout(layout);
        // ��������� ���� �� �����.
            BaseExcel baseExcel = new BaseExcel();
            baseExcel.readBaseExcel();

//            final ReadBaseFileTXT readBase = new ReadBaseFileTXT();
//            readBase.readFileBase();
        // ������� ������ ������� � �����.
//            final MyFirstTableModel myFirstTableModel = new MyFirstTableModel(readBase.getFullBase().getBaseList());
            final MyFirstTableModel myFirstTableModel = new MyFirstTableModel(baseExcel.getBase().getBaseList());
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
        // ������ �����.

        // ������� ����.
            JMenuBar menuBar = new JMenuBar(); // ������� ��� ����.
            JMenu menu = new JMenu("����"); // ������� ������� ����.
            menu.setFont(font); // ��������� �����.

            JMenuItem addElement = new JMenuItem("�������� ������� � ����");
            addElement.setFont(font);
            menu.add(addElement);
            // addElement.addMenuKeyListener();

            JMenuItem delElement = new JMenuItem("������� ������� �� ����");
            delElement.setFont(font);
            menu.add(delElement);

        // ���������� ������� �������� ��� ����� ����� ������.
            baseTable.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
                    public void valueChanged(ListSelectionEvent event) {

                        ArrayList<Element> chouseElem = new ArrayList<Element>();
                        // �������� �������� � �������, ���������� ������� �� ����� ����.
                        while (myFirstTableModel.getElements().size() > count) {
                            if (myFirstTableModel.getElements().get(count).getCount() > 0) {
                                chouseElem.add(myFirstTableModel.getElements().get(count));
                            }
                            count++;
                        }
                        count = 0;
                        // ��������� �������� � ����������� ������ ���� � ��������.
                        playground.setpGroundList(chouseElem);
                        // ������� ������ ������� ��� �������� � ������� �� �����.
                        MySecondTableModel secondTable = new MySecondTableModel(playground.getpGroundList());
                        JTable playGroundTable = new JTable(secondTable);
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

            JMenuItem txt = new JMenuItem("TXT");
            txt.setFont(font);
            file.add(txt);
            txt.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createFile.�reateTXT();
                }
            });

            JMenuItem doc = new JMenuItem("DOC");
            doc.setFont(font);
            file.add(doc);
            doc.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createFile.createDOC();
                }
            });

            JMenuItem pdf = new JMenuItem("PDF");
            pdf.setFont(font);
            file.add(pdf);
            pdf.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    /* �������� ���� ��� ����������:
                         - ������;
                         - ���� ���������;
                         - ���� ��������;
                         - ����� ��������;
                        */
                    createFile.createPDF();
                }
            });

            JMenu info = new JMenu("����������");
            info.setFont(font);

            JMenuItem help = new JMenuItem("������");
            help.setFont(font);
            info.add(help);
            help.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame, helpInfo);
                }
            });

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
            frame.setPreferredSize(new Dimension(900, 700));
            frame.pack(); // ��� ���� ��� �� ��� �������� ������ � ����.
            frame.setLocationRelativeTo(null);   //���� ���������� � ������ ������, � �� �����
            frame.setVisible(true);
        }

}

