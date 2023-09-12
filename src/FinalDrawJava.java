import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FinalDrawJava {
    // Inisialisasi variable frame
    int wSection = 150; int hSection = 50;
    int hTopLabel = 5; int hBotLabel = 30;
    int hTopSection = 90;
    int wtextbox = 100; int wstatebox = 100;
    int hPanelBot = 30;

    int JumlahEvent;
    int hSection2;
    int realEvent;
    int w; int h;
    int x1; int x2;
    int y1; int y2;
    int yProbabilitas;

    String InitSystem;
    String ProbInit;
    JTextField[] NamaSistemMitigasi;
    String[] ProbFail;
    String[] ProbSucces;
    double[] NilaiProbabilitas;
    JFrame Frame4;
    JFrame Frame3;
    int JumlahSection;
    String[] SEndState;
    String SJumlahProbFail;

    JFrame frame5 = new JFrame("Final Drawing");
    MyPanel panel = new MyPanel();
    String[] Probabilitas;

    public FinalDrawJava(int jumlahEvent, String initSystem, String probInit, JTextField[] namaSistemMitigasi, String[] probFail, String[] probSucces, double[] nilaiProbabilitas, JFrame frame4, int jumlahSection, String[] sEndState, String sJumlahProbFail, JFrame frame3, String[] probabilitas) {
        JumlahEvent = jumlahEvent;
        InitSystem = initSystem;
        ProbInit = probInit;
        NamaSistemMitigasi = namaSistemMitigasi;
        ProbFail = probFail;
        ProbSucces = probSucces;
        NilaiProbabilitas = nilaiProbabilitas;
        Frame4 = frame4;
        Frame3 = frame3;
        JumlahSection = jumlahSection;
        SEndState = sEndState;
        SJumlahProbFail = sJumlahProbFail;
        Probabilitas = probabilitas;

        // Menentukan besar canvas
        realEvent = JumlahEvent + 1;
        w = (realEvent * wSection) + wtextbox + wstatebox;
        h = (hTopLabel + hBotLabel + hTopSection + hPanelBot) + (hSection * (int)(Math.pow(2, JumlahEvent)));

        //Inisialisasi Panel dan JFrame
        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(500, 500));
        scrollPane.setViewportView(panel);

        frame5.add(scrollPane);
        frame5.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame5.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame5.dispose();
                Frame4.setVisible(true);
                Frame3.setVisible(true);
            }
        });
        frame5.setSize(400, 300);
        frame5.setVisible(true);

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

        int w2 = w - (wtextbox + wstatebox);
        int w3 = w - wstatebox;
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
                g2d.drawString(SEndState[k], w3 + 3, yProbabilitas + 3);
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
            g.drawString(ProbInit, x1 + 3, y1 - 3);

            g.drawLine(panel.getWidth(),h - hPanelBot, 0, h - hPanelBot);
            g2d.drawString("Jumlah Probabilitas Kegagalan = " + SJumlahProbFail, 10, h - 6);
            g.drawLine(panel.getWidth(),h, 0, h);
        } else {
            for (int l = 0; l < JumlahSection; l++) {
                g2d.drawString(SEndState[l], w3 +  3, yProbabilitas + 3);
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
        g.drawString(ProbInit, x1 + 3, y1 - 3);

        g.drawLine(panel.getWidth(),h - hPanelBot, 0, h - hPanelBot);
        g2d.drawString("Jumlah Probabilitas Kegagalan = " + SJumlahProbFail, 10, h - 6);
        g.drawLine(panel.getWidth(), h, 0, h);
    }
}
