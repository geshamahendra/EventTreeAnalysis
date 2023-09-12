import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

public class DrawingCanvas extends Canvas {
    int JumlahEvent;
    int wSection;
    int hSection;
    int hSection2;
    int hTopLabel;
    int hBotLabel;
    int hTopSection;
    int realEvent;
    int wtextbox;
    String InitSystem;
    JTextField[] NamaSistemMitigasi;
    JTextField[] inputProbabilitas;

    int w; int h;
    int x1; int x2;
    int y1; int y2;
    int yinputProbabilitas;

    public DrawingCanvas(int jumlahEvent, String initSystem, JTextField[] namaSistemMitigasi, JTextField[] inputProbabilitas, JFrame frame3){
        NamaSistemMitigasi = namaSistemMitigasi;
        JumlahEvent = jumlahEvent;
        InitSystem = initSystem;
        inputProbabilitas = inputProbabilitas;
    }

    public void setJlhW(int jlhW) {
        w = jlhW;
    }

    public void setJlhH(int jlhH) {
        h = jlhH;
    }

    public void setJlhWSection(int jlhW) {
        wSection = jlhW;
    }

    public void setJlhHSection(int jlhH) {
        hSection = jlhH;
    }

    public void setHTobLabel(int jlHTL) {
        hTopLabel = jlHTL;
    }

    public void setHBotLabel(int jlHBL) {
        hBotLabel = jlHBL;
    }

    public void setHTopSection(int jlHTS) {
        hTopSection = jlHTS;
    }
    public void setJlhREvent(int jlHRE) { realEvent = jlHRE;
    }
    public void setWTextBox(int JLHTB) {
        wtextbox = JLHTB;
    }
    public void setInitSystem(String initSystem) {
        InitSystem = initSystem;
    }

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

    public void drawSection(Graphics g, Graphics2D g2d){
        int w2 = w - wtextbox;
        int yTittle = hBotLabel + 5;

        g2d.setFont(new Font("Arial", Font.PLAIN,12));
        g2d.drawString(InitSystem, 0, hBotLabel);

        int xTittle = wSection;

        for (JTextField jTextField : NamaSistemMitigasi) {
            g2d.drawString(jTextField.getText(), xTittle, hBotLabel);
            xTittle = xTittle + wSection;
        }

        g.drawLine(w, hTopLabel, 0, hTopLabel);
        g.drawLine(w, yTittle, 0, yTittle);

        //inisialisasi kursor
        hSection2 = hSection;
        y1 = hTopSection;
        y2 = y1 + hSection2;
        x1 = w2 - wSection;
        x2 = w2;
        yinputProbabilitas = y1;

        if (JumlahEvent < 3) {
            for (JTextField jTextField : inputProbabilitas) {
                g2d.drawString(jTextField.getText(), w2 + 3, yinputProbabilitas + 3);
                //xTittle = xTittle + wSection;
                yinputProbabilitas = yinputProbabilitas + hSection2;
            }
            for (int i = 1; i <= JumlahEvent; i++) {
                for (int j = 0; j < (int)(Math.pow(2, JumlahEvent - i)); j++) {
                    g.drawLine(x1, y1, x2, y1);
                    g.drawLine(x1, y1, x1, y2);
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
            //h = (hTopLabel + hBotLabel + hTopSection) + (hSection * (int) (Math.pow(2, jumlahEvent))) / 2;
            y1 = hTopSection + hSection2 / 3;
            x1 = 0;
            x2 = w2 / realEvent;
            g.drawLine(x1, y1, x2, y1);
        } else {
            for (JTextField jTextField : inputProbabilitas) {
                g2d.drawString(jTextField.getText(), w2 + 3, yinputProbabilitas + 3);
                //xTittle = xTittle + wSection;
                yinputProbabilitas = yinputProbabilitas + hSection2;
            }
            for (int i = 1; i <= JumlahEvent; i++) {
                for (int j = 0; j < (int)(Math.pow(2, JumlahEvent - i)); j++) {
                    g.drawLine(x1, y1, x2, y1);
                    g.drawLine(x1, y1, x1, y2);
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
            //h = (hTopLabel + hBotLabel + hTopSection) + (hSection * (int) (Math.pow(2, jumlahEvent))) / 2;
            y1 = hBotLabel + hSection2 / 2;
            x1 = 0;
            x2 = w2 / realEvent;
            g.drawLine(x1, y1, x2, y1);
        }
    }
}