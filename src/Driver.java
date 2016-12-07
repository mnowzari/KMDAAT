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
//		System.out.println(laps.get(laps.size() - 1).rawLaptime);
	}
}
