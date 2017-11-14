
public class Thermo implements InterfaceSensor {

	private double tempGUI;
	GUI gui;

	public Thermo(GUI gui) {
		super();
		this.gui = gui;
	}

	public double getTempGUI() {
		tempGUI = gui.getTemp();
		return tempGUI;
	}

	@Override
	public Object getValue() {
		double val = getTempGUI();
	    Double valClass = Double.valueOf(val);
	    Object objectVal = valClass;
	    return objectVal;
	}

}
