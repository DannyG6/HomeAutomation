import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class BlindController implements Actor {

	private ArrayList<InterfaceSensor> interfaceSensor;
	private GUI gui;
	private boolean onOrOff = false;

	public BlindController(GUI gui) {
		this.gui = gui;
	}

	@Override
	public void setInterfaceSensor(ArrayList<InterfaceSensor> interfaceSensor) {
		this.interfaceSensor = interfaceSensor;
	}

	@Override
	public void setTemperature(double temp) {
		// EMPTY
		// DO NOT WRITE ANYTHING HERE

	}

	@Override
	public void setBlind(boolean onOrOff) {
		gui.setOnOff(onOrOff);
	}

	@Override
	public void update() {

		Object object = interfaceSensor.get(1).getValue();
		double tempGUI = ((Double) object).doubleValue();
		// System.out.println(tempGUI);

		object = interfaceSensor.get(2).getValue();
		int timeGUI = ((Integer) object).intValue();
		// System.out.println(timeGUI);

		if (isAfternoon(timeGUI)) {
			onOrOff = true;
		} else {
			onOrOff = false;
			if(tempGUI>=36){
				onOrOff = true;
			}
		}

		setBlind(onOrOff);

	}

	private boolean isAfternoon(int timeGUI) {
		return timeGUI >= 1000 && timeGUI <=1400;
	}
}
