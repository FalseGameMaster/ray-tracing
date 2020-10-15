package rayTracing7;

import java.awt.Color;

public class Col {
	
	public float r;
	public float g;
	public float b;
	
	public Col(float r, float g, float b) {
		
		this.r = r;
		this.g = g;
		this.b = b;
		
	}
	
	public static Col add(Col a, Col c) {
		
		float r = a.r + c.r;
		float g = a.g + c.g;
		float b = a.b + c.b;
		
		return new Col(r, g, b);
		
	}
	
	public static Col scale(Col a, float s) {
		
		float r = a.r * s;
		float g = a.g * s;
		float b = a.b * s;
		
		return new Col(r, g, b);
		
	}
	
	public static Col clamp(Col a) {
		
		float r = a.r;
		float g = a.g;
		float b = a.b;
		
		r = r < 0 ? 0 : r;
		g = g < 0 ? 0 : g;
		b = b < 0 ? 0 : b;
		
		r = r > 255 ? 255 : r;
		g = g > 255 ? 255 : g;
		b = b > 255 ? 255 : b;
		
		return new Col(r, g, b);
		
	}
	
	public static Col gammaCorrect(Col a, float gamma) {
		
		float r = a.r / 255f;
		float g = a.g / 255f;
		float b = a.b / 255f;
		
//		double scale = 1.0 / samples;
		
		r = (float) Math.pow(r, 1f / gamma);
		g = (float) Math.pow(g, 1f / gamma);
		b = (float) Math.pow(b, 1f / gamma);
		
//		System.out.println(r);
		
		return new Col(255 * r, 255 * g, 255 * b);
		
	}
	
	public static Color toColor(Col a, float gamma) {
		
		Col c = clamp(gammaCorrect(a, gamma));
		
		int r = (int) c.r;
		int g = (int) c.g;
		int b = (int) c.b;
		
		return new Color(r, g, b);
		
	}
	
	public static Col toCol(Color a) {
		
		float r = a.getRed();
		float g = a.getGreen();
		float b = a.getBlue();
		
		return new Col(r, g, b);
		
	}
	
	public static Col mult(Col a, Col c) {
		
		float r = a.r * c.r / 255f;
		float g = a.g * c.g / 255f;
		float b = a.b * c.b / 255f;
		
		return new Col(r, g, b);
		
	}

}
