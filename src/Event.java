import java.util.ArrayList;

public class Event {
	String filename;
	String eventName;
	String eventLocation;
	String competingClass;
	String tireName;
	String tireSize;
	double ambientAirTemp;
	double trackSurfaceTemp;
	double BTC;
	double BTD;
	double percent_BTC;
	double percent_BTD;
	double carWeight;
	double adjustmentCoeff;
	double[] frSetup; //index 0 = camber, index 1 = toe
	double[] flSetup;
	double[] rlSetup;
	double[] rrSetup;
	ArrayList<Lap> laps;
	dataLogger dl;
	
	public Event(dataLogger dl){
		this.dl = dl;
		filename= null;
		eventName = null;
		eventLocation = null;
		tireName = null;
		tireSize = null;
		ambientAirTemp = 0.0;
		trackSurfaceTemp = 0.0;
		BTC = 0.0;
		BTD = 0.0;
		percent_BTC = 0.0;
		percent_BTD = 0.0;
		carWeight = 0.0;
		adjustmentCoeff = 0.0;
		frSetup = new double[2];
		flSetup = new double[2];
		rrSetup = new double[2];
		rlSetup = new double[2];
		laps = new ArrayList<Lap>();
		
	}
	
	public void genFilename(){
		this.filename = this.eventName.trim() + "_database.xml";
	}
	
	public void saveEventToXML(){
		xmlLogger xm = new xmlLogger();
	}
	
	public void addLap(Lap newLap){
		laps.add(newLap);
	}
	
	
	public void dumpData(){
		System.out.println(filename + ", " + eventName + ", " + eventLocation + ", " + competingClass + ", " + tireName + ", " + tireSize);
		for (int i = 0; i < laps.size(); i++){
			for (int k = 0; k < laps.get(i).data.size(); k++){
				System.out.println("Lap " + laps.get(i).id + " velocity: " + laps.get(i).data.get(k).velocity);
			}
		}
	}
	
	public void resetSuspensionSettings(){
		frSetup = new double[2];
		flSetup = new double[2];
		rrSetup = new double[2];
		rlSetup = new double[2];
	}
	
	public void resetEvent(){
		filename= null;
		eventName = null;
		eventLocation = null;
		tireName = null;
		tireSize = null;
		ambientAirTemp = 0.0;
		trackSurfaceTemp = 0.0;
		BTC = 0.0;
		BTD = 0.0;
		percent_BTC = 0.0;
		percent_BTD = 0.0;
		carWeight = 0.0;
		adjustmentCoeff = 0.0;
		frSetup = new double[2];
		flSetup = new double[2];
		rrSetup = new double[2];
		rlSetup = new double[2];
		laps.clear();
	}
}
