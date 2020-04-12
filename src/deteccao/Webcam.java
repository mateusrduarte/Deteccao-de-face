package deteccao;

import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
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

                    Mat imagemColorida = video;
                    Mat imagemCinza = new Mat();
                    Imgproc.cvtColor(imagemColorida, imagemCinza, Imgproc.COLOR_BGR2GRAY);

                    CascadeClassifier classificador = new CascadeClassifier("src\\cascades\\haarcascade_frontalface_default.xml");
                    MatOfRect faceDetectada = new MatOfRect();

                    classificador.detectMultiScale(imagemCinza, faceDetectada, 1.1, 1, 0, new Size(150, 150), new Size(500, 500));

                    for (Rect rect : faceDetectada.toArray()) {
                        Imgproc.rectangle(imagemColorida, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255), 2);
                    }

                    BufferedImage imagem = new Utilitarios().convertMatToImage(video);
                    Graphics g = painelMain.getGraphics();
                    g.drawImage(imagem, 0, 0, imagem.getWidth(), imagem.getHeight(), null);
                }
            }
        }
    }
}
