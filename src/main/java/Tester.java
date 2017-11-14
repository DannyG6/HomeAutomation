public class Tester {

	public static void main(String[] args) {
		
		GUI gui = new GUI();
		gui.showGUI();
		
		Sensor sensor = new Sensor(gui);
		sensor.addSensor(new Wind(gui));
		sensor.addSensor(new Thermo(gui));
		sensor.addSensor(new Clock(gui));
		
		while(true)
		sensor.run();
		
		
	}

}
