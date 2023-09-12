import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ThirdInput {
    String InitSystem;
    String[] Probabilitas;
    JTextField[] NamaSistemMitigasi;
    String[] ProbFail;
    String[] ProbSucces;
    String ProbInit;
    int JumlahSection;
    int JumlahEvent;
    double[] NilaiProbabilitas;
    JFrame frame4 = new JFrame("Selection End State");
    JFrame Frame3;
    JFrame Frame2;

    public ThirdInput(int jumlahEvent, String initSystem, JTextField[] namaSistemMitigasi, JFrame frame2, String[] stringProbFail, String[] stringProbSucces, String[] probabilitas, String stringProbInit, double[] nilaiProbabilitas, JFrame frame3, int jumlahSection) {
        //Ambil input dari sebelumnya
        JumlahEvent = jumlahEvent;
        InitSystem = initSystem;
        Probabilitas = probabilitas;
        NamaSistemMitigasi = namaSistemMitigasi;
        ProbFail = stringProbFail;
        ProbSucces = stringProbSucces;
        ProbInit = stringProbInit;
        NilaiProbabilitas = nilaiProbabilitas;
        Frame2 = frame2;
        Frame3 = frame3;
        JumlahSection = jumlahSection;

        //Inisialisasi JFrame, JLabel, dan JPanel untuk Container dan JRadioButton
        JPanel panel = new JPanel();
        @SuppressWarnings("MismatchedReadAndWriteOfArray") JLabel[] labelSistem = new JLabel[JumlahSection];

        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridLayout(JumlahSection + 3, 5, 5, 5));
        panel.setPreferredSize(new Dimension(60 * 6, 30 * (JumlahSection + 3)));

        //Jscrollpane
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(panel);
        scrollPane.setPreferredSize(new Dimension(500, 500));

        frame4.add(scrollPane);
        frame4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame4.setSize(500, 500);
        frame4.setLocationRelativeTo(null);
        frame4.setVisible(true);
        frame4.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame2.setVisible(true);
            }
        });

        //Inisialisasi JRadioButton dan ButtonGroup
        JRadioButton[] Ok = new JRadioButton[JumlahSection];
        JRadioButton[] Rusak = new JRadioButton[JumlahSection];
        ButtonGroup[] btnSystem = new ButtonGroup[JumlahSection];

        //Inisialisasi JButton Konfirmasi
        JButton btnOk = new JButton("OK");
        JButton btnKembali = new JButton("Kembali");

        for (int j = 0; j < 5; j++) {
            panel.add(new JLabel());
        }
        int countLabel = 1;
        for (int i = 0; i < JumlahSection; i++) {
            panel.add(new JLabel());
            panel.add(labelSistem[i] = new JLabel("End State ke - " + countLabel));

            btnSystem[i] = new ButtonGroup();
            Ok[i] = new JRadioButton("OK");
            Ok[i].setSelected(true);
            Rusak[i] = new JRadioButton("Rusak");
            btnSystem[i].add(Ok[i]);
            btnSystem[i].add(Rusak[i]);

            panel.add(Ok[i]);
            panel.add(Rusak[i]);
            panel.add(new JLabel());
            countLabel++;
        }
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(btnOk);
        panel.add(btnKembali);
        panel.add(new JLabel());

        for (int l = 0; l < 3; l++) {
            panel.add(new JLabel());
        }

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                double JumlahProbFail = 0;
                String[] sEndState = new String[jumlahSection];

                for (int i = 0; i < JumlahSection; i++) {
                    if (Rusak[i].isSelected()) {
                        JumlahProbFail = JumlahProbFail + NilaiProbabilitas[i];
                        sEndState[i] = "Rusak";
                    } else {
                        sEndState[i] = "OK";
                    }
                    System.out.println(sEndState[i]);
                }
                String sJumlahProbFail = String.format("%1.2E", (JumlahProbFail));

                new FinalDrawJava(JumlahEvent, InitSystem, ProbInit, NamaSistemMitigasi, ProbFail, ProbSucces, NilaiProbabilitas, frame4, JumlahSection, sEndState, sJumlahProbFail, frame3, Probabilitas);

                frame4.setVisible(false);
                Frame3.setVisible(false);
            }
        });
        btnKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame4.dispose();
                Frame3.dispose();
                Frame2.setVisible(true);
            }
        });
    }
}
