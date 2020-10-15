package rayTracing7;

import java.util.ArrayList;
import java.util.List;

public class BH {
	
	private float baseF(Vect x) {
		
		Vect u = Vect.scale(Vect.subtract(x, o), 1f / r);
		
//		float f = (float) Math.sqrt(Math.log(Vect.dot(u, u)));
		
		float f = (float) Math.sqrt(1f - 1f / Vect.dot(u, u));
		
		return f;
		
	}
	
	// Function describing space-time curvature around a black hole
	public float f(Vect x) {
		
//		Vect u = Vect.scale(Vect.subtract(x, o), 1f / r);
		
		float f = m * baseF(x);
//		f = r == 0 ? 0 : f;
//		f = f == Float.NaN && m > 0 ? -1 : f;
//		f = f == Float.NaN && m < 0 ? 1 : f;
		
		return f;
		
	}
	
	// Derivative of f with respect to x
	public float dfdx(Vect x) {
		
		Vect u = Vect.scale(Vect.subtract(x, o), 1f / r);
		
//		float dfdx = m * u.x / (Vect.dot(u, u) * baseF(x));
		float dfdx = m * u.x / (2 * (float) Math.pow(Vect.dot(u, u), 2) * baseF(x));
//		f = r == 0 ? 0 : f;
		
		return dfdx;
		
	}
	
	// Derivative of f with respect to y
	public float dfdy(Vect x) {
		
		Vect u = Vect.scale(Vect.subtract(x, o), 1f / r);
		
//		float dfdy = m * u.y / (Vect.dot(u, u) * baseF(x));
		float dfdy = m * u.y / (2 * (float) Math.pow(Vect.dot(u, u), 2) * baseF(x));
//		f = r == 0 ? 0 : f;
		
		return dfdy;
		
	}
	
	// Derivative of f with respect to z
	public float dfdz(Vect x) {
		
		Vect u = Vect.scale(Vect.subtract(x, o), 1f / r);
		
//		float dfdz = m * u.z / (Vect.dot(u, u) * baseF(x));
		float dfdz = m * u.z / (2 * (float) Math.pow(Vect.dot(u, u), 2) * baseF(x));
//		f = r == 0 ? 0 : f;
		
		return dfdz;
		
	}
	
	public float dfdx(Wect x) {
		
		Vect x1 = Wect.toVect(x);
		
		Vect u = Vect.scale(Vect.subtract(x1, o), 1f / r);
		
		float dfdx = u.x / (x.w * (float) Math.pow(Vect.dot(u, u), 2));
		
		return dfdx;
		
	}
	
	public float dfdy(Wect x) {
		
		Vect x1 = Wect.toVect(x);
		
		Vect u = Vect.scale(Vect.subtract(x1, o), 1f / r);
		
		float dfdy = u.y / (x.w * (float) Math.pow(Vect.dot(u, u), 2));
		
		return dfdy;
		
	}
	
	public float dfdz(Wect x) {
		
		Vect x1 = Wect.toVect(x);
		
		Vect u = Vect.scale(Vect.subtract(x1, o), 1f / r);
		
		float dfdz = u.z / (x.w * (float) Math.pow(Vect.dot(u, u), 2));
		
		return dfdz;
		
	}
	
	public static List<BH> bhList = new ArrayList<>();
	
	public Vect o;
	public float m;
	public float r;
	public Vect rot;
	public float omega;
	
	public BH(Vect o, float m, float r, Vect rot, float omega) {
		
		this.o = o;
		this.m = m;
		this.r = r;
		this.rot = rot;
		this.omega = omega;
		
		bhList.add(this);
		
	}
	
	// General function for all black holes in the scene
	public static float F(Vect x) {
		
		float f = 0;
		
		for (BH bh : bhList) { f += bh.f(x); }
		
		return f;
		
	}
	
	// Derivative of F with respect to x
	public static float DFDX(Vect x) {
		
		float dfdx = 0;
		
		for (BH bh : bhList) { dfdx += bh.dfdx(x); }
		
		return dfdx;
		
	}
	
