package rayTracing7;

public class HollowDisk extends Obj {
	
	public Vect x;
	public Vect n;
	public float r0;
	public float r1;
	
	public Col col;
	
	public HollowDisk(Vect x, Vect n, float r0, float r1, Col col) {
		
		this.x = x;
		this.n = Vect.normalize(n);
		this.r0 = r0;
		this.r1 = r1;
		
		this.col = col;
		
	}
	
	@Override
	public HitReturn hit(Ray r) {
		
		Vect l0 = r.o;
		Vect l = r.d;
		Vect p0 = this.x;
		
		float t = -Vect.dot(Vect.subtract(l0, p0), this.n) / Vect.dot(l, this.n);
		
		Vect p = Vect.add(l0, Vect.scale(l, t));
		
		float d = Vect.mag(Vect.subtract(p, p0));
		
		boolean hit = r0 <= d && d <= r1;
		t = hit ? t : Float.POSITIVE_INFINITY;
		
		return new HitReturn(hit, t, this.col);
		
	}

}
