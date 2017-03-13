import java.util.ArrayList;

public class Lap implements Runnable{
	int lapNumber;
	String driverName;
	int cones;
	double rawLaptime;
	double adjustedLaptime;
	boolean isRecording;
	ArrayList<dataPack> data;
	dataLogger dl;
	
	public Lap(dataLogger dl){
		lapNumber = 0;
		driverName = "";
		cones = 0;
		rawLaptime = 0.0;
		adjustedLaptime = 0.0;
		isRecording = false;
		this.dl = dl;
		data = new ArrayList<dataPack>();
	}
	
	public void recordLiveData(){
		while (isRecording == true){
			if (data.size() < 1199){
				data.add(dl.sr.dataPack);
//				System.out.println(data.get(data.size()-1).test);
			}
			else {
				isRecording = false;
			}
    		try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void run() {
		recordLiveData();
	}
}
