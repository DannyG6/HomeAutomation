import java.util.ArrayList;

public interface Actor {
	public void setTemperature(double temp);
	public void setBlind(boolean onOrOff);
	public void update();
	public void setInterfaceSensor(ArrayList<InterfaceSensor> interfaceSensor);
}
