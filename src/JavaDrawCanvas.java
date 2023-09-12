import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JavaDrawCanvas {
    // Inisialisasi variable frame
    int wSection = 150; int hSection = 50;
    int hTopLabel = 5; int hBotLabel = 30;
    int hTopSection = 90;
    int wtextbox = 100;
    int JumlahEvent;
    int hSection2;
    int realEvent;
    int w; int h;
    int x1; int x2;
    int y1; int y2;
    int yProbabilitas;

    // Inisialisasi variabel dari input sebelumnya
    String InitSystem;
    String[] Probabilitas;
    JTextField[] NamaSistemMitigasi;
    String[] ProbFail;
    String[] ProbSucces;
    String probInit;

    MyPanel panel = new MyPanel();
    JFrame frame3 = new JFrame("Observe Drawing");
    JFrame Frame2;
    double[] NilaiProbabilitas;
    int JumlahSection;


    public JavaDrawCanvas(int jumlahEvent, String initSystem, JTextField[] namaSistemMitigasi, JFrame frame2, String[] stringProbFail, String[] stringProbSucces, String[] probabilitas, String ProbInit, double[] nilaiProbabilitas, int jumlahSection) {

        //Menarik input dari frame sebelumnya
        NamaSistemMitigasi = namaSistemMitigasi;
        JumlahEvent = jumlahEvent;
        InitSystem = initSystem;
        Probabilitas = probabilitas;
        ProbFail = stringProbFail;
        ProbSucces = stringProbSucces;
        probInit = ProbInit;
        NilaiProbabilitas = nilaiProbabilitas;
        Frame2 = frame2;
        JumlahSection = jumlahSection;

        // Menentukan besar canvas
        realEvent = JumlahEvent + 1;
        w = realEvent * wSection + wtextbox;
        h = (hTopLabel + hBotLabel + hTopSection) + (hSection * (int)(Math.pow(2, JumlahEvent)));

        //Inisialisasi Panel dan JFrame
        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(500, 500));
        scrollPane.setViewportView(panel);

        frame3.add(scrollPane);
        frame3.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame3.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame2.setVisible(true);
            }
        });
        frame3.setSize(400, 300);
        frame3.setVisible(true);

        new ThirdInput(JumlahEvent, InitSystem, NamaSistemMitigasi, Frame2, ProbFail, ProbSucces, Probabilitas, probInit, NilaiProbabilitas, frame3, JumlahSection);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                panel.setPreferredSize(new Dimension(w, h));
                scrollPane.revalidate();
            }
        });
    }

    class MyPanel extends JPanel{
        // Inisialisasi paint
        @Override
        public void paint (Graphics g) {
            super.paint(g);
            Graphics2D g2d = (Graphics2D) g;

            RenderingHints rh = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHints(rh);
            drawSection(g, g2d);
        }
    }

    public void drawSection(Graphics g, Graphics2D g2d){

        int w2 = w - wtextbox;
        int yTittle = hBotLabel + 5;

        g2d.setFont(new Font("Arial", Font.PLAIN,12));
        g2d.drawString(InitSystem, 3, hBotLabel);

        int xTittle = wSection;

        for (JTextField jTextField : NamaSistemMitigasi) {
            g2d.drawString(jTextField.getText(), xTittle + 3, hBotLabel);
            xTittle = xTittle + wSection;
        }

        g.drawLine(panel.getWidth(), hTopLabel, 0, hTopLabel);
        g.drawLine(panel.getWidth(), yTittle, 0, yTittle);

        //inisialisasi kursor
        hSection2 = hSection;
        y1 = hTopSection;
        y2 = y1 + hSection2;
        x1 = w2 - wSection;
        x2 = w2;
        yProbabilitas = y1;

        int place = JumlahEvent;

        if (JumlahEvent < 3) {
            for (int k = 0; k < JumlahSection; k++) {
                g2d.drawString(Probabilitas[k], w2 + 3, yProbabilitas + 3);
                yProbabilitas = yProbabilitas + hSection2;
            }
            for (int i = 1; i <= JumlahEvent; i++) {
                for (int j = 0; j < (int)(Math.pow(2, JumlahEvent - i)); j++) {
                    g.drawString(ProbSucces[place - i], x1 + 3, y1 - 3);
                    g.drawLine(x1, y1, x2, y1);
                    g.drawLine(x1, y1, x1, y2);
                    g.drawString(ProbFail[place - i], x1 + 3, y2 - 3);
                    g.drawLine(x1, y2, x2, y2);
                    y1 = y1 + 2 * hSection2;
                    y2 = y2 + 2 * hSection2;
                }
                y1 = hTopSection + hSection2 / 2;
                hSection2 = hSection2 * 2;
                y2 = y1 + hSection2;
                x1 = x1 - wSection;
                x2 = x2 - wSection;
            }
            y1 = hTopSection + hSection2 / 3;
            g.drawString(probInit, x1 + 3, y1 - 3);
        } else {
            for (int l = 0; l < JumlahSection; l++) {
                g2d.drawString(Probabilitas[l], w2 + 3, yProbabilitas + 3);
                yProbabilitas = yProbabilitas + hSection2;
            }
            for (int i = 1; i <= JumlahEvent; i++) {
                for (int j = 0; j < (int)(Math.pow(2, JumlahEvent - i)); j++) {
                    g.drawString(ProbSucces[place - i], x1 + 3, y1 - 3);
                    g.drawLine(x1, y1, x2, y1);
                    g.drawLine(x1, y1, x1, y2);
                    g.drawString(ProbFail[place - i], x1 + 3, y2 - 3);
                    g.drawLine(x1, y2, x2, y2);
                    y1 = y1 + 2 * hSection2;
                    y2 = y2 + 2 * hSection2;
                }
                y1 = hTopSection + hSection2 / 2;
                hSection2 = hSection2 * 2;
                y2 = y1 + hSection2;
                x1 = x1 - wSection;
                x2 = x2 - wSection;
            }
            y1 = hBotLabel + hSection2 / 2;
        }
        x1 = 0;
        x2 = w2 / realEvent;
        g.drawLine(x1, y1, x2, y1);
        g.drawString(probInit, x1 + 3, y1 - 3);
    }
}
