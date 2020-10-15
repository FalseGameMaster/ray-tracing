package rayTracing7;

public class Sphere extends Obj {

	public Vect x;
	public float r;
	public Col col;
	
	public Sphere(Vect x, float r, Col col) {
		
		this.x = x;
		this.r = r;
		this.col = col;
		
	}
	
	@Override
	public HitReturn hit(Ray r) {
		
		Vect d = Vect.subtract(this.x, r.o);
		
		float l = Vect.dot(d, r.d);
		
		Vect a = Vect.scale(r.d, l);
		
		float t = (float) Math.sqrt(this.r * this.r - Vect.dot(d, d) + Vect.dot(a, a));
		
		float t1 = l - t;
		
		float b = (float) Math.sqrt(Vect.dot(d, d) - Vect.dot(a, a));
		
		boolean hit = b <= this.r ? true : false;
		
		return new HitReturn(hit, t1, col);
		
	}
	
}
