package rayTracing7;

public class Vect {
	
	public float x;
	public float y;
	public float z;
	
	public Vect(float x, float y, float z) {
		
		this.x = x;
		this.y = y;
		this.z = z;
		
	}
	
	public static Vect add(Vect a, Vect b) {
		
		float x = a.x + b.x;
		float y = a.y + b.y;
		float z = a.z + b.z;
		
		return new Vect(x, y, z);
		
	}
	
	public static Vect subtract(Vect a, Vect b) {
		
		float x = a.x - b.x;
		float y = a.y - b.y;
		float z = a.z - b.z;
		
		return new Vect(x, y, z);
		
	}

	public static Vect scale(Vect a, float s) {
		
		float x = a.x * s;
		float y = a.y * s;
		float z = a.z * s;
		
		return new Vect(x, y, z);
		
	}
	
	public static float dot(Vect a, Vect b) {
		
		return a.x * b.x + a.y * b.y + a.z * b.z;
		
	}
	
	// 23-31-12
	public static Vect cross(Vect a, Vect b) {
		
		float x = a.y * b.z - a.z * b.y;
		float y = a.z * b.x - a.x * b.z;
		float z = a.x * b.y - a.y * b.x;
		
		return new Vect(x, y, z);
		
	}
	
	public static float mag(Vect a) {
		
		return (float) Math.sqrt(dot(a, a));
		
	}
	
	public static Vect normalize(Vect a) {
		
		return scale(a, 1f / mag(a));
		
	}
	
	public static Wect toWect(Vect a, float w) {
		
		return new Wect(a.x, a.y, a.z, w);
		
	}

}
