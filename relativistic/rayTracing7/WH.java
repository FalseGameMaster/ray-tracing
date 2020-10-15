package rayTracing7;

import java.util.ArrayList;
import java.util.List;

public class WH {
	
	// Function describing space-time curvature around a black hole
		public float f(Vect x) {
			
			Vect u = Vect.scale(Vect.subtract(x, o), 1f / r);
			
			float f = -m * (float) Math.sqrt(Math.log(Vect.dot(u, u)));
//			f = r == 0 ? 0 : f;
//			f = f == Float.NaN && m > 0 ? -1 : f;
//			f = f == Float.NaN && m < 0 ? 1 : f;
			
			return f;
			
		}
		
		// Derivative of f with respect to x
		public float dfdx(Vect x) {
			
			Vect u = Vect.scale(Vect.subtract(x, o), 1f / r);
			
			float f = -m * u.x / (Vect.dot(u, u) * (f(x) / -m));
//			f = r == 0 ? 0 : f;
			
			return f;
			
		}
		
		// Derivative of f with respect to y
		public float dfdy(Vect x) {
			
			Vect u = Vect.scale(Vect.subtract(x, o), 1f / r);
			
			float f = -m * u.y / (Vect.dot(u, u) * (f(x) / -m));
//			f = r == 0 ? 0 : f;
			
			return f;
			
		}
		
		// Derivative of f with respect to z
		public float dfdz(Vect x) {
			
			Vect u = Vect.scale(Vect.subtract(x, o), 1f / r);
			
			float f = -m * u.z / (Vect.dot(u, u) * (f(x) / -m));
//			f = r == 0 ? 0 : f;
			
			return f;
			
		}
		
		public static List<WH> whList = new ArrayList<>();
		
		public Vect o;
		public float m;
		public float r;
		
		public WH(Vect o, float m, float r) {
			
			this.o = o;
			this.m = m;
			this.r = r;
			
			whList.add(this);
			
		}
		
		// General function for all black holes in the scene
		public static float F(Vect x) {
			
			float f = 0;
			
			for (WH wh : whList) { f += wh.f(x); }
			
			return f;
			
		}
		
		// Derivative of F with respect to x
		public static float DFDX(Vect x) {
			
			float dfdx = 0;
			
			for (WH wh : whList) { dfdx += wh.dfdx(x); }
			
			return dfdx;
			
		}
		
		// Derivative of F with respect to y
		public static float DFDY(Vect x) {
			
			float dfdy = 0;
			
			for (WH wh : whList) { dfdy += wh.dfdy(x); }
			
			return dfdy;
			
		}
		
		// Derivative of F with respect to z
		public static float DFDZ(Vect x) {
			
			float dfdz = 0;
			
			for (WH wh : whList) { dfdz += wh.dfdz(x); }
			
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
		
		// Distance to the closest white hole
		public static float distance(Vect x) {
			
			float distance = 0;
			
			for (WH wh : whList) {
				
				float tempDistance = Vect.mag(Vect.subtract(wh.o, x));
				
				distance = tempDistance < distance ? tempDistance : distance;
				
			}
			
			return distance;
			
		}

}
