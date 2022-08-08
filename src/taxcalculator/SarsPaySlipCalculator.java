
package taxcalculator;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author C
 */
public class SarsPaySlipCalculator {

    public static void main(String[] args) {
        // new Calculations().cmd_doCalculation(2023, 29, 16910.0);
        
        boolean running = true;
        System.out.println("Payslip Calculator");
        
        while(running) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                
                System.out.println("Which tax year would you like to calculate? ");
                int tax_year = Integer.parseInt(reader.readLine());

                System.out.println("How much do you earn before deductions? ");
                double taxable_earnings = Double.parseDouble(reader.readLine());

                System.out.println("How young are you? ");
                int age = Integer.parseInt(reader.readLine());

                new Calculations().cmd_doCalculation(tax_year, age, taxable_earnings);

                System.out.println("Would you like to calculate again? Y / N ");
                if(reader.readLine().toUpperCase().equals("N")) {
                        running = false;
                }
            } catch(Exception error) {
            }
        }
    }
    
}
