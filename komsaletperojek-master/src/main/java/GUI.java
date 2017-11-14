import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Observer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.attribute.AttributeSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class GUI{
	
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    private static JTextField clockInput;
    private static JLabel textField;
    private static ChangeListener listener1,listener2,listener3;
    private static ActionListener listener4;
    private static JLabel gordyn,energy;
    private static JToggleButton toggleEnergy;
    private static boolean OnOff,toggleEnergySvg;
    private static double temp,windSpeed;
    private static int time;
    private static double temp2,energySvg;

    public static void addComponentsToPane(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        
     // common listener for all sliders
	listener1 = new ChangeListener()
	{
	   public void stateChanged(ChangeEvent event)
	   {
	      // update text field when the slider value changes
	      JSlider source = (JSlider) event.getSource();
	     // textField.setText("" + source.getValue());
//	      System.out.println("inside : "+temp2);
	      if(temp2!=-1){
	    	  textField.setText(temp2+" °C");
	      }
	      else{
	    	  textField.setText("AC OFF");
	      }
	      temp = source.getValue();
	      if(OnOff){
				gordyn.setText("ON");
			}else{
				gordyn.setText("OFF");
			}
	      energy.setText(energySvg+"%");
	   }
	};

    JSlider slider;
	pane.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();
	if (shouldFill) {
		//natural height, maximum width
		c.fill = GridBagConstraints.HORIZONTAL;
	}
	  
	JLabel label = new JLabel("AC");
	c.weightx = 0.5;
	c.gridx = 0;
	c.gridy = 0;
	pane.add(label, c);
	
	
	textField = new JLabel("16 °C");
    c.gridx = 0;
    c.gridy = 1;
    c.gridwidth=1;
    c.gridheight=1;
    pane.add(textField,c);
    
    JLabel gordynLabel = new JLabel("Gordyn");
    c.gridx = 0;
    c.gridy = 2;
    pane.add(gordynLabel,c);
    
    gordyn = new JLabel("OFF");
    c.gridx = 0;
    c.gridy = 3;
    pane.add(gordyn,c);
    
    JLabel label2 = new JLabel("Temperature");
    c.weightx = 0.5;
    c.gridx = 1;
    c.gridy = 0;
    pane.add(label2, c);
    
    
    // add a slider with numeric labels
    slider = new JSlider(16,37);
    slider.setPaintTicks(true);
    slider.setPaintLabels(true);
    slider.setSnapToTicks(true);
    slider.setMajorTickSpacing(10);
    slider.setMinorTickSpacing(1);
    c.weightx = 0.5;
    c.gridx = 1;
    c.gridy = 1;
    addSlider(slider,"",pane,c,listener1);
    
    JLabel anemo = new JLabel("Anemometer");
    c.gridx = 1;
    c.gridy = 2;
    c.gridwidth=1;
    c.gridheight=1;
    pane.add(anemo,c);

    // add a slider with numeric labels
 	JSlider anemometer = new JSlider(0,80);
 	anemometer.setPaintTicks(true);
 	anemometer.setPaintLabels(true);
 	anemometer.setSnapToTicks(true);
 	anemometer.setMajorTickSpacing(80);
 	anemometer.setMinorTickSpacing(1);
 	c.weightx = 0.5;
 	c.gridx = 1;
 	c.gridy = 3;
 	listener2 = new ChangeListener()
	{
	   public void stateChanged(ChangeEvent event)
	   {
	      // update text field when the slider value changes
	      JSlider source = (JSlider) event.getSource();
	      if(temp2!=-1){
	    	  textField.setText(temp2+"°C");
	      }
	      else{
	    	  textField.setText("AC OFF");
	      }
	      windSpeed = source.getValue();
	   }
	};
 	addSlider(anemometer,"",pane,c,listener2);
 	
     
     
     

  	clockInput = new JTextField(5);
  	c.gridx = 1;
    c.gridy = 5;
    c.gridwidth=2;
    pane.add(clockInput, c);
    
    clockInput.addKeyListener(new java.awt.event.KeyAdapter() {

    public void keyReleased(java.awt.event.KeyEvent evt) {
        try {
            long number = Long.parseLong(clockInput.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(pane, "Only Numbers Allowed");
            clockInput.setText("");
        }
    }
});
    clockInput.addKeyListener(new KeyAdapter() {
	        public void keyReleased(KeyEvent e) {
	        	time = Integer.parseInt(clockInput.getText());
				char[] timeDot = (time+"-").toCharArray();
				timeDot[4] = timeDot[3];
				timeDot[3] = timeDot[2];
				timeDot[2] = ':';
				String finalTime = String.valueOf(timeDot);
				clockInput.setText(finalTime);
				if(OnOff){
					gordyn.setText("ON");
				}else{
					gordyn.setText("OFF");
				}
	        }
	    });
    
    energy = new JLabel("Energy Saving Off");
    c.gridx = 0;
    c.gridy = 4;
    c.gridwidth=1;
    c.gridheight=1;
    pane.add(energy, c);
    
 
    toggleEnergy = new JToggleButton("Energy Saving On/Off");
    c.gridx = 0;
    c.gridy = 5;
  listener3 = new ChangeListener()
  {
	   public void stateChanged(ChangeEvent event)
	   {
		   JToggleButton tgl = (JToggleButton)event.getSource();
		 if(tgl.isSelected()){
			 toggleEnergySvg=true;
			 textField.setText(temp+"°C");
			 energy.setText(energySvg+"%");
		 }else{

			 toggleEnergySvg=false;
		 }
	   }
	};
	toggleEnergy.addChangeListener(listener3);
    pane.add(toggleEnergy, c);
    
    
    }
        
    public static void addSlider(JSlider s, String description,Container pane,GridBagConstraints c,ChangeListener listener)
    {
      s.addChangeListener(listener);
      pane.add(s,c);
    }
    
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("GridBagLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public void showGUI() {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
            }
        });
    }
    
    public boolean getOnOff(){
    	return OnOff;
    }
    
    public void setOnOff(boolean OnOff){
    	this.OnOff = OnOff;
//    	gordyn.setEnabled(OnOff);
    }
    
    public int getTime(){
    	return time;
    }
    
    public double getTemp(){
    	return temp;
    }
    
    public void setTemp(double temp2){
    	this.temp2 = temp2;
//    	System.out.println("GUI : "+temp2);
    }
    
    public double getWindSpeed(){
    	return windSpeed;
    }
    
    public void setEnergy(double energySvg){
    	DecimalFormat df = new DecimalFormat("#.00");
    	this.energySvg = Double.parseDouble(df.format(energySvg));
    }
    
    public boolean getEnergySavingMode(){
    	
		return toggleEnergySvg;
    }
 	    	
}