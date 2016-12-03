
public class temperatureGen implements Runnable{
	double[] randomNumber;
	public temperatureGen(){
		this.randomNumber = new double[3];
	}

	public void run() {
		while (true){
			randomNumber[0] = 175 + (Math.random() * ((207 - 178) + 1));
			randomNumber[1] = 180 + (Math.random() * ((215 - 197) + 1));
			randomNumber[2] = 195 + (Math.random() * ((230 - 205) + 1));
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
