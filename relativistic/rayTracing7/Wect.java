package rayTracing7;

public class Wect {
	
	public float x;
	public float y;
	public float z;
	public float w;
	
	public Wect(float x, float y, float z, float w) {
		
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		
	}
	
	public static Wect add(Wect a, Wect b) {
		
		float x = a.x + b.x;
		float y = a.y + b.y;
		float z = a.z + b.z;
		float w = a.w + b.w;
		
		return new Wect(x, y, z, w);
		
	}

	public static Wect subtract(Wect a, Wect b) {
		
		float x = a.x - b.x;
		float y = a.y - b.y;
		float z = a.z - b.z;
		float w = a.w - b.w;
		
		return new Wect(x, y, z, w);
		
	}
	
	public static Wect scale(Wect a, float s) {
		
		float x = a.x * s;
		float y = a.y * s;
		float z = a.z * s;
		float w = a.w * s;
		
		return new Wect(x, y, z, w);
		
	}
	
	public static float dot(Wect a, Wect b) {
		
		return a.x * b.x + a.y * b.y + a.z * b.z + a.w * b.w;
		
	}
	
	public static float mag(Wect a) {
		
		return (float) Math.sqrt(dot(a, a));
		
	}
	
	public static Wect normalize(Wect a) {
		
		return scale(a, 1f / mag(a));
		
	}
	
	public static Wect normal(float dfdx, float dfdy, float dfdz) {
		
		return new Wect(-dfdx, -dfdy, -dfdz, 1);
		
	}
	
	public static Vect toVect(Wect a) {
		
		return new Vect(a.x, a.y, a.z);
		
	}

}
