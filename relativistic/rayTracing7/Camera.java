package rayTracing7;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Camera {
	
	public Vect x;
	public Vect d;
	public Vect vup = new Vect(0,1,0);
	
	public Camera(Vect x, Vect d) {
		
		this.x = x;
		this.d = Vect.normalize(d);
		
		Ray.init();
		
	}
	
	public Vect getI() {
		
		Vect i = Vect.cross(this.d, this.vup);
		
		return Vect.normalize(i);
		
	}
	
	public Vect getJ() {
		
		Vect j = Vect.cross(this.d, getI());
		
		return Vect.normalize(j);
		
	}
	
	public void show(Graphics g, int w, int h, int scale) throws IOException {
		
		int l = Math.min(w, h);
		
		int samples = 50;
		
		BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		
		File f = null;
		
		for (int j = 0; j < h / scale; j ++) {
			
			for (int i = 0; i < w / scale; i ++) {
				
				float x = scale * (float) (i + Math.random()) / l - 0.5f * (float) w / l;
				float y = scale * (float) (j + Math.random()) / l - 0.5f * (float) h / l;
				
//				float x = scale * (float) i / l - 0.5f * (float) w / l;
//				float y = scale * (float) j / l - 0.5f * (float) h / l;
				
				Vect X = Vect.scale(getI(), -x);
				Vect Y = Vect.scale(getJ(), y);
				
				Vect dir = Vect.add(Vect.add(X, Y), this.d);
				
				Ray r = new Ray(this.x, dir, 0.1f);
				
				r.hit(100);
				
//				int c = (int) (255 * Math.random());
				
//				Color color = Col.toColor(r.HR.col, 2f);
				
				Col col = r.HR.col;
				
				for (int s = 1; s < samples; s ++) {
					
					x = scale * (float) (i + Math.random()) / l - 0.5f * (float) w / l;
					y = scale * (float) (j + Math.random()) / l - 0.5f * (float) h / l;
					
					X = Vect.scale(getI(), -x);
					Y = Vect.scale(getJ(), y);
					
					dir = Vect.add(Vect.add(X, Y), this.d);
					
					r = new Ray(this.x, dir, 0.1f);
					
					r.hit(100);
					
//					int c = (int) (255 * Math.random());
					
//					Color color = Col.toColor(r.HR.col, 2f);
					
					col = Col.add(col, r.HR.col);
					
				}
				
				col = Col.scale(col, 1f / samples);
				
				Color c = Col.toColor(col, 1f);
				
//				int R = (int) col.r;
//				int G = (int) col.g;
//				int B = (int) col.b;
//				
//				Color c = new Color(R, G, B);
				
				g.setColor(c);
				g.fillRect(i * scale, j * scale, scale, scale);
				
				img.setRGB(i, j, c.getRGB());
				
			}
			
		}
		
		try {
			
			f = new File("src\\rayTracing7\\" + Integer.toString(Main.F) + ".png");
			ImageIO.write(img, "png", f);
			
		} catch (IOException e) {}
		
	}

}
