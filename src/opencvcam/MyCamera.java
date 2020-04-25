package opencvcam;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

public class MyCamera extends JFrame {
	
	public static LogService LogService = new LogService ();
	
	static {
		System.out.println(Core.NATIVE_LIBRARY_NAME);
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	private JPanel contentPane;
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";


	public int stopper = 4;
	CameraVideoProcessManager videoCap;

	public MyCamera() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		
		videoCap = new CameraVideoProcessManager(this.contentPane);

		setContentPane(contentPane);
		contentPane.setLocale(getLocale());
		new DisplayImageToJPanelThread().start();
		setBounds(0, 0, videoCap.getOneFrame().getWidth() + 15, videoCap.getOneFrame().getHeight() + 35);
		
		int height = videoCap.getOneFrame().getHeight();
		int width = videoCap.getOneFrame().getWidth();
		log("height" + height + "width " + width);
		
		
	}

	private void log(String text) {
		LogService.log(text);
		
	}

	public void paint(Graphics g) {
		g = contentPane.getGraphics();
		g.drawImage(videoCap.getOneFrame(), 0, 0, this);

	}
	

	class DisplayImageToJPanelThread extends Thread {
		@Override
		public void run() {
			for (;;) {
				repaint();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
			}
		}
	}
}

