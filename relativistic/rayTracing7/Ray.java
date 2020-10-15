package rayTracing7;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ray {
	
	public Vect o;
	public Vect d;
	
	public Wect wo;
	public Wect wd;
	
	public float step;
	public float wstep;
	
	public int sign = 1;
	
	public static File file1;
	public static File file2;
	
	public static BufferedImage img1 = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
	public static BufferedImage img2 = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
	
	public static int w1;
	public static int h1;
	
	public static int w2;
	public static int h2;
	
//	public boolean crossed;
	
	public static BH bh0 = new BH(new Vect(0.0f,0,0), 2f, 1f, new Vect(0,1,0), 0.0f);
//	public static BH bh1 = new BH(new Vect(0.75f,0,0), 2f, 1f, new Vect(0,1,0), 0.0f);
//	public static BH bh2 = new BH(new Vect(0.0f,-0.5f,0), 2f, 1f, new Vect(0,1,0), 0.0f);
//	public static BH bh3 = new BH(new Vect(0.0f,0.5f,0), 2f, 1f, new Vect(0,1,0), 0.0f);
	
//	public static WH wh0 = new WH(new Vect(0f,0,0), 1f, 1f);
	
	public HitReturn HR = new HitReturn();
	
	public static void init() {
		
		file1 = new File("src\\rayTracing7\\Oceanside1.jpeg");
		file2 = new File("src\\rayTracing7\\Pier1.jpeg");
		
		ImageIO.setUseCache(false);
		try { img1 = ImageIO.read(file1); } catch (IOException e) {}
		try { img2 = ImageIO.read(file2); } catch (IOException e) {}
		
		w1 = img1.getWidth() - 1;
		h1 = img1.getHeight() - 1;
		
		w2 = img2.getWidth() - 1;
		h2 = img2.getHeight() - 1;
		
	}
	
	public Ray(Vect o, Vect d, float wstep) {
		
		this.step = wstep;
		this.wstep = wstep;
		
		this.o = o;
		this.d = Vect.normalize(d);
		
		this.wo = Vect.toWect(o, BH.F(o));
		
//		System.out.println(this.wo.x + "," + this.wo.y + "," + this.wo.z);
		
		Wect x = Wect.scale(BH.px(o), this.d.x);
		Wect y = Wect.scale(BH.py(o), this.d.y);
		Wect z = Wect.scale(BH.pz(o), this.d.z);
		
		Wect dir = Wect.add(Wect.add(x, y), z);
		
		this.wd = Wect.scale(Wect.normalize(dir), this.step);
		
//		this.crossed = -0.01f < 1f / BH.DFDX(this.o) && 1f / BH.DFDX(this.o) < 0.01f;
//		this.crossed = 0f == 1f / BH.DFDX(this.o);
		
	}
	
	public boolean crossed() {
		
//		return 0f == 1f / BH.DFDX(this.o);
		
		return false;
		
	}
	
	public float f(Vect x) { return crossed() ? -BH.F(x) : BH.F(x); }
	
	public float dfdx(Vect x) { return crossed() ? -BH.DFDX(x) : BH.DFDX(x); }
	public float dfdy(Vect x) { return crossed() ? -BH.DFDY(x) : BH.DFDY(x); }
	public float dfdz(Vect x) { return crossed() ? -BH.DFDZ(x) : BH.DFDZ(x); }
	
	public Wect px(Vect x) {
		
		Wect px = BH.px(x);
		Wect pxCrossed = Vect.toWect(Vect.scale(Wect.toVect(px), -1), px.w);
		
		return crossed() ? pxCrossed : px;
		
	}
	
	public Wect py(Vect x) {
		
		Wect py = BH.py(x);
		Wect pyCrossed = Vect.toWect(Vect.scale(Wect.toVect(py), -1), py.w);
		
		return crossed() ? pyCrossed : py;
		
	}
	
	public Wect pz(Vect x) {
		
		Wect pz = BH.pz(x);
		Wect pzCrossed = Vect.toWect(Vect.scale(Wect.toVect(pz), -1), pz.w);
		
		return crossed() ? pzCrossed : pz;
		
	}
	
	public Wect normal(Vect x) { return new Wect(-dfdx(x), -dfdy(x), -dfdz(x), 1); }
	
	public void hit(int depth) {
		
		if (depth > 0) {
			
			HitReturn HR = new HitReturn();
			HR.t = step;
			
			for (Obj o : Obj.objects) {
				
				HitReturn tempHR = o.hit(this);
				
				HR = 0 < tempHR.t && tempHR.t < HR.t ? tempHR : HR;
				
			}
			
			if (!HR.hit) { step(depth - 1); hit(depth - 1); }
			
			else { this.HR = HR; }
			
		}
		
	}
	
	public void step(int depth) {
		
//		if (Float.isNaN(Wect.mag(this.wo))) {
//			
//			System.out.println(this.wo.x + "," + this.wo.y + "," + this.wo.z);
//			
//		}
//		
//		Vect u1 = BH.getU(Wect.toVect(this.wo));
//		Wect wu1 = Vect.toWect(u1, 0);
		
//		System.out.println(wu1.x + "," + wu1.y + "," + wu1.z);
		
		// Update 4D position
//		Wect nextStep = Wect.add(Wect.add(this.wd, this.wo), wu1);
		Wect nextStep = Wect.add(this.wd, this.wo);
//		Wect nextWO = Vect.toWect(Wect.toVect(nextStep), sign * BH.F(Wect.toVect(nextStep)));
		
//		if (!Float.isNaN(nextWO.x) && !Float.isNaN(nextWO.y) && !Float.isNaN(nextWO.z) && Float.isNaN(nextWO.w)) {
//			
//			sign *= -1;
//			
//			nextWO = new Wect(wo.x, wo.y, wo.z, -wo.w);
//			
//			this.wo = nextWO;
//			
//			Wect nextWD = new Wect(-wd.x, -wd.y, -wd.z, wd.w);
//			
//			this.wd = nextWD;
//			
//		} else {
			
//		this.wo = Vect.toWect(Wect.toVect(nextStep), BH.F(Wect.toVect(nextStep)));
		this.wo = nextStep;
		
		// Update 4D direction
//		Wect normal = BH.normal(Wect.toVect(this.wo));
		Wect normal = BH.normal(this.wo);
//		normal = new Wect(normal.x, normal.y, normal.z, sign * normal.w);
		Wect projectedToNormal = Wect.scale(normal, Wect.dot(this.wd, normal));
		Wect projectedToPlane = Wect.subtract(this.wd, projectedToNormal);
		this.wd = Wect.scale(Wect.normalize(projectedToPlane), this.wstep);
			
//		}
		
		// Update 3D position
		Vect oPrev = this.o;
		this.o = Wect.toVect(this.wo);
		
		// Update 3D direction
		Vect dTemp = Vect.subtract(this.o, oPrev);
//		this.d = Vect.mag(Wect.toVect(this.wd)) == 0 ? this.d : Vect.normalize(dTemp);
		this.d = Vect.normalize(dTemp);
		this.step = Vect.mag(dTemp);
		
		// Update 4D position and direction if past the event horizon
//		this.wo.w = crossed ? -this.wo.w : this.wo.w;
//		Wect wdCrossed = Vect.toWect(Vect.scale(Wect.toVect(this.wd), -1), this.wd.w);
//		this.wd = crossed() ? wdCrossed : this.wd;
		
		// A simple sky box
		
		float theta = (float) Math.acos(this.d.x);
		
		Col checker = (float) Math.sin(100 * theta / Math.PI) + (float) Math.sin(100 * (this.d.y + 1)) > 0 ? new Col(51, 51, 51) : new Col(0,0,0);
		
//		this.HR.col = checker;
		
		// A more complicated sky box
		
		// Galaxy 2
//		float u1 = w1 * (float) (Math.acos(this.d.x) / Math.PI);
		float u1 = w1 * (float) (Math.acos(this.d.x) / (2 * Math.PI));
		float v1 = h1 * (-this.d.y + 1) / 2f;
		
		int U1 = u1 < 0 ? 0 : (int) u1;
		U1 = u1 > w1 ? w1 : U1;
		
		int V1 = v1 < 0 ? 0 : (int) v1;
		V1 = v1 > h1 ? h1 : V1;
		
		int c1 = img1.getRGB(U1, V1);
		Color color1 = new Color(c1);
		
		// Galaxy
		float u2 = w2 * (float) (Math.acos(this.d.x) / (2 * Math.PI));
		float v2 = h2 * (-this.d.y + 1) / 2f;
		
		int U2 = u2 < 0 ? 0 : (int) u2;
		U2 = u2 > w2 ? w2 : U2;
		
		int V2 = v2 < 0 ? 0 : (int) v2;
		V2 = v2 > h2 ? h2 : V2;
		
		int c2 = img2.getRGB(U2, V2);
		Color color2 = new Color(c2);
		
		Col col = wo.w < 0 ? Col.toCol(color2) : Col.toCol(color1);
//		Col col = wo.w < 0 ? new Col(0,0,0) : Col.toCol(color1);
//		Col col = wo.w < 0 ? Col.toCol(color1) : new Col(0,0,0);
//		Col col = wo.w < 0 ?  Col.toCol(color2) : checker;
		
//		this.HR.col = Col.toCol(color);
//		this.HR.col = depth == 0 ? Col.toCol(color) : this.HR.col;
		this.HR.col = depth == 0 ? col : new Col(0,0,0);
//		this.HR.col = crossed() ? checker : this.HR.col;
		
//		this.o = Vect.add(this.o, Vect.scale(this.d, this.step));
		
//		this.wd = new Wect(0,0,0,this.wd.z); // Turns black when 3D direction is 0 in magnitude
		
	}

}
