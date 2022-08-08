
package taxcalculator;

/**
 *
 * @author C
 */
public class Calculations {

    double[][] bracket_2023 = {
        { 0.0,        226000.0,   0.0,       0.18 },
        { 226000.00,  353100.00,  40680.00,  0.26 },
        { 353100.00,  488700.00,  73726.00,  0.31 },
        { 488700.00,  641400.00,  115762.00, 0.36 }, // Our problem occurs here 639000 -> 53250pm
        { 641400.00,  817600.00,  170734.00, 0.39 },
        { 817600.00,  1731600.00, 239452.00, 0.41 },
        { 1731600.00, -1.0,       614192.00, 0.45 }
    };

    double[][] bracket_2022 = {
        { 0.0,        216200.00,  0.0,       0.18 },
        { 216200.00,  337800.00,  38916.00,  0.26 },
        { 337800.00,  467500.00,  70532.00,  0.31 },
        { 467500.00,  613600.00,  110739.00, 0.36 },
        { 613600.00,  782200.00,  163335.00, 0.39 },
        { 782200.00,  1656600.00, 229089.00, 0.41 },
        { 1656600.00, -1.0,       587593.00, 0.45 }
    };

    double[][] bracket_2021 = {
        { 0.0,        205900,     0.0,       0.18 },
        { 205900.00,  321600.00,  37062.00,  0.26 },
        { 321600.00,  445100.00,  67144.00,  0.31 },
        { 445100.00,  584200.00,  105429.00, 0.36 },
        { 584200.00,  744800.00,  155505.00, 0.39 },
        { 744800.00,  1577300.00, 218139.00, 0.41 },
        { 1577300.00, -1.0,       559464.00, 0.45 }
    };


    double[][] rebates = {
        // 2023
        { 16425.00, 9000.00, 2997.00 },

        // 2022
        { 15714.00, 8613.00, 2871.00},

        // 2021
        { 14958.00, 8199.00, 2736.00}
    };


    double[][] tax_thresholds = {
        // 2023
        { 91250.00, 141250.00, 157900.00 },

        // 2022
        { 87300.00, 135150.00, 151100.00 },

        // 2021
        { 83100.00, 128650.00, 143850.00 }
    };
    
    
     public double get_uif(double salary) {
            var uif_monthly = 0.0;
            if((salary * 0.01) >= 177.12) {
                uif_monthly = 177.12;
            } else {
                uif_monthly = salary * 0.01;
            }
            System.out.println("UIF: " + uif_monthly);
            return uif_monthly;
        }


    public double  get_rebate(int year, int age) {
        double rebate = 0.0;
        switch(year) {
            case 2023:
                if(age < 65) {
                    rebate = rebates[0][0];
                } else {
                    if(age >= 65 && age < 75) {
                        rebate = rebates[0][0] + rebates[0][1];
                    } else {
                        if(age >= 75) {
                            rebate = rebates[0][0] + rebates[0][1] + rebates[0][2];
                        }
                    }
                }
                break;

            case 2022:
            if(age < 65) {
                    rebate = rebates[1][0];
                } else {
                    if(age >= 65 && age < 75) {
                        rebate = rebates[1][0] + rebates[1][1];
                    } else {
                        if(age >= 75) {
                            rebate = rebates[1][0] + rebates[1][1] + rebates[1][2];
                        }
                    }
                }
                break;

            case 2021:
                if(age < 65) {
                        rebate = rebates[2][0];
                } else {
                    if(age >= 65 && age < 75) {
                        rebate = rebates[2][0] + rebates[2][1];
                    } else {
                        if(age >= 75) {
                            rebate = rebates[2][0] + rebates[2][1] + rebates[2][2];
                        }
                    }
                }
                break;

            default:
                System.out.println("Rebate Default: " + year + " " + age);
        }
        System.out.println("Rebate for year: " + year + " R" + rebate);
        return rebate;
    }


    public double get_paye(int year, int age, double income) {
        double paye = 0.0;
        double income_annually = income * 12;
        double rebate = get_rebate(year, age);

        //    Min         Max         Rate       %
        //  [ 205900.00,  321600.00,  37062.00,  0.26 ],

        switch(year) {
            case 2023:
                System.out.println(bracket_2023.length);
                for(int i = 0; i < bracket_2023.length; i++) {
                    if(income_annually >= bracket_2023[i][0] && income_annually <= bracket_2023[i][1]) {
                        paye = (bracket_2023[i][2] + ((bracket_2023[i][3] * (income_annually - bracket_2023[i][0])) - rebate)) / 12;
                    } else {
                        if(income_annually > bracket_2023[i][0]) {
                            paye = (bracket_2023[i][2] + ((bracket_2023[i][3] * (income_annually - bracket_2023[i][0])) - rebate)) / 12;
                        }
                    }
                }    
                break;

            case 2022:
                for(int i = 0; i < bracket_2022.length; i++) {
                    if(income_annually >= bracket_2022[i][0] && income_annually <= bracket_2022[i][1]) {
                        paye = (bracket_2022[i][2] + ((bracket_2022[i][3] * (income_annually - bracket_2022[i][0])) - rebate)) / 12;
                    } else {
                        if(income_annually > bracket_2023[i][0]) {
                            paye = (bracket_2022[i][2] + ((bracket_2022[i][3] * (income_annually - bracket_2022[i][0])) - rebate)) / 12;
                        }
                    }
                }    
                break;

            case 2021:
                for(int i = 0; i < bracket_2021.length; i++) {
                    if(income_annually >= bracket_2021[i][0] && income_annually <= bracket_2021[i][1]) {
                        paye = (bracket_2021[i][2] + ((bracket_2021[i][3] * (income_annually - bracket_2021[i][0])) - rebate)) / 12;
                    } else {
                        if(income_annually > bracket_2021[i][0]) {
                            paye = (bracket_2021[i][2] + ((bracket_2021[i][3] * (income_annually - bracket_2021[i][0])) - rebate)) / 12;
                        }
                    }
                }   
                break;

            default:
                System.out.println("Default PAYE: " + year + " " + paye);
        }

        if(paye >= 0) {
            System.out.println("PAYE for year: " + year + " " + paye);
            return paye;
        } else {
            System.out.println("PAYE for year: " + year + " was not needed" + paye);
            return 0.0;
        }
    }


    public void cmd_doCalculation(int year, int age, double income) {
        System.out.println("Year: " + year);
        System.out.println("Age: " + age);
        System.out.println("Income: " + income);
        System.out.println("Take home: " + (income - get_paye(year, age, income) - get_uif(income)));
    }
    
}
