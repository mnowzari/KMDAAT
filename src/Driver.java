import java.util.ArrayList;

public class Driver {
	String name;
	int numberOfLaps;
	ArrayList<Lap> laps;
	
	public Driver(String name){
		this.name = name;
		numberOfLaps = 0;
		laps = new ArrayList<Lap>();
	}
	
	public void addLap(Lap newLap){
		laps.add(newLap);
		numberOfLaps = laps.size();
	}
	
	//returns most recent laptime
	public double getLastLaptime(){
		if (laps.size() > 0){
			return laps.get(laps.size() - 1).rawLaptime;
		}
		else {
			return 0;
		}
	}
	
	//returns the laptime BEFORE the most recent lap
	public double getPreviousLaptime(){
		if (laps.size() == 1){
			return getLastLaptime();
		}
		else if (laps.size() > 1){
			return laps.get(laps.size() - 2).rawLaptime;
		}
		else {
			return 0;			
		}
	}
	
	public int getFastestLapIndex(){
		int lapIndex = 0;
		for (int i = 0; i < laps.size(); i++){
			if (laps.get(i).rawLaptime == getFastestLaptime()){
				lapIndex = i;
			}
		}
		return lapIndex;
	}
	
	public double getFastestLaptime(){
		double fastestLap = 10000.000;
		for (int i = 0; i < laps.size(); i++){
			if (laps.get(i).rawLaptime < fastestLap){
				fastestLap = laps.get(i).rawLaptime;
			}
		}
		return fastestLap;
	}
}
