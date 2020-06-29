import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * HeaterApplication class adds Scanner/JOptionPane features to access and change values in the Heater class
 * 
 * @Adam Schaible
 * @version 6/29/20
 */
public class HeaterApplication
{
    
        public static void main(String[] args)
    {
        Heater heater1;
        Heater heater2;
        
        Scanner ns = new Scanner(System.in);
        
        System.out.print("Input the minimum temperature for the 1st Heater: ");
        int minimumTemperature;
        while(true)
        {
            String input = "";

            try
            {
                input = ns.nextLine();

                if (input.contains("."))
                {
                    System.out.println("You entered: " + input + ", which is not a integer");
                    System.out.println("Please enter an integer");
                    System.out.print("Input the minimum temperature for the 1st Heater: ");
                }
                else
                {
                    minimumTemperature = Integer.parseInt(input);
                    break;
                }
            }
            catch (Exception e)
            {
               System.out.println("You entered: " + input + ", which is not a integer");
                System.out.println("Please enter an integer");
                System.out.print("Input the minimum temperature for the 1st Heater: ");
            }
        }

        System.out.print("Input the maximum temperature for the 1st Heater: ");
        int maximumTemperature;

        while(true)
        {
            String input = "";

            try
            {
                input = ns.nextLine();

                if (input.contains("."))
                {
                    System.out.println("You entered: " + input + ", which is not a integer");
                    System.out.println("Please enter an integer");
                    System.out.print("Input the minimum temperature for the 1st Heater: ");
                }
                else
                {
                    maximumTemperature = Integer.parseInt(input);
                    break;
                }
            }
            catch (Exception e)
            {
                System.out.println("You entered: " + input + ", which is not a integer");
                System.out.println("Please enter an integer");
                System.out.print("Input the minimum temperature for the 1st Heater: ");
            }
        }
        
        heater1 = new Heater(minimumTemperature, maximumTemperature);
        System.out.println("The Heater 1 temperature currently is: " + heater1.getTemperature());
        heater1.cooler();
        System.out.print("Current Heater 1 temperature after the attempt to decrease the temperature is: " + heater1.getTemperature());

        String minimumTemperatureText = "";
        int minimumTemperature2;
        while(true)
        {
            String input = "";

            try
            {
                input = JOptionPane.showInputDialog("Input the minimum Temperature for 2nd Heater: ");

                if (input.contains("."))
                {
                    JOptionPane.showMessageDialog(null, "You entered: '" + input +
                            "' for the minimum Temperature for 2nd Heater, which is not a integer" +
                            "\nClick Ok to enter in a correct value for the minimum Temperature for 2nd Heater");
                }
                else
                {
                    minimumTemperature2 = Integer.parseInt(input);
                    break;
                }
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null, "You entered: '" + input +
                        "' for the minimum Temperature for 2nd Heater, which is not a integer" +
                        "\nClick Ok to enter in a correct value for the minimum Temperature for 2nd Heater");
            }
        }


        String maximumTemperatureText = "";
        int maximumTemperature2;
        while(true)
        {
            String input = "";

            try
            {
                input = JOptionPane.showInputDialog("Input the maximum Temperature for 2nd Heater: ");

                if (input.contains("."))
                {
                    JOptionPane.showMessageDialog(null, "You entered: '" + input +
                            "' for the maximum Temperature for 2nd Heater, which is not a integer" +
                            "\nClick Ok to enter in a correct value for the maximum Temperature for 2nd Heater");
                }
                else
                {
                    maximumTemperature2 = Integer.parseInt(input);
                    break;
                }
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null, "You entered: '" + input +
                        "' for the maximum Temperature for 2nd Heater, which is not a integer" +
                        "\nClick Ok to enter in a correct value for the maximum Temperature for 2nd Heater");
            }
        }
        
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
