public class Clock implements InterfaceSensor {

	private int timeGUI;
	GUI gui;
	
	public Clock(GUI gui) {
		super();
		this.gui = gui;
	}
	
	public int getTimeGUI() {
		timeGUI = gui.getTime();
		return timeGUI;
	}

	@Override
	public Object getValue() {
		int val = getTimeGUI();
	    Integer valClass = Integer.valueOf(val);
	    Object objectVal = valClass;
	    return objectVal;
	}

}