	// Derivative of F with respect to y
	public static float DFDY(Vect x) {
		
		float dfdy = 0;
		
		for (BH bh : bhList) { dfdy += bh.dfdy(x); }
		
		return dfdy;
		
	}
	
	// Derivative of F with respect to z
	public static float DFDZ(Vect x) {
		
		float dfdz = 0;
		
		for (BH bh : bhList) { dfdz += bh.dfdz(x); }
		
		return dfdz;
		
	}
	
	// Derivative of F with respect to x
	public static float DFDX(Wect x) {
		
		float dfdx = 0;
		
		for (BH bh : bhList) { dfdx += bh.dfdx(x); }
		
		return dfdx;
		
	}
	
	// Derivative of F with respect to y
	public static float DFDY(Wect x) {
		
		float dfdy = 0;
		
		for (BH bh : bhList) { dfdy += bh.dfdy(x); }
		
		return dfdy;
		
	}
	
	// Derivative of F with respect to z
	public static float DFDZ(Wect x) {
		
		float dfdz = 0;
		
		for (BH bh : bhList) { dfdz += bh.dfdz(x); }
		
		return dfdz;
		
	}
	
	// Vector along the derivative of f with respect to x
	public static Wect px(Vect x) {
		
		Wect px = Wect.normalize(new Wect(1, 0, 0, DFDX(x)));
		float dfdx = DFDX(x);
		px = Float.isNaN(dfdx) || Float.isInfinite(dfdx) ? new Wect(0, 0, 0, -1) : px;
		
		return px;
		
	}
	
	// Vector along the derivative of f with respect to y
	public static Wect py(Vect x) {
		
		Wect py = Wect.normalize(new Wect(0, 1, 0, DFDY(x)));
		float dfdy = DFDY(x);
		py = Float.isNaN(dfdy) || Float.isInfinite(dfdy) ? new Wect(0, 0, 0, -1) : py;
		
		return py;
		
	}
	
	// Vector along the derivative of f with respect to z
	public static Wect pz(Vect x) {
		
		Wect pz = Wect.normalize(new Wect(0, 0, 1, DFDZ(x)));
		float dfdz = DFDZ(x);
		pz = Float.isNaN(dfdz) || Float.isInfinite(dfdz) ? new Wect(0, 0, 0, -1) : pz;
		
		return pz;
		
	}
	
	// Vector normal to the surface
	public static Wect normal(Vect x) {
		
		return Wect.normalize(new Wect(-DFDX(x), -DFDY(x), -DFDZ(x), 1));
		
	}
	
	public static Wect normal(Wect x) {
		
		return Wect.normalize(new Wect(-DFDX(x), -DFDY(x), -DFDZ(x), 1));
		
	}
	
	// Distance to the closest black hole
	public static float distance(Vect x) {
		
		float distance = 0;
		
		for (BH bh : bhList) {
			
			float tempDistance = Vect.mag(Vect.subtract(bh.o, x));
			
			distance = tempDistance < distance ? tempDistance : distance;
			
		}
		
		return distance;
		
	}
	
	// Calculates 'u', the vector accounting for frame dragging
	public static Vect getU(Vect x) {
		
		Vect u = new Vect(0,0,0);
		
		for (BH bh : bhList) {
			
			Vect v = Vect.subtract(bh.o, x);
			
			float squareL = Vect.dot(v, v);
			
			float magU = bh.omega / squareL;
			
			Vect uTangent = Vect.scale(Vect.normalize(Vect.cross(v, bh.rot)), magU);
			
			Vect uSecant = Vect.scale(Vect.normalize(Vect.add(v, uTangent)), Vect.mag(v));
			
			Vect u1 = Vect.scale(Vect.normalize(Vect.cross(v, bh.rot)), magU);
			
//			System.out.println(bh.o.x + "," + bh.o.y + "," + bh.o.z);
			
			u = Vect.add(u, uSecant);
			
		}
		
		return u;
		
	}
	
}
