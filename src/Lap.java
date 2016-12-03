import java.util.ArrayList;

public class Lap implements Runnable{
	String id;
	double rawLaptime;
	double adjustedLaptime;
	boolean isRecording;
	ArrayList<dataPack> data;
	dataLogger dl;
	public Lap(dataLogger dl){
		id = "";
		rawLaptime = 0.0;
		adjustedLaptime = 0.0;
		isRecording = false;
		this.dl = dl;
		data = new ArrayList<dataPack>();
	}
	
	public void recordLiveData(){
		while (isRecording == true){
			data.add(dl.sr.dataPack);
//			System.out.println(dl.sr.dataPack);
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
