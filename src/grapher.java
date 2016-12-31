import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class grapher {
	public grapher(Event e){
		
	}
	
	public BufferedImage overlaidVelocityGraph(Driver driver){
		int width = 2400;
		int height = 300;
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		
		int fastestLapIndex = driver.getFastestLapIndex();
		
		img = makeSingleVelocityGraph(driver.laps.get(fastestLapIndex).data, Color.MAGENTA);
		
		ArrayList<dataPack> data = driver.laps.get(driver.numberOfLaps - 1).data;
		
		if (data.size() < 1200){
			for (int i = 0; i < data.size(); i++){
				if ((int)data.get(i).velocity == 0){
					img.setRGB(i*2, height - ((int)data.get(i).velocity + 1), Color.GREEN.getRGB());	
				}
				else {
					img.setRGB(i*2, height - ((int)data.get(i).velocity), Color.GREEN.getRGB());
				}
			}	
		}
		
		return img;
	}
	
	public BufferedImage makeSingleVelocityGraph(ArrayList<dataPack> data, Color lineColor){
		int width = 2400;
		int height = 300;
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		for (int i = 0; i < width; i++){
			for (int k = 0; k < height; k++){
				img.setRGB(i, k, Color.BLACK.getRGB());
				
				if ( ( ( (double)i / 9) % 2) == 0){
					img.setRGB(i, k, Color.DARK_GRAY.getRGB());
				}
				
				if ( ( ( (double)k / 9) % 2) == 0){
					img.setRGB(i, k, Color.DARK_GRAY.getRGB());
				}
			}
		}
		if (data.size() < 1200){
			for (int i = 0; i < data.size(); i++){
				if ((int)data.get(i).velocity == 0){
					img.setRGB(i*2, height - ((int)data.get(i).velocity + 1), lineColor.getRGB());	
				}
				else {
					img.setRGB(i*2, height - ((int)data.get(i).velocity), lineColor.getRGB());
				}
			}	
		}
//		else if (data.size() > 1200){
//			for (int i = 0; i < data.size() / 2; i++){
//				if ((int)data.get(i).velocity == 0){
//					img.setRGB(i*2, height - ((int)data.get(i).velocity + 1), Color.GREEN.getRGB());		
//				}
//				else {
//					img.setRGB(i*2, height - ((int)data.get(i).velocity), Color.GREEN.getRGB());
//				}
//			}	
//		}
		return img;
	}
	
	public BufferedImage makeSingleTireTempGraph(ArrayList<dataPack> data, String whichTire){
		int width = 2400;
		int height = 300;
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

		for (int i = 0; i < width; i++){
			for (int k = 0; k < height; k++){
				img.setRGB(i, k, Color.BLACK.getRGB());
				
				if ( ( ( (double)i / 9) % 2) == 0){
					img.setRGB(i, k, Color.DARK_GRAY.getRGB());
				}
				
				if ( ( ( (double)k / 9) % 2) == 0){
					img.setRGB(i, k, Color.DARK_GRAY.getRGB());
				}
			}
		}
				
		for (int i = 0; i < data.size(); i++){
			if (whichTire.equalsIgnoreCase("fl")){
				img.setRGB(i, height - ((int)data.get(i).flTemp[0]), Color.BLUE.getRGB());
				img.setRGB(i, height - ((int)data.get(i).flTemp[1]), Color.GREEN.getRGB());
				img.setRGB(i, height - ((int)data.get(i).flTemp[2]), Color.RED.getRGB());	
			}
			else if (whichTire.equalsIgnoreCase("fr")){
				img.setRGB(i, height - ((int)data.get(i).frTemp[0]), Color.BLUE.getRGB());
				img.setRGB(i, height - ((int)data.get(i).frTemp[1]), Color.GREEN.getRGB());
				img.setRGB(i, height - ((int)data.get(i).frTemp[2]), Color.RED.getRGB());
			}
			else if (whichTire.equalsIgnoreCase("rr")){
				img.setRGB(i, height - ((int)data.get(i).rrTemp[0]), Color.BLUE.getRGB());
				img.setRGB(i, height - ((int)data.get(i).rrTemp[1]), Color.GREEN.getRGB());
				img.setRGB(i, height - ((int)data.get(i).rrTemp[2]), Color.RED.getRGB());
			}
			else if (whichTire.equalsIgnoreCase("rl")){
				img.setRGB(i, height - ((int)data.get(i).rlTemp[0]), Color.BLUE.getRGB());
				img.setRGB(i, height - ((int)data.get(i).rlTemp[1]), Color.GREEN.getRGB());
				img.setRGB(i, height - ((int)data.get(i).rlTemp[2]), Color.RED.getRGB());
			}
			else if (whichTire.equalsIgnoreCase("all")){
				img.setRGB(i*2, height - ((int)data.get(i).flTemp[0]), Color.BLUE.getRGB());
				img.setRGB(i*2, height - ((int)data.get(i).flTemp[1]), Color.GREEN.getRGB());
				img.setRGB(i*2, height - ((int)data.get(i).flTemp[2]), Color.RED.getRGB());	
				
				img.setRGB(i*2, height - ((int)data.get(i).frTemp[0]), Color.BLUE.getRGB());
				img.setRGB(i*2, height - ((int)data.get(i).frTemp[1]), Color.GREEN.getRGB());
				img.setRGB(i*2, height - ((int)data.get(i).frTemp[2]), Color.RED.getRGB());
				
				img.setRGB(i*2, height - ((int)data.get(i).rrTemp[0]), Color.BLUE.getRGB());
				img.setRGB(i*2, height - ((int)data.get(i).rrTemp[1]), Color.GREEN.getRGB());
				img.setRGB(i*2, height - ((int)data.get(i).rrTemp[2]), Color.RED.getRGB());
				
				img.setRGB(i*2, height - ((int)data.get(i).rlTemp[0]), Color.BLUE.getRGB());
				img.setRGB(i*2, height - ((int)data.get(i).rlTemp[1]), Color.GREEN.getRGB());
				img.setRGB(i*2, height - ((int)data.get(i).rlTemp[2]), Color.RED.getRGB());
			}

		}
		return img;
	}
}
