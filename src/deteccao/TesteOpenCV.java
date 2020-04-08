package deteccao;

import org.opencv.core.Core;
import org.opencv.core.Mat;

import static org.opencv.imgcodecs.Imgcodecs.CV_LOAD_IMAGE_COLOR;
import static org.opencv.imgcodecs.Imgcodecs.imread;

public class TesteOpenCV {

    public static void main(String[] args) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println(Core.VERSION);

        Mat imagemColorida = imread("src\\deteccao\\original.jpg", CV_LOAD_IMAGE_COLOR);
        Utilitarios ut = new Utilitarios();
        ut.mostraImagem(ut.convertMatToImage(imagemColorida));
    }
}
