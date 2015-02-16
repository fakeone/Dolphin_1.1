package view;

import importer.IgnoreCodesReader;
import importer.SchemeFileParser;
import model.*;
import service.*;
import util.FileUtil;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class MainWindow extends JFrame {

    private static String information = "Миссия компании OOO «ДОЛФИН» - максимальное удовлетворение потребностей заказчиков \n" +
            "при высоком уровне ответственности и профессионализма.\n" +
            "\n" +
            "Почему мы?\n" +
            "Все просто! При работе с нами вы получаете такие преимущества как:\n" +
            "* Богатый выбор оборудования монтируемого на детские игровые и спортивные площадки;\n" +
            "* Мобильность и скорость выполнения заказов на детские площадки;\n" +
            "* Безопасность игровых комплексов детской площадки;\n" +
            "* Мы сможем учесть особенности именно вашего объекта и подобрать то оборудование, \n" +
            "что соответствует вашим желаниям и возможностям;\n" +
            "* Мы быстро реагируем на любые изменения и охотно воплощаем перспективные новинки;\n" +
            "* Наша компания непрерывно расширяет свой ассортимент продукции (качели, детские горки, \n" +
            "игровые комплексы, спортивные и детские площадки).\n" +
            "Преимущества наших детских площадок - они изготовлены из экологического сырья и полностью безопасны,\n" +
            "обладают привлекательным дизайном игровых элементов и радуют доступной ценой! \n" +
            "\n" +
            "С нашей компанией легко работать!\n" +
            "* У нас есть художники и дизайнеры, они помогут смоделировать детские площадки с учетом особенностей \n" +
            "вашего ландшафта. Или создадут индивидуальный дизайн.\n" +
            "* Высококвалифицированные специалисты с легкостью осуществят монтаж сложных конструкций на детской площадке.\n" +
            "* Мы поможем осуществить комплексное проектирование детской игровой зоны и полностью оснастить ее \n" +
            "необходимым оборудованием.\n" +
            "* Мы легко сможем доставить в короткие сроки наши детские и спортивно игровые площадки в любой уголок \n" +
            "нашей страны. \n" +
            "Профессиональный подход нашей команды разносторонних специалистов отличает нашу компанию и позволяет \n" +
            "предоставлять услуги высокого уровня. Инженеры и разработчики проявили максимум фантазии, а также \n" +
            "тщательно проработали каждую деталь игровых элементов в соответствии с параметром надежности.\n" +
            "\n" +
            "Радость для наших детей и их родителей - наше главное желание. Давайте дарить радость вместе!";

    private static int count = 0;
    private static Playground playground = new Playground();
    private static JTextField JTFFilter = new JTextField();
    private static final Font font = new Font("Verdana", Font.PLAIN, 11);
    private static final Font font2 = new Font("Verdana", Font.PLAIN, 18);

    //UI
    private static JPanel panel;
    private static JTable firstTable;
    private static JTable secondTable;
    private static JScrollPane sPane;
    private static JScrollPane sPane2 = new JScrollPane();
    private static JLabel fullPrise;

    //Model
    private static MyFirstTableModel firstTableModel;
    private static MySecondTableModel secondTableModel;

    private static BaseExcel baseExcel;
    private static final String rootFolder = FileUtil.getRootFolder();

    private static String excelFile = rootFolder + "/Price.xls"; //TODO calculate relative path
    public static final String dolphinImage = rootFolder + "/1dolphin.png";


    public static void createGUI() throws IOException {


        // Загружаем базу из файла.
        //TODO fix paths
        baseExcel = new BaseExcel();
        baseExcel.readBaseExcel(excelFile);
        baseExcel.readBaseExcel(rootFolder + "/MODPrice.xls");

        // Создаем panel и делаем разметку.
        panel = new JPanel();
        BorderLayout layout = new BorderLayout();
        panel.setLayout(layout);

        // Создаем первую таблицу с базой.
        createFistTableAndModel();
        panel.add(sPane, BorderLayout.CENTER);
        // Поиск элементов в таблице с базой.
        panel.add(JTFFilter, BorderLayout.SOUTH);
        panel.remove(sPane2);
        sPane2 = new JScrollPane(new JTable());//TODO is it needed?
        fullPrise = new JLabel("", JLabel.CENTER);
        fullPrise.setFont(font2);
        panel.add(fullPrise, BorderLayout.NORTH);
        panel.add(sPane2, BorderLayout.EAST);
        panel.updateUI();

        // Создаем главное окно.
        final MainWindow frame = new MainWindow();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Теперь окно можно закрыть и программа завершится.
        frame.setTitle("Dolphin - детские площадки!"); // Заголовок окна.
        // Задаем картинку заголовка главного окна.
        Image im = Toolkit.getDefaultToolkit().getImage(dolphinImage);
        frame.setIconImage(im);
        // Создаем меню.
        JMenuBar menuBar = createMenu(frame);
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(panel);
        frame.setPreferredSize(new Dimension(1320, 700));
        frame.pack(); // Для того что бы все элементы влезли в окно.
        frame.setLocationRelativeTo(null);   //окно появляется в центре экрана, а не слева
        frame.setVisible(true);
    }

    private static void createFistTableAndModel() {
        firstTableModel = new MyFirstTableModel(baseExcel.getBaseList(), excelFile);
        firstTable = new JTable(firstTableModel);
        // Сортировка по названию.
        final TableRowSorter<MyFirstTableModel> sorter = new TableRowSorter<>(firstTableModel);
        JTFFilter.getDocument().addDocumentListener(new Search(JTFFilter, sorter));
        firstTable.setRowSorter(sorter);
        firstTable.setRowHeight(80);
        firstTable.setDefaultRenderer(Object.class, new FirstTableRenderer());

        sPane = new JScrollPane(firstTable);
        // Обновление таблицы площадки при любом клике мышкой.
        firstTable.getSelectionModel().addListSelectionListener(
                new FirstTableListener());
        sPane.updateUI();
    }

    private static JMenuBar createMenu(final MainWindow frame) {
        JMenuBar menuBar = new JMenuBar(); // Создаем бар меню.
        JMenu menu = new JMenu("Меню"); // Создаем элемент меню.
        menu.setFont(font); // Применяем шрифт.

        JMenuItem openPrice = createOpenPriceMenuItem();
        menu.add(openPrice);
        JMenuItem openScheme = createOpenSchemeMenuItem();
        menu.add(openScheme);
        menu.addSeparator();
        JMenuItem exitItem = createExitMenuItem();
        menu.add(exitItem);

        JMenu file = new JMenu("Сохранить в файл");
        file.setFont(font);

        JMenuItem pdf = createPdfMenuItem();
        file.add(pdf);

        JMenu info = new JMenu("Информация");
        info.setFont(font);

        JMenuItem about = new JMenuItem("О нас");
        about.setFont(font);
        info.add(about);
        about.addActionListener(e -> JOptionPane.showMessageDialog(frame, information));

        menuBar.add(menu);
        menuBar.add(file);
        menuBar.add(info);
        return menuBar;
    }

    private static JMenuItem createOpenSchemeMenuItem() {
        JMenuItem openScheme = new JMenuItem("Открыть площадку");
        openScheme.setFont(font);
        openScheme.addActionListener(l -> {
            String schemeFile = selectSchemeFile();
            //if file was chosen
            if (schemeFile != null) {

                List<String> ignoreCodes = IgnoreCodesReader.getIgnoreCodes();
                SchemeFileParser parser = new SchemeFileParser(ignoreCodes, baseExcel);
                try {
                    Playground schemePlayground = parser.
                            parseFile(schemeFile);//TODO pass path as parameter

//                secondTable = new MySecondTableModel(schemePlayground.getpGroundList());
                    // Создаем вторую таблицу для площадки и выводим на экран.
                    secondTableModel = new MySecondTableModel(schemePlayground.getElements());
                    createSecondTable();
                    secondTable.setModel(secondTableModel);
                    panel.remove(sPane2);
                    sPane2 = new JScrollPane(secondTable);
                    //TODO count total price
                    String text = "Общая цена площадки: " + schemePlayground.getPlayGroundPrise() + "грн.";
                    fullPrise.setText(text);
                    sPane.updateUI();
                    panel.add(sPane2, BorderLayout.EAST);
                    panel.updateUI();

                    //TODO replace original playground?
                    playground = schemePlayground;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return openScheme;
    }

    private static String selectSchemeFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(rootFolder + "/schemes"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("playground scheme or scene file (*.scheme, *.scene)", "scheme", "scene");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(panel);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        }
        return null;
    }

    private static JMenuItem createOpenPriceMenuItem() {
        JMenuItem openPrice = new JMenuItem("Открыть прайс");
        openPrice.setFont(font);
        openPrice.addActionListener(e -> {
                    try {
                        Runtime.getRuntime().exec("cmd /C " + FileUtil.getRootFolder() + "/Price.xls");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
        );
        return openPrice;
    }

    private static JMenuItem createPdfMenuItem() {
        JMenuItem pdf = new JMenuItem("PDF");
        pdf.setFont(font);
        pdf.addActionListener(e -> {
                    final JFrame frame2 = new JFrame();
                    Panel panel2 = new Panel();
                    GridLayout gridLayout = new GridLayout(5, 2);
                    panel2.setLayout(gridLayout);
                    frame2.setTitle("Информация для PDF файла."); // Заголовок окна.

                    panel2.add(new Label("Адрес доставки:"));
                    final JTextField addressField = new JTextField();
                    addressField.setText("Самовывоз");
                    panel2.add(addressField);
                    panel2.add(new Label("Цена доставки (грн.):"));
                    final JTextField priseSheepField = new JTextField();
                    panel2.add(priseSheepField);
                    priseSheepField.setText("0");
                    panel2.add(new Label("Скидка (%):"));
                    final JTextField discount = new JTextField();
                    discount.setText("0");
                    panel2.add(discount);
                    panel2.add(new Label("Монтаж (%):"));
                    final JTextField workPrice = new JTextField();
                    workPrice.setText("15");
                    panel2.add(workPrice);
                    JButton ok = new JButton("Ок");
                    panel2.add(ok);
                    ok.addActionListener(event -> {
                        playground.setPosition(addressField.getText());
                        playground.setPriceDelivery(Double.parseDouble(priseSheepField.getText()));
                        playground.setDiscount(Double.parseDouble(discount.getText()));
                        playground.setWorkPricePercent(Integer.parseInt(workPrice.getText()));

                        final CreateFile createFile = new CreateFile(playground);
                        createFile.createPDF();
                        frame2.setVisible(false);
                    });
                    JButton cancel = new JButton("Отмена");
                    panel2.add(cancel);
                    cancel.addActionListener(ev -> frame2.setVisible(false));

                    frame2.getContentPane().add(panel2);
                    frame2.setPreferredSize(new Dimension(450, 150));
                    frame2.pack();
                    frame2.setLocationRelativeTo(null);
                    frame2.setVisible(true);
                }
        );
        return pdf;
    }

    private static JMenuItem createExitMenuItem() {
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setFont(font);
        // для закрытия программы из меню.
        exitItem.addActionListener(e -> System.exit(0));
        return exitItem;
    }

    private static void createSecondTable() {
        secondTable = new JTable(secondTableModel);
        secondTable.addFocusListener(new SecondTableFocusListener());
        TableRowSorter<MySecondTableModel> sorter = new TableRowSorter<>(secondTableModel);
        secondTable.setRowSorter(sorter);
        secondTable.setRowHeight(80);
    }


    private static class FirstTableListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent event) {
            List<Element> chosenElements = new ArrayList<Element>();
            // Выбираем элементы в таблице, количество которых не равно нулю.
            List<Element> firstTableModelElements = firstTableModel.getElements();

            while (firstTableModelElements.size() > count) {
                Element element = firstTableModelElements.get(count);
                if (element.getCount() > 0) {
                    chosenElements.add(element);
                    //baseTable.getCellRenderer(count,5).getTableCellRendererComponent(baseTable, 0, false, false, 5, 1).setBackground(Color.BLUE);
                    //baseTable.getColumnModel().getColumn(5).setCellRenderer(renderer);
                }

                count++;
            }

            count = 0;
            // Добавляем элементы с количеством больше нуля в площадку.
            playground.setElements(chosenElements);
            displaySecondTable();
        }

        private void displaySecondTable() {
            // Создаем вторую таблицу для площадки и выводим на экран.
            secondTableModel = new MySecondTableModel(playground.getElements());
            createSecondTable();
            panel.remove(sPane2);
            sPane2 = new JScrollPane(secondTable);
            panel.remove(fullPrise);
            fullPrise = new JLabel("Общая цена площадки: " + Double.toString(playground.getPlayGroundPrise()) + "грн.", JLabel.CENTER);
            fullPrise.setFont(font2);
            panel.add(fullPrise, BorderLayout.NORTH);
            sPane.updateUI();
            panel.add(sPane2, BorderLayout.EAST);
            panel.updateUI();
        }


    }

    private static class SecondTableFocusListener implements FocusListener {

        @Override
        public void focusGained(FocusEvent e) {
            JTFFilter.setText("");
            if (secondTable.getSelectedRow() != -1) {
                int response = JOptionPane.showConfirmDialog(null, "Вы действительно хотите убрать элемент из площадки?");
                if (response == 0) {
                    String value = (String) secondTable.getValueAt(secondTable.getSelectedRow(), MyFirstTableModel.CODE_COLUMN);

                    for (int i = 0; i <= firstTableModel.getElements().size() - 1; i++) {
                        if (value == firstTableModel.getValueAt(i, MyFirstTableModel.CODE_COLUMN)) {
                            firstTableModel.setValueAt(0, i, MyFirstTableModel.COUNT_COLUMN);
                            secondTableModel.removeRow(secondTable.getSelectedRow());
                        }
                    }

//                                    TableRowSorter<MySecondTableModel> sorter2 = new TableRowSorter<MySecondTableModel>(secondTable);
//                                    playGroundTable.setRowSorter(sorter2);
//                                    playGroundTable.setRowHeight(80);
                    panel.remove(sPane2);
                    sPane2 = new JScrollPane(secondTable);
                    panel.remove(fullPrise);
                    fullPrise = new JLabel("Общая цена площадки: " + Double.toString(playground.getPlayGroundPrise()) + "грн.", JLabel.CENTER);
                    fullPrise.setFont(font2);
                    panel.add(fullPrise, BorderLayout.NORTH);

                    sPane.updateUI();
                    panel.add(sPane2, BorderLayout.EAST);
                    panel.updateUI();
                    firstTable.requestFocusInWindow();
                } else {
                    firstTable.requestFocusInWindow();
                }
            }
        }

        @Override
        public void focusLost(FocusEvent e) {

        }

    }

}


