import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class grapher {
	public grapher(Event e){
		
	}
	
	public BufferedImage overlaidVelocityGraph(ArrayList<Driver> drivers){
		ArrayList<dataPack> driver1 = new ArrayList<dataPack>();
		ArrayList<dataPack> driver2 = new ArrayList<dataPack>();

		int width = 1800;
		int height = 300;
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		
		
		
		return  img;
	}
	
	public BufferedImage makeSingleVelocityGraph(ArrayList<dataPack> data){
		int width = 1800;
		int height = 300;
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		for (int i = 0; i < width; i++){
			for (int k = 0; k < height; k++){
				img.setRGB(i, k, Color.DARK_GRAY.getRGB());
			}
		}
		
		if (data.size() < 900){
			for (int i = 0; i < data.size(); i++){
				if ((int)data.get(i).velocity == 0){
					img.setRGB(i*2, height - ((int)data.get(i).velocity + 1), Color.RED.getRGB());			
				}
				else {
					img.setRGB(i*2, height - ((int)data.get(i).velocity), Color.RED.getRGB());	
				}
			}	
		}
		else if (data.size() > 900){
			for (int i = 0; i < data.size() / 2; i++){
				if ((int)data.get(i).velocity == 0){
					img.setRGB(i*2, height - ((int)data.get(i).velocity + 1), Color.RED.getRGB());			
				}
				else {
					img.setRGB(i*2, height - ((int)data.get(i).velocity), Color.RED.getRGB());	
				}
			}	
		}
		
		return img;
	}
	
	public BufferedImage makeSingleTireTempGraph(ArrayList<dataPack> data, String whichTire){
		int width = 1800;
		int height = 300;
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

		for (int i = 0; i < width; i++){
			for (int k = 0; k < height; k++){
				img.setRGB(i, k, Color.DARK_GRAY.getRGB());
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
