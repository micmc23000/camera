package opencvcam;

import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;


public class CameraVideoProcessManager {

	final int cameraDeviceNumber=0;
	VideoCapture videoCapture;
	VectorDataImageGenerator vectorDataImageGenerator;
	
	CameraVideoProcessManager(JPanel displayJPanel) {
		vectorDataImageGenerator = new MatVectorDataImageGeneratorV6(displayJPanel);
		videoCapture = new VideoCapture();
		videoCapture.open(0);
	}
	BufferedImage getOneFrame() {
		Mat imageData = new Mat();
		videoCapture.read(imageData);
		return vectorDataImageGenerator.getImage(imageData);
	}
}