package rayTracing7;

import java.util.ArrayList;
import java.util.List;

public class Obj {
	
	public static List<Obj> objects = new ArrayList<>();
	
	public Obj() {
		
		objects.add(this);
		
	}
	
	public HitReturn hit(Ray r) {
		
		return new HitReturn();
		
	}

}
