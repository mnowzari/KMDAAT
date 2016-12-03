
public class main {

	public static void main(String[] args) {
		dataLogger dl = new dataLogger();
        Event event = new Event(dl);
		mainWindow mw = new mainWindow(event);
	}

}
