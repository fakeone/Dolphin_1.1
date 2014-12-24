import view.MainWindow;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainWindow.createGUI(); // ��������� ���������.
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
