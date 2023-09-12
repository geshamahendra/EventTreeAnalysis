import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private JTextField tfJumlahEvent;
    private JButton btnOK;
    private JButton btnHapus;
    private JButton btnKeluar;
    private JPanel InputMain;
    private JTextField tfInitiatingEvent;
    private JTextField tfProbabilitasInitEvent;

    static JFrame frame;
    int jumlahEvent;
    String initSystem;
    double probabilitasInit;

    public static void main(String[] args) {
        frame = new JFrame("Event Tree Analysis");
        frame.add(new Main().InputMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(450, 200);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public Main() {
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mengambil input
                jumlahEvent = Integer.parseInt(tfJumlahEvent.getText());
                initSystem = tfInitiatingEvent.getText();
                probabilitasInit = Double.parseDouble(tfProbabilitasInitEvent.getText());

                if (jumlahEvent == 0) {
                    JOptionPane.showMessageDialog(frame, "Input tidak boleh sama dengan 0");
                } else {
                    frame.setVisible(false);
                    new SecondInput(jumlahEvent, initSystem, frame, probabilitasInit);
                }
                frame.setVisible(false);
            }
        });
        btnHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfJumlahEvent.setText("");
                tfInitiatingEvent.setText("");
                tfJumlahEvent.requestFocus();
            }
        });
        btnKeluar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
