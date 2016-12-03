import java.io.IOException;
import java.io.InputStream;

public class SerialReader implements Runnable{
    InputStream in;
    String out;
    dataPack dataPack;
    public SerialReader (InputStream in){
        this.in = in;
    }
    
	public dataPack parseAndPackageData(){
		//this method will take the data from serial, parse it, make a neat dataPack object, and return that dataPack.
		dataPack paket = new dataPack();
		paket.velocity = 0 + (Math.random() * ((50 - 0) + 1));
		paket.coolantTemp = 70 + (Math.random() * ((110 - 70) + 1));
		paket.engineRPM = 4000 + (Math.random() * ((7000 - 4000) + 1));
		paket.oilTemp = 110 + (Math.random() * ((120 - 110) + 1));
		
		paket.flTemp[0] = 175 + (Math.random() * ((207 - 178) + 1));
		paket.flTemp[1] = 180 + (Math.random() * ((215 - 197) + 1));
		paket.flTemp[2] = 195 + (Math.random() * ((230 - 205) + 1));
		
		paket.frTemp[0] = 175 + (Math.random() * ((207 - 178) + 1));
		paket.frTemp[1] = 180 + (Math.random() * ((215 - 197) + 1));
		paket.frTemp[2] = 195 + (Math.random() * ((230 - 205) + 1));
		
		paket.rlTemp[0] = 175 + (Math.random() * ((207 - 178) + 1));
		paket.rlTemp[1] = 180 + (Math.random() * ((215 - 197) + 1));
		paket.rlTemp[2] = 195 + (Math.random() * ((230 - 205) + 1));
		
		paket.rrTemp[0] = 175 + (Math.random() * ((207 - 178) + 1));
		paket.rrTemp[1] = 180 + (Math.random() * ((215 - 197) + 1));
		paket.rrTemp[2] = 195 + (Math.random() * ((230 - 205) + 1));
		
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
//        		System.out.print(new String(buffer, 0, len));
        		out = new String(buffer, 0, len);
//        		System.out.print(out);
        		dataPack = parseAndPackageData();
            }
        }
        catch ( IOException e ){
            e.printStackTrace();
        }            
    }
}