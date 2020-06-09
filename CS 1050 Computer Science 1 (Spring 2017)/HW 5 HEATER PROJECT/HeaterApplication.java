import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * HeaterApplication class adds Scanner/JOptionPane features to access and change values in the Heater class
 * 
 * @Adam Schaible
 * @version 3/13/17
 */
public class HeaterApplication
{
    
        public static void main(String[] args)
    {
        Heater heater1;
        Heater heater2;
        
        Scanner ns = new Scanner(System.in);
        
        System.out.print("Input the minimum temperature for the 1st Heater: ");
        int minimumTemperature = ns.nextInt();
        
        System.out.print("Input the maximum temperature for the 1st Heater: ");
        int maximumTemperature = ns.nextInt();
        
        heater1 = new Heater(minimumTemperature, maximumTemperature);
        System.out.println("The Heater 1 temperature currently is: " + heater1.getTemperature());
        heater1.cooler();
        System.out.print("Current Heater 1 temperature after the attempt to decrease the temperature is: " + heater1.getTemperature());
        
        String minimumTemperatureText = JOptionPane.showInputDialog("Input the minimum Temperature for 2nd Heater: ");
        int minimumTemperature2 = Integer.parseInt(minimumTemperatureText);
        
        String maximumTemperatureText = JOptionPane.showInputDialog("Input the Maximum Temperature for 2nd Heater: ");
        int maximumTemperature2 = Integer.parseInt(maximumTemperatureText);
        
        heater2 = new Heater(minimumTemperature2, maximumTemperature2);
        JOptionPane.showMessageDialog(null, "The temperature currently is: " + heater2.getTemperature());
        heater2.warmer();
        JOptionPane.showMessageDialog(null, "Current Heater 2 temperature after attempt to increase the temperature is: " + heater2.getTemperature());
                
        if(heater1.equals(heater2))
        {
            JOptionPane.showMessageDialog(null, "Heaters 1 & 2 are the same and have identical values stored in them");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Heaters 1 & 2 are not the same and have differing values stored in them");
        }
        
        System.exit(0);              
        
    }
        
}
