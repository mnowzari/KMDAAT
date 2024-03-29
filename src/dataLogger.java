import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
public class dataLogger{
	SerialReader sr;
	String currentComPort;
	int baudRate;
	public dataLogger(String comPort, String selectedBaudRate){
		baudRate = Integer.valueOf(selectedBaudRate);
		try {
			currentComPort = comPort;
			establishSerialConnection(comPort);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void establishSerialConnection(String portName) throws Exception{
		CommPortIdentifier portID = CommPortIdentifier.getPortIdentifier(portName);
		if (portID.isCurrentlyOwned()){
			System.out.println("ERR: PORT IS CURRENTLY IN USE");
		}
		else {
			CommPort commPort = portID.open(this.getClass().getName(), 2000);
			if (commPort instanceof SerialPort){
				SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(baudRate,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
                InputStream in = serialPort.getInputStream();
                
                sr = new SerialReader(in);
                Thread srThread = new Thread(sr);
                srThread.start();
			}
		}
	}	
}
