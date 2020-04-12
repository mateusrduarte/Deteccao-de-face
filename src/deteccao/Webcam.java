package deteccao;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Webcam extends JFrame{
    private JPanel painelMain;

    public Webcam() {
        initComponents();
    }

    private void initComponents() {

        painelMain = new JPanel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GroupLayout jPanel1Layout = new GroupLayout(painelMain);
        painelMain.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 653, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 420, Short.MAX_VALUE)
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(painelMain, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(72, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(painelMain, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }

    public static void main(String[] args) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Webcam janela = new Webcam();
        janela.setDefaultCloseOperation(EXIT_ON_CLOSE);
        janela.setSize(600, 500);
        janela.setVisible(true);
        janela.mostrarVideos();
    }

    public void mostrarVideos() {

        Mat video = new Mat();
        VideoCapture captura = new VideoCapture(0);

        if (captura.isOpened()) {
            while (true) {
                captura.read(video);
                if (!video.empty()) {
                    setSize(video.width() + 50, video.height() + 70);
                    BufferedImage imagem = new Utilitarios().convertMatToImage(video);
                    Graphics g = painelMain.getGraphics();
                    g.drawImage(imagem, 0, 0, imagem.getWidth(), imagem.getHeight(), null);
                }
            }
        }
    }
}
