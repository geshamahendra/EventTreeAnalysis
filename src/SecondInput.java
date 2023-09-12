import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

public class SecondInput {
    int JumlahEvent;
    String InitSystem;
    JTextField[] namaSistemMitigasi;
    JTextField[] inputProbFail;
    JFrame frame2;
    JFrame Frame;
    double ProbInit;

        public SecondInput(int jumlahEvent, String initSystem, JFrame frame, double probabilitasInit) {
        //Ambil variabel dari input sebelumnya
        JumlahEvent = jumlahEvent;
        InitSystem = initSystem;
        ProbInit = probabilitasInit;
        Frame = frame;

        int JumlahSection = (int)(Math.pow(2, JumlahEvent));

        //Inisialisasi Variabel probabilitas
        double[] ProbFail = new double[jumlahEvent];
        double[] ProbSucces = new double[jumlahEvent];
        double[] nilaiProbabilitas = new double[JumlahSection];
        int jumlahSection = (int)(Math.pow(2, JumlahEvent));

        //Inisialisasi Frame input kedua
        JPanel panel = new JPanel();
        frame2 = new JFrame("Insert Probability Fail");

        panel.setLayout(new GridLayout(JumlahEvent + 3, 6, 5, 5));
        panel.setPreferredSize(new Dimension(60 * 6, 30 * (jumlahEvent + 3)));

        //Membuat loop untuk menyediakan Jtextfield
        JLabel[] labelSistemMitigasi = new JLabel[jumlahEvent];
        JLabel[] labelProbabilitas = new JLabel[jumlahEvent];
        namaSistemMitigasi = new JTextField[jumlahEvent];
        inputProbFail = new JTextField[jumlahEvent];
        int count = 1;

        for (int j = 0; j < 6; j++) {
            panel.add(new JLabel());
        }
        for (int i = 0; i < jumlahEvent; i++) {
            panel.add(new JLabel());
            labelSistemMitigasi[i] = new JLabel("Sistem - " + count);
            panel.add(labelSistemMitigasi[i]);
            namaSistemMitigasi[i] = new JTextField("");
            panel.add(namaSistemMitigasi[i]);
            namaSistemMitigasi[i].getText();

            labelProbabilitas[i] = new JLabel("Probabilitas - " + count);
            panel.add(labelProbabilitas[i]);
            inputProbFail[i] = new JTextField("");
            panel.add(inputProbFail[i]);
            panel.add(new JLabel());
            count++;
        }

        //Masukkan JButton
        JButton btnGambar = new JButton("OK");
        JButton btnKembali = new JButton("KEMBALI");

        //Masukkan tombol
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(btnGambar);
        panel.add(btnKembali);
        panel.add(new JLabel());
        panel.add(new JLabel());

        for (int k = 0; k < 6; k++) {
            panel.add(new JLabel());
        }

        // Menambahkan panel ke JScrollPane
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(500, 500));
        scrollPane.setViewportView(panel);

        // Masukkan scrollpane ke frame
        frame2.add(scrollPane);
        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame2.setSize(700, 500);
        frame2.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    Frame.setVisible(true);
                }
            });
        frame2.setVisible(true);

        //Masukkan Event Listener
        btnGambar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Inisialisasi String Kemungkinan gagal dan sukses
                String[] StringProbFail = new String[jumlahEvent];
                String[] StringProbSucces = new String[jumlahEvent];

                for (int j = 0; j < jumlahEvent; j++) {
                    ProbFail[j] = Double.parseDouble(inputProbFail[j].getText());
                    StringProbFail[j] = String.format("%1.2E", (ProbFail[j]));
                    ProbSucces[j] = 1 - ProbFail[j];
                    StringProbSucces[j] = String.format("%1.2E", (ProbSucces[j]));
                }

                // Mengambil variabel biner menjadi susunan probabilitas yang diinginkan
                double prob = ProbInit;
                char ch;

                String[] Probabilitas = new String[jumlahSection];
                for (int k = 0; k < jumlahSection; k++) {
                    String Result = toBinary(k, jumlahEvent);
                    //System.out.println("Biner : " + Result);

                    for (int l = 0; l < (Objects.requireNonNull(Result).length()); l++) {
                        ch = Result.charAt(l);
                        if (ch == '0') {
                            prob = prob * ProbSucces[l];
                        } else {
                            prob = prob * ProbFail[l];
                        }
                    }
                    nilaiProbabilitas[k] = prob;
                    String ProbString = String.format("%1.2E", (prob));
                    Probabilitas[k] = ProbString;
                    prob = ProbInit;
                }
                String StringProbInit = String.format("%1.2E", (ProbInit));

                new JavaDrawCanvas(jumlahEvent, initSystem, namaSistemMitigasi, frame2, StringProbFail, StringProbSucces, Probabilitas, StringProbInit, nilaiProbabilitas, jumlahSection);

                frame2.setVisible(false);
            }
        });
        btnKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(true);
                frame2.dispose();
            }
        });
    }
    public static String toBinary(int x, int len)
    {
        if (len > 0)
        {
            return String.format("%" + len + "s",
                    Integer.toBinaryString(x)).replaceAll(" ", "0");
        }
        return null;
    }
}
