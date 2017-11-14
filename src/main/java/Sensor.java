import java.util.ArrayList;

public class Sensor {
	private ArrayList<InterfaceSensor> interfaceSensor = new ArrayList<InterfaceSensor>();
	private BlindController blindController;
	private AirConditionController airConditionController;
	
	public Sensor(GUI gui){
		blindController = new BlindController(gui);
		airConditionController = new AirConditionController(gui);
		//added supaya controller bisa konek ke GUI
		
	}
	
	public void addSensor(InterfaceSensor sensor){
		interfaceSensor.add(sensor);
	}
	
	public void run(){
//		print("wind:"+String.valueOf(interfaceSensor.get(0).getTemp())); //wind
//		print("thermo:"+String.valueOf(interfaceSensor.get(1).getTemp())); //thermo
//		print("thermo:"+String.valueOf(interfaceSensor.get(1).getBlind()));//thermo
//		print("clock:"+String.valueOf(interfaceSensor.get(2).getTemp()));//clock
//		print("clock:"+String.valueOf(interfaceSensor.get(2).getBlind()));//clock
		
		blindController.setInterfaceSensor(interfaceSensor);
		airConditionController.setInterfaceSensor(interfaceSensor);
		
		blindController.update();
		airConditionController.update();
	}
	
	public void print(String text){
		//System.out.println(text);
	}
}
