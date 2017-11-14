import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class AirConditionController implements Actor {// implements observer to
														// observe changes in
														// GUI

	private ArrayList<InterfaceSensor> interfaceSensor;
	private GUI gui;
	private double temp = 0;
	private boolean energySavingMode = true;
	private double desiredEnergySaving = 80;

	public AirConditionController(GUI gui) {
		this.gui = gui;
	}

	@Override
	public void setInterfaceSensor(ArrayList<InterfaceSensor> interfaceSensor) {
		this.interfaceSensor = interfaceSensor;
	}

	@Override
	public void setTemperature(double temp) {
		gui.setTemp(temp);
	}

	@Override
	public void setBlind(boolean onOrOff) {
		// EMPTY
		// DO NOT WRITE ANYTHING HERE
	}

	@Override
	public void update() {

	    energySavingMode = gui.getEnergySavingMode();

		Object object = interfaceSensor.get(0).getValue();
		double windGUI = ((Double) object).doubleValue();
		// System.out.println("WIND:"+windGUI);

		object = interfaceSensor.get(1).getValue();
		double tempGUI = ((Double) object).doubleValue();
		// System.out.println("TEMP"+tempGUI);

		object = interfaceSensor.get(2).getValue();
		int timeGUI = ((Integer) object).intValue();
		// System.out.println(timeGUI);

		if (timeGUI >= 2100 || timeGUI <= 600) {
			if (!energySavingMode) {
				temp = 27;
			} else {
				if (tempGUI <= 27){
					temp = (tempGUI+(((38-tempGUI)/desiredEnergySaving)));
				}
				else {
					temp = ((((desiredEnergySaving - 100) * (38 - tempGUI))/100) + tempGUI);
				}

			}
		} else {
			if (tempGUI >= 34) {
				if (!energySavingMode) {
					temp = 27;
				} else {
					if (tempGUI < 27)
						temp = ((desiredEnergySaving / 100) * (38 - tempGUI) + tempGUI);
					else {
						temp = ((desiredEnergySaving / 100) * (tempGUI - 38) + tempGUI);
					}

				}
			} else {
				temp = -1;
			}
			if (tempGUI <= 24) {
				temp = -1;
			} else if (tempGUI == 32 && windGUI > 30) {
				if (!energySavingMode) {
					temp = 27;
				} else {
					if (tempGUI < 27)
						temp = ((desiredEnergySaving / 100) * (38 - tempGUI) + tempGUI);
					else {
						temp = ((desiredEnergySaving / 100) * (tempGUI - 38) + tempGUI);
					}

				}
			}
		}

		double saving = 0;
		if (!energySavingMode) {
			saving = 100 - (Math.abs((27 - tempGUI) / (38 - tempGUI) * 100));
			if (saving <= 0) {
				saving = 0;
			}
		} else {
			saving = desiredEnergySaving;
		}

		gui.setEnergy(saving);
		setTemperature(temp);

	}

}
