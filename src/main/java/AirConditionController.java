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
	private double desiredEnergySaving = 20;
	private double cost=0;
	
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
		desiredEnergySaving = gui.getDesiredEnergySvg();
		
		Object object = interfaceSensor.get(0).getValue();
		double windGUI = ((Double) object).doubleValue();
		// System.out.println("WIND:"+windGUI);

		object = interfaceSensor.get(1).getValue();
		double tempGUI = ((Double) object).doubleValue();
		// System.out.println("TEMP"+tempGUI);

		object = interfaceSensor.get(2).getValue();
		int timeGUI = ((Integer) object).intValue();
		// System.out.println(timeGUI);

		if (isNight(timeGUI) || isMorning(timeGUI)) {
			if (!energySavingMode) {
				desiredTemperature();
			} else {
				bestTemperature(tempGUI);

			}
		} else {
			if (isTooHot(tempGUI)) {
				if (!energySavingMode) {
					desiredTemperature();
				} else {
					bestTemperature(tempGUI);

				}
			} else {
				temp = -1;
			}
			if (tempGUI <= 24) {
				temp = -1;
			} else if (tempGUI == 32 && windGUI > 30) {
				if (!energySavingMode) {
					desiredTemperature();
				} else {
					bestTemperature(tempGUI);

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
		cost = cost + ((100-saving)*0.000001);
		gui.setCost(cost);
		System.out.println(cost);
	}

	private boolean isTooHot(double tempGUI) {
		return tempGUI >= 34;
	}

	private boolean isMorning(int timeGUI) {
		return timeGUI <= 600;
	}

	private boolean isNight(int timeGUI) {
		return timeGUI >= 2100;
	}

	private double desiredTemperature() {
		//return temp = gui.getDesiredTemperature();
		return temp=27;
	}

	private void bestTemperature(double tempGUI) {
		if (tempGUI <= 27) {
			temp = (tempGUI + (((38 - tempGUI) / desiredEnergySaving)));
		} else {
			temp = ((((desiredEnergySaving - 100) * (38 - tempGUI)) / 100) + tempGUI);
		}
	}

}
