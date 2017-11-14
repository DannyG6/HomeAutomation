
public class Wind implements InterfaceSensor {

	private double windSpeedGUI;
	GUI gui;

	public Wind(GUI gui) {
		super();
		this.gui = gui;
	}
	
	public double getWindGUI() {
		windSpeedGUI = gui.getWindSpeed();
		return windSpeedGUI;
	}

	@Override
	public Object getValue() {
		double val = getWindGUI();
	    Double valClass = Double.valueOf(val);
	    Object objectVal = valClass;
	    return objectVal;
	}

}
