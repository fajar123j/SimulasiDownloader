package tugaspertemuan3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tugaspertemuan3 extends JFrame {

    private final JPanel panel;
    private final JButton startButton;
    private final JProgressBar[] progressBars;

    public Tugaspertemuan3() {
        setTitle("Simulasi Downloader (Multi-thread)");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10));

        startButton = new JButton("Mulai Download");
        panel.add(startButton);

        // 3 progress bar untuk simulasi 3 download paralel
        progressBars = new JProgressBar[3];
        for (int i = 0; i < 3; i++) {
            progressBars[i] = new JProgressBar(0, 100);
            progressBars[i].setStringPainted(true);
            panel.add(progressBars[i]);
        }

        startButton.addActionListener((ActionEvent e) -> {
            for (int i = 0; i < 3; i++) {
                int index = i;
                // Membuat thread untuk setiap download
                Thread downloadThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        simulateDownload(index);
                    }
                });
                downloadThread.start();
            }
        });

        add(panel);
    }

    private void simulateDownload(int index) {
        try {
            for (int i = 0; i <= 100; i++) {
                Thread.sleep(50 + (int)(Math.random() * 50)); // Simulasi waktu download yang berbeda-beda
                progressBars[index].setValue(i);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Tugaspertemuan3().setVisible(true);
        });
    }
}

