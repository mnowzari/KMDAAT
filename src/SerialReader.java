import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class SerialReader implements Runnable{
    InputStream in;
    dataPack dataPack;
    String tempOutput;
    public SerialReader (InputStream in){
        this.in = in;
        tempOutput = new String();
    }
    
	public dataPack parseAndPackageData(String out){
		//this method will take the data from serial, parse it, make a neat dataPack object, and return that dataPack.
		dataPack paket = new dataPack();
		int expectedSizeOfPacket = 11;
		String[] sOut = out.split(",");
		if (sOut.length >= expectedSizeOfPacket){
			paket.velocity = Double.valueOf(sOut[3]);
			paket.latG = Double.valueOf(sOut[4]);
			paket.lonG = Double.valueOf(sOut[6]);
//			System.out.println(paket.velocity + ", " + paket.latG + ", " + paket.lonG);
		}
		else {
			System.out.println("ERR: DROPPED DATA " + out);
			try {
				in.skip(1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

//		paket.velocity = 0 + (Math.random() * ((65 - 0) + 1));
//		paket.coolantTemp = 70 + (Math.random() * ((110 - 70) + 1));
//		paket.engineRPM = 4000 + (Math.random() * ((7000 - 4000) + 1));
//		paket.oilTemp = 70 + (Math.random() * ((110 - 70) + 1));
//		paket.latG = 0 + (Math.random() * ((3 - 0) + 1));
//		paket.lonG = 0 + (Math.random() * ((3 - 0) + 1));
//		paket.flTemp[0] = 190 + (Math.random() * ((200 - 190) + 1));
//		paket.flTemp[1] = 197 + (Math.random() * ((200 - 197) + 1));
//		paket.flTemp[2] = 205 + (Math.random() * ((210 - 205) + 1));
//		
//		paket.frTemp[0] = 193 + (Math.random() * ((200 - 193) + 1));
//		paket.frTemp[1] = 197 + (Math.random() * ((200 - 197) + 1));
//		paket.frTemp[2] = 205 + (Math.random() * ((210 - 205) + 1));
//		
//		paket.rlTemp[0] = 192 + (Math.random() * ((200 - 192) + 1));
//		paket.rlTemp[1] = 197 + (Math.random() * ((200 - 197) + 1));
//		paket.rlTemp[2] = 205 + (Math.random() * ((210 - 205) + 1));
//		
//		paket.rrTemp[0] = 191 + (Math.random() * ((200 - 191) + 1));
//		paket.rrTemp[1] = 197 + (Math.random() * ((200 - 197) + 1));
//		paket.rrTemp[2] = 205 + (Math.random() * ((210 - 205) + 1));
		return paket;
	}
    
    public void run(){
    	int buffsize = 1024;
        byte[] buffer = new byte[buffsize];
        byte[] overrunBuffer = new byte[buffsize];
        boolean appendToOverrun = false;
        int len = -1;
        int errorCounter = 0;
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
        try{
            while ((len = this.in.read(buffer)) > -1 ){
            	if (appendToOverrun == true){
            		int term = 0;
            		boolean hasFound = false;
            		for (int i = 0; i < buffer.length; i++){ //finds index of terminating char
            			if (buffer[i] == 10 && hasFound == false){
            				term = i;
            				hasFound = true;
            			}
            		}
            		
            		int startHere = 0; //finds index of where it left off on overrunBuffer
            		while (overrunBuffer[startHere] != 0){
            			startHere++;
            		}
            		
            		for (int k = 0; k < term; k++){
            			overrunBuffer[startHere] = buffer[k];
            			startHere++;
            		}
            		
            		String serialOut = new String(overrunBuffer, 0, startHere);
            		System.out.println(serialOut);
        			dataPack = parseAndPackageData(serialOut);
            		appendToOverrun = false;
            		overrunBuffer = new byte[buffsize];
            	}
            	else if (appendToOverrun == false){
                	String serialOut = "";
                	int header = 0;
                	int term = 0;
                	boolean hasHeaderBeenFound = false;
                	boolean hasTermBeenFound = false;
                	for (int i = 0; i < buffer.length; i++){
                		if (buffer[i] == 70 && hasHeaderBeenFound == false){
                			header = i;
                			hasHeaderBeenFound = true;
                		}
                		else if (hasHeaderBeenFound == true){
                			if (buffer[i] == 10){
                				term = i;
                				hasTermBeenFound = true;
                			}
                		}
                	}
                	
                	if (hasHeaderBeenFound == true && hasTermBeenFound == true && header < term){
                    	byte[] outputArray = new byte[Math.abs(term - header)];
                    	int pointer = header;
                    	for (int k = 0; k < (term - header); k++){
                    		if (buffer[pointer] != 0){
                        		outputArray[k] = buffer[pointer];
                        		pointer++;	
                    		}
                    	}            	
                    	
                		serialOut = new String(outputArray, 0, Math.abs(term - header)); 
            			dataPack = parseAndPackageData(serialOut);
                	}
            		else if (hasTermBeenFound == false && hasHeaderBeenFound == true){
            			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            			Calendar calobj = Calendar.getInstance();
            			System.out.println("Buffer Overflow Condition. " + df.format(calobj.getTime()));
            			
            			appendToOverrun = true;
            			int index = 0;
            			for (int i = header; i < buffer.length; i++){ //populate overrun buffer
            				overrunBuffer[index] = buffer[i];
            				index++;
            			}
                		buffer = new byte[buffsize];
            		}
            		else {
            			errorCounter++;
            			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            			Calendar calobj = Calendar.getInstance();
            			System.out.println("ERROR. Error Count: " + errorCounter + ", " + df.format(calobj.getTime()));
            		}
                	buffer = new byte[buffsize];
            		try {
            			Thread.sleep(100);
            		} catch (InterruptedException e) {
            			e.printStackTrace();
            		}	
            	}
            }
        }
        catch ( IOException e ){
            e.printStackTrace();
        }            
    }
}
