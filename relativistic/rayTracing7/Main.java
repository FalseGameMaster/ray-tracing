package rayTracing7;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Main extends JPanel {
	
	public static JPanel panel = new Main();
	
	public static Camera camera = new Camera(new Vect(-1.0f, 0.0f, -0.3f), new Vect(1,0,-0.6f));
//	public static Camera camera = new Camera(new Vect(-5.0f, 0.0f, -3.0f), new Vect(0,0,1));
	
//	public static Sphere s = new Sphere(new Vect(0f, 0, 0.0f), 0.5f, new Col(255, 255, 255));
	
//	public static HollowDisk hd = new HollowDisk(new Vect(0, 0, 0), new Vect(0, 1, -0.1f), 1.0f, 1.5f, new Col(255, 255, 255));
	
	public void paint(Graphics g) {
		
		super.paint(g);
		
		int w = panel.getWidth();
		int h = panel.getHeight();
		
		try { camera.show(g, w, h, 1); } catch (IOException e) {}
		
	}
	
	public static int F = 0;
	
	public static void main(String[] args) throws InterruptedException {
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel.setPreferredSize(new Dimension(512, 512));
		panel.setBackground(new Color(51, 51, 51));
		frame.add(panel);
		
		frame.pack();
		frame.setVisible(true);
		
//		float dfdx = BH.DFDX(new Vect(1.0f,0,0));
//		
//		if (Float.isNaN(dfdx) || Float.isInfinite(dfdx)) {
//			
//			System.out.println("no u");
//			
//		}
		
//		int frames = 100;
//		
//		float a = -5;
//		
////		float z = hd.n.z;
////		float y = hd.n.y;
//		
//		while (F < frames) {
//			
////			camera.x.z += 0.1;
////			s.x.x += 0.1;
////			Ray.bh0.o.x += 0.1;
////			Ray.bh1.o.x -= 0.1;
////			a += (float) Math.PI / 50f;
////			Ray.bh0.o = new Vect(1.5f * (float) -Math.cos(a),1.5f * (float) -Math.sin(a),0);
////			Ray.bh1.o = new Vect(1.5f * (float) Math.cos(a),1.5f * (float) Math.sin(a),0);
//			
////			float zPrime = z * (float) Math.cos(a) - y * (float) Math.sin(a);
////			float yPrime = z * (float) Math.sin(a) + y * (float) Math.cos(a);
////			
////			hd.n = new Vect(0, yPrime, zPrime);
//			
//			camera.x.x = a;
//			
////			a += (float) Math.PI / 50f;
//			
//			a += 0.1;
//			
//			panel.repaint();
//			
//			F ++;
//			
//			TimeUnit.SECONDS.sleep(15);
//			
//		}
		
	}

}
