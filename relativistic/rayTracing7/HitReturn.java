package rayTracing7;

public class HitReturn {
	
	public boolean hit = false;
	public float t = Float.POSITIVE_INFINITY;
	public Col col = new Col(51,51,51);
	
	public HitReturn() {}
	
	public HitReturn(boolean hit, float t, Col col) {
		
		this.hit = hit;
		this.t = t;
		this.col = col;
		
	}

}
