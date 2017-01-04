import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Event {
	String filename;
	String eventName;
	String eventLocation;
	String competingClass;
	String tireName;
	String tireSize;
	String date;
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
	boolean eventExists;
	ArrayList<Driver> drivers;
	dataLogger dl;
	xmlLogger xml;
	
	public Event(dataLogger dl){
		this.dl = dl;
		filename= null;
		eventName = null;
		eventLocation = null;
		tireName = null;
		tireSize = null;
		competingClass = null;
		date = getDate();
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
		eventExists = false;
		drivers = new ArrayList<Driver>();
		xml = new xmlLogger(this);
	}
	
	private String getDate(){
		String timeStamp = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());
		return timeStamp;
	}
	
	public String genFilename(){
		this.filename = this.eventName.replaceAll("\\s+","") + "_database.xml";
		return this.filename;
	}
	
	public void loadFromPreviousXML(String loadName){
		xml.loadFromPreviousXML(loadName);
	}
	
	public void createNewXML(){
		xml.createNewXML();
	}
	
	public void updateBTC_BTD_percentages(){
		xml.addBTC_BTD_percentages();
	}
	
	public void updateSuspensionSettings(){
		xml.updateSuspensionSettings();
	}
	
	public void addLap(String driver){
		xml.addLap(driver);
	}
	
	public Double getOverallFastestLapTime(){
		double overallFastest = 1000.000;
		for (int i = 0; i < drivers.size(); i++){
			if (drivers.get(i).getFastestLaptime() < overallFastest){
				overallFastest = drivers.get(i).getFastestLaptime();				
			}
		}
		return overallFastest;
	}
	
	public void calcPercentBTD(){
		BigDecimal bd = new BigDecimal(getOverallFastestLapTime() / BTD);
	    bd = bd.setScale(2, RoundingMode.HALF_UP);
		percent_BTD = bd.doubleValue();
	}
	
	public void calcPercentBTC(){
		BigDecimal bd = new BigDecimal(getOverallFastestLapTime() / BTC);
	    bd = bd.setScale(2, RoundingMode.HALF_UP);
		percent_BTC = bd.doubleValue();
	}
	
	public void dumpData(){
//		System.out.println(filename + ", " + eventName + ", " + eventLocation + ", " + competingClass + ", " + tireName + ", " + tireSize);
//		for (int i = 0; i < laps.size(); i++){
//			for (int k = 0; k < laps.get(i).data.size(); k++){
//				System.out.println("Lap " + laps.get(i).lapNumber + " velocity: " + laps.get(i).data.get(k).velocity);
//			}
//		}
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
		competingClass = null;
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
		drivers.clear();
	}
}
