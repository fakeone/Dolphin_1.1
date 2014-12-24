package service;

import javax.swing.*;
import java.io.*;

public class ReadBaseFileTXT {

    private String line;
    private BufferedReader fin;
    private static Base base = new Base();

    // Метод читает информацию из папки С://DolphinBase/.
    // В самой папке находятся картинки элементов.
    // В файле Base.txt находится информация о элементах.
    public void readFileBase() throws IOException {

        File f = new File("/DolphinBase/Base.txt"); // Загружаем базу из Base.txt.
        try {
            fin = new BufferedReader(new FileReader(f)); // Читаем все стоки файла в буфер.
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Отсутствует файл с базой " + ".\n"+
                    "Программа будет закрыта, добавьте файл с базой C://DolphinBase/Base.txt и перезапустите программу.",
                    "Файл не найден!",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        while ((line = fin.readLine()) != null) {
            Element elem = new Element(); // Создаем элемент.
            elem.setName(line); // Записываем название элемента.
            line = fin.readLine(); // Читаем следующую строку.
            elem.setCode(line); // Записываем код элемента.
            line = fin.readLine(); // Читаем следующую строку.
            elem.setPrice(Double.parseDouble(line)); // Записываем цену элемента.
            //elem.setImage("/DolphinBase/" + elem.getName() + ".jpg"); // Записываем картинку элемента.
            base.addElemInBase(elem); // Добавляем элемент в базу.
        }
        fin.close(); // Закрываем буфер
    }

    public Base getFullBase() {
        return base;
    }
}

