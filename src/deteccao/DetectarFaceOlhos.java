package deteccao;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import static org.opencv.imgcodecs.Imgcodecs.imread;

public class DetectarFaceOlhos {

    public static void main(String[] args) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        //Inicializando a imagem
        Mat imagemColorida = imread("src\\pessoas\\beatles.jpg");
        Mat imagemCinza = new Mat();
        Imgproc.cvtColor(imagemColorida, imagemCinza, Imgproc.COLOR_BGR2GRAY);

        //Detertar face
        MatOfRect facesDetectadas = new MatOfRect();
        CascadeClassifier classificadorface = new CascadeClassifier("src\\cascades\\haarcascade_frontalface_default.xml");

        //Para melhor resultado os parâmetros do classificador devem ser ajustados para cada imagem.
        classificadorface.detectMultiScale(imagemCinza, facesDetectadas, 1.2, 3, 0, new Size(30, 30), new Size(500, 500));

        for (Rect rect : facesDetectadas.toArray()) {
            Imgproc.rectangle(imagemColorida, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0), 2);
        }

        //Detectar olhos
        MatOfRect olhosDetestados = new MatOfRect();
        CascadeClassifier classificadorolho = new CascadeClassifier("src\\cascades\\haarcascade_eye.xml");

        classificadorolho.detectMultiScale(imagemCinza, olhosDetestados);

        for (Rect rect : olhosDetestados.toArray()) {
            Imgproc.rectangle(imagemColorida, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255), 2);
        }

        Utilitarios ut = new Utilitarios();
        ut.mostraImagem(ut.convertMatToImage(imagemColorida));
    }
}
