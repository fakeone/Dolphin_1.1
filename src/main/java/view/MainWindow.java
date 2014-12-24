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

    private static String helpInfo = "По всем вопросам обращаться по телефону (066) 093-19-00 Дмитрий";
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

    private static JScrollPane sPane2 = new JScrollPane();
    private static int count = 0;
    private static Playground playground = new Playground();
    private static JTextField JTFFilter = new JTextField();
    private static JLabel fullPrise = new JLabel();
    private static final Font font = new Font("Verdana", Font.PLAIN, 11);
    private static final Font font2 = new Font("Verdana", Font.PLAIN, 18);

    public static void createGUI() throws IOException {

        // Создаем главное окно.
            final MainWindow frame = new MainWindow();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Теперь окно можно закрыть и программа завершится.
            frame.setTitle("Dolphin - детские площадки!"); // Заголовок окна.
        // Задаем картинку заголовка главного окна.
            Image im=Toolkit.getDefaultToolkit().getImage("/DolphinBase/1dolphin.png");
            frame.setIconImage(im);
        // Создаем panel и делаем разметку.
            final JPanel panel = new JPanel();
            BorderLayout layout = new BorderLayout();
            panel.setLayout(layout);
        // Загружаем базу из файла.
            BaseExcel baseExcel = new BaseExcel();
            baseExcel.readBaseExcel();

//            final ReadBaseFileTXT readBase = new ReadBaseFileTXT();
//            readBase.readFileBase();
        // Создаем первую таблицу с базой.
//            final MyFirstTableModel myFirstTableModel = new MyFirstTableModel(readBase.getFullBase().getBaseList());
            final MyFirstTableModel myFirstTableModel = new MyFirstTableModel(baseExcel.getBase().getBaseList());
            final JTable baseTable = new JTable(myFirstTableModel);
        // Сортировка по названию.
            final TableRowSorter<MyFirstTableModel> sorter = new TableRowSorter<MyFirstTableModel>(myFirstTableModel);
            baseTable.setRowSorter(sorter);
            baseTable.setRowHeight(80);
            final JScrollPane sPane = new JScrollPane(baseTable);
            panel.add(sPane, BorderLayout.CENTER);
        // Поиск элементов в таблице с базой.
            panel.add(JTFFilter, BorderLayout.SOUTH);
            JTFFilter.getDocument().addDocumentListener(new Search(JTFFilter, sorter));
        // Меняем шрифт.

        // Создаем меню.
            JMenuBar menuBar = new JMenuBar(); // Создаем бар меню.
            JMenu menu = new JMenu("Меню"); // Создаем элемент меню.
            menu.setFont(font); // Применяем шрифт.

            JMenuItem addElement = new JMenuItem("Добавить элемент в базу");
            addElement.setFont(font);
            menu.add(addElement);
            // addElement.addMenuKeyListener();

            JMenuItem delElement = new JMenuItem("Удалить элемент из базы");
            delElement.setFont(font);
            menu.add(delElement);

        // Обновление таблицы площадки при любом клике мышкой.
            baseTable.getSelectionModel().addListSelectionListener(
                new ListSelectionListener() {
                    public void valueChanged(ListSelectionEvent event) {

                        ArrayList<Element> chouseElem = new ArrayList<Element>();
                        // Выбираем элементы в таблице, количество которых не равно нулю.
                        while (myFirstTableModel.getElements().size() > count) {
                            if (myFirstTableModel.getElements().get(count).getCount() > 0) {
                                chouseElem.add(myFirstTableModel.getElements().get(count));
                            }
                            count++;
                        }
                        count = 0;
                        // Добавляем элементы с количеством больше нуля в площадку.
                        playground.setpGroundList(chouseElem);
                        // Создаем вторую таблицу для площадки и выводим на экран.
                        MySecondTableModel secondTable = new MySecondTableModel(playground.getpGroundList());
                        JTable playGroundTable = new JTable(secondTable);
                        TableRowSorter<MySecondTableModel> sorter2 = new TableRowSorter<MySecondTableModel>(secondTable);
                        playGroundTable.setRowSorter(sorter2);
                        playGroundTable.setRowHeight(80);
                        panel.remove(sPane2);
                        sPane2 = new JScrollPane(playGroundTable);
                        panel.remove(fullPrise);
                        fullPrise = new JLabel("Общая цена площадки: " + Double.toString(playground.getPlayGroundPrise()) + "грн.", JLabel.CENTER);
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
            // для закрытия программы из меню.
            exitItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            JMenu file = new JMenu("Сохранить в файл");
            file.setFont(font);

            final CreateFile createFile = new CreateFile(playground, count);

            JMenuItem txt = new JMenuItem("TXT");
            txt.setFont(font);
            file.add(txt);
            txt.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    createFile.сreateTXT();
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
                    /* ДОБАВИТЬ окно для параметров:
                         - скидка;
                         - цена установки;
                         - цена доставки;
                         - адрес доставки;
                        */
                    createFile.createPDF();
                }
            });

            JMenu info = new JMenu("Информация");
            info.setFont(font);

            JMenuItem help = new JMenuItem("Помощь");
            help.setFont(font);
            info.add(help);
            help.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame, helpInfo);
                }
            });

            JMenuItem about = new JMenuItem("О нас");
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
            frame.pack(); // Для того что бы все элементы влезли в окно.
            frame.setLocationRelativeTo(null);   //окно появляется в центре экрана, а не слева
            frame.setVisible(true);
        }

}

