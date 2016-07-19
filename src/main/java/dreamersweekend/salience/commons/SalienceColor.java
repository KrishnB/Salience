package dreamersweekend.salience.commons;

import java.awt.Color;

public class SalienceColor {
	
	SalienceProperties sProp = new SalienceProperties();
	private int sSalienceColor;
	private String sUserDefinedColor;
	
	public SalienceColor() {
		sUserDefinedColor = sProp.getColor();
		findColor();
	}

	private void findColor() {
		try {
			switch (sUserDefinedColor.toUpperCase()) {
			case Colors.BLACK:
				sSalienceColor = Color.BLACK.getRGB();
				break;

			case Colors.BLUE:
				sSalienceColor = Color.BLUE.getRGB();
				break;
				
			case Colors.CYAN:
				sSalienceColor = Color.CYAN.getRGB();
				break;
			
			case Colors.DARK_GRAY:
				sSalienceColor = Color.DARK_GRAY.getRGB();
				break;
			
			case Colors.GRAY:
				sSalienceColor = Color.GRAY.getRGB();
				break;
			
			case Colors.GREEN:
				sSalienceColor = Color.GREEN.getRGB();
				break;
			
			case Colors.LIGHT_GRAY:
				sSalienceColor = Color.LIGHT_GRAY.getRGB();
				break;
			
			case Colors.MAGENTA:
				sSalienceColor = Color.MAGENTA.getRGB();
				break;
			
			case Colors.ORANGE: 
				sSalienceColor = Color.ORANGE.getRGB();
				break;
			
			case Colors.PINK:
				sSalienceColor = Color.PINK.getRGB();
				break;
			
			case Colors.RED:
				sSalienceColor = Color.RED.getRGB();
				break;
			
			case Colors.WHITE:
				sSalienceColor = Color.WHITE.getRGB();
				break;
			
			case Colors.YELLOW:
				sSalienceColor = Color.YELLOW.getRGB();
				break;
				
			default: 
				sSalienceColor = Color.PINK.getRGB();
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getColor() {

		return sSalienceColor;
	}
	
	private static class Colors {
		static final String BLACK = "BLACK";
		static final String BLUE = "BLUE";
		static final String CYAN = "CYAN";
		static final String DARK_GRAY = "DARK_GRAY";
		static final String GRAY = "GRAY";
		static final String GREEN = "GREEN";
		static final String LIGHT_GRAY = "LIGHT_GRAY";
		static final String MAGENTA = "MAGENTA";
		static final String ORANGE = "ORANGE";
		static final String PINK = "PINK";
		static final String RED = "RED";
		static final String WHITE = "WHITE";
		static final String YELLOW = "YELLOW";
	}

}
