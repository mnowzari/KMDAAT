import java.io.IOException;
import java.io.InputStream;

public class SerialReader implements Runnable{
    InputStream in;
    String out;
    dataPack dataPack;
    public SerialReader (InputStream in){
        this.in = in;
    }
    
	public dataPack parseAndPackageData(String out){
		//this method will take the data from serial, parse it, make a neat dataPack object, and return that dataPack.
		dataPack paket = new dataPack();
		
//		paket.test = out;
		paket.velocity = 0 + (Math.random() * ((65 - 0) + 1));
		paket.coolantTemp = 70 + (Math.random() * ((110 - 70) + 1));
		paket.engineRPM = 4000 + (Math.random() * ((7000 - 4000) + 1));
		paket.oilTemp = 70 + (Math.random() * ((110 - 70) + 1));
		paket.latG = 0 + (Math.random() * ((3 - 0) + 1));
		paket.lonG = 0 + (Math.random() * ((3 - 0) + 1));

		paket.flTemp[0] = 190 + (Math.random() * ((200 - 190) + 1));
		paket.flTemp[1] = 197 + (Math.random() * ((200 - 197) + 1));
		paket.flTemp[2] = 205 + (Math.random() * ((210 - 205) + 1));
		
		paket.frTemp[0] = 193 + (Math.random() * ((200 - 193) + 1));
		paket.frTemp[1] = 197 + (Math.random() * ((200 - 197) + 1));
		paket.frTemp[2] = 205 + (Math.random() * ((210 - 205) + 1));
		
		paket.rlTemp[0] = 192 + (Math.random() * ((200 - 192) + 1));
		paket.rlTemp[1] = 197 + (Math.random() * ((200 - 197) + 1));
		paket.rlTemp[2] = 205 + (Math.random() * ((210 - 205) + 1));
		
		paket.rrTemp[0] = 191 + (Math.random() * ((200 - 191) + 1));
		paket.rrTemp[1] = 197 + (Math.random() * ((200 - 197) + 1));
		paket.rrTemp[2] = 205 + (Math.random() * ((210 - 205) + 1));
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return paket;
	}
    
    public void run(){
        byte[] buffer = new byte[1024];
        int len = -1;
        try{
            while ((len = this.in.read(buffer)) > -1 ){
        		out = new String(buffer, 0, len);
        		dataPack = parseAndPackageData(out);
            }
        }
        catch ( IOException e ){
            e.printStackTrace();
        }            
    }
}
