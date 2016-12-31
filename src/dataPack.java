
public class dataPack {
	String id;
	double velocity;
	double latG;
	double lonG;
	double posX;
	double posY;
	double engineRPM;
	double steeringAngle;
	double oilTemp;
	double coolantTemp;
	double[] frTemp; //index 0 = outer, index 1 = middle, index 2 = inner
	double[] flTemp;
	double[] rrTemp;
	double[] rlTemp;
	String test;
	
	public dataPack(){
		id = "0000";
		velocity = 0.0;
		latG = 0.0;
		lonG = 0.0;
		posX = 0.0;
		posY = 0.0;
		engineRPM = 0.0;
		steeringAngle = 0.0;
		oilTemp = 0.0;
		coolantTemp = 0.0;
		frTemp = new double[3];
		flTemp = new double[3];
		rrTemp = new double[3];
		rlTemp = new double[3];
	}
}
