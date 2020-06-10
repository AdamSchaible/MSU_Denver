//Name Adam Schaible
//CS 4050
//Programming assignment # 3
import java.util.Random;

public class MonteCarloIntegration
{
    public static void main(String[] args)
    {
        //x boundaries
        int xLeftBoundary = -3;
        int xRightBoundary = 3;

        //time
        long start, end;

        //y boundaries derived with T1-83 calculator minimum and maximum function

        //y boundaries of xCubedFormula
        double yOfCubedXMaximum = 27;
        double yOfCubedXMinimum = -27;

        //y boundaries of xSquaredMinusXtoTheThird
        double yOfXSquaredMinusXtoTheThirdMaximum = 36;
        double yOfXSquaredMinusXtoTheThirdMinimum = -18;

        //y boundaries of negativeAbsoluteValueOfX
        double yNegativeAbsoluteValueOfXMaximum = 0;
        double yNegativeAbsoluteValueOfXMinimum = -3;
        MonteCarloIntegration integrate = new MonteCarloIntegration();

        System.out.println("In this section the number of darts, random values and y values in trapezoid rule = 1,000 ................................................\n");
        //DART THROWING SECTION---------------------------------------------------------------------------
        //Calculating x^3 integral by dart throwing
        start = System.nanoTime();
        float xCubedIntegral = integrate.dartThrowing(1000,xLeftBoundary,xRightBoundary,yOfCubedXMinimum,yOfCubedXMaximum,"xCubedFormula");
        end = System.nanoTime();
        System.out.println("The estimated integral by dart throwing of x^3 from x (" + xLeftBoundary + ", " + xRightBoundary + ") is: " + xCubedIntegral + ", \nand the time spent doing this in milliseconds = " + (end - start) + "\n");

        //Calculating x^2 - x^3 integral by dart throwing
        start = System.nanoTime();
        float xSquaredMinusXCubedIntegral = integrate.dartThrowing(1000,xLeftBoundary,xRightBoundary,yOfXSquaredMinusXtoTheThirdMinimum,yOfXSquaredMinusXtoTheThirdMaximum,"xSquaredMinusXtoTheThird");
        end = System.nanoTime();
        System.out.println("The estimated integral by dart throwing of x^2 - x^3 from x (" + xLeftBoundary + ", " + xRightBoundary + ") is: " + xSquaredMinusXCubedIntegral + ", \nand the time spent doing this in milliseconds = " + (end - start) + "\n");

        //Calculating negative absolute value of x's integral by dart throwing
        start = System.nanoTime();
        float negativeAbsoluteOfXIntegral = integrate.dartThrowing(1000,xLeftBoundary,xRightBoundary,yNegativeAbsoluteValueOfXMinimum,yNegativeAbsoluteValueOfXMaximum,"negativeAbsoluteValueOfX");
        end = System.nanoTime();
        System.out.println("The estimated integral by dart throwing on the negative absolute value of x (" + xLeftBoundary + ", " + xRightBoundary + ") is: " + negativeAbsoluteOfXIntegral + ", \nand the time spent doing this in milliseconds= " + (end - start) + "\n");

        //INTEGRAL BY TAKING THE AVERAGE OF THE RANDOM Y VALUES SECTION----------------------------------------
        //Calculating x^3 integral by the mean of values at random locations in the interval
        start = System.nanoTime();
        xCubedIntegral = integrate.IntegralUsingMeanYValueAtRandomLocations(1000,xLeftBoundary,xRightBoundary,"xCubedFormula");
        end = System.nanoTime();
        System.out.println("The estimated integral by the mean of values at random locations of x^3 from x (" + xLeftBoundary + ", " + xRightBoundary + ") is: " + xCubedIntegral + ", \nand the time spent doing this in milliseconds= " + (end - start) + "\n");

        //Calculating x^2 - x^3 integral by the mean of values at random locations in the interval
        start = System.nanoTime();
        xSquaredMinusXCubedIntegral = integrate.IntegralUsingMeanYValueAtRandomLocations(1000,xLeftBoundary,xRightBoundary,"xSquaredMinusXtoTheThird");
        end = System.nanoTime();
        System.out.println("The estimated integral by the mean of values at random locations of x^2 - x^3 from x (" + xLeftBoundary + ", " + xRightBoundary + ") is: " + xSquaredMinusXCubedIntegral + ", \nand the time spent doing this in milliseconds = " + (end - start) + "\n");

        //Calculating negative absolute value of x's integral by the mean of values at random locations in the interval
        start = System.nanoTime();
        negativeAbsoluteOfXIntegral = integrate.IntegralUsingMeanYValueAtRandomLocations(1000,xLeftBoundary,xRightBoundary,"negativeAbsoluteValueOfX");
        end = System.nanoTime();
        System.out.println("The estimated integral by taking the mean of values at random locations of on the negative absolute value of x (" + xLeftBoundary + ", " + xRightBoundary + ") is: " + negativeAbsoluteOfXIntegral + ", \nand the time spent doing this in milliseconds = " + (end - start) + "\n");

        //INTEGRAL BY USING THE TRAPEZOIDAL RULE SECTION----------------------------------------
        //Calculating x^3 integral by the mean of values at random locations in the interval
        start = System.nanoTime();
        xCubedIntegral = integrate.IntegralUsingTrapezoidRule(1000,xLeftBoundary,xRightBoundary,"xCubedFormula");
        end = System.nanoTime();
        System.out.println("The estimated integral by using the trapezoidal rule on x^3 from x (" + xLeftBoundary + ", " + xRightBoundary + ") is: " + xCubedIntegral + ", \nand the time spent doing this in milliseconds = " + (end - start) + "\n");

        //Calculating x^2 - x^3 integral by the mean of values at random locations in the interval
        start = System.nanoTime();
        xSquaredMinusXCubedIntegral = integrate.IntegralUsingTrapezoidRule(1000,xLeftBoundary,xRightBoundary,"xSquaredMinusXtoTheThird");
        end = System.nanoTime();
        System.out.println("The estimated integral by using the trapezoidal rule on x^2 - x^3 from x (" + xLeftBoundary + ", " + xRightBoundary + ") is: " + xSquaredMinusXCubedIntegral + ", \nand the time spent doing this in milliseconds = " + (end - start) + "\n");

        //Calculating negative absolute value of x's integral by the mean of values at random locations in the interval
        start = System.nanoTime();
        negativeAbsoluteOfXIntegral = integrate.IntegralUsingTrapezoidRule(1000,xLeftBoundary,xRightBoundary,"negativeAbsoluteValueOfX");
        end = System.nanoTime();
        System.out.println("The estimated integral by using the trapezoidal rule on the negative absolute value of x (" + xLeftBoundary + ", " + xRightBoundary + ") is: " + negativeAbsoluteOfXIntegral + ", \nand the time spent doing this in milliseconds = " + (end - start) + "\n");
        System.out.println("End of the 1000 sample section----------------------------------------\n\n\n");




        System.out.println("In this section the number of darts, random values and y values in trapezoid rule = 100,000,000 .......................................\n");
        //DART THROWING SECTION---------------------------------------------------------------------------
        //Calculating x^3 integral by dart throwing
        start = System.nanoTime();
        xCubedIntegral = integrate.dartThrowing(100000000,xLeftBoundary,xRightBoundary,yOfCubedXMinimum,yOfCubedXMaximum,"xCubedFormula");
        end = System.nanoTime();
        System.out.println("The estimated integral by dart throwing of x^3 from x (" + xLeftBoundary + ", " + xRightBoundary + ") is: " + xCubedIntegral + ", \nand the time spent doing this in milliseconds = " + (end - start) + "\n");

        //Calculating x^2 - x^3 integral by dart throwing
        start = System.nanoTime();
        xSquaredMinusXCubedIntegral = integrate.dartThrowing(100000000,xLeftBoundary,xRightBoundary,yOfXSquaredMinusXtoTheThirdMinimum,yOfXSquaredMinusXtoTheThirdMaximum,"xSquaredMinusXtoTheThird");
        end = System.nanoTime();
        System.out.println("The estimated integral by dart throwing of x^2 - x^3 from x (" + xLeftBoundary + ", " + xRightBoundary + ") is: " + xSquaredMinusXCubedIntegral + ", \nand the time spent doing this in milliseconds = " + (end - start) + "\n");

        //Calculating negative absolute value of x's integral by dart throwing
        start = System.nanoTime();
        negativeAbsoluteOfXIntegral = integrate.dartThrowing(100000000,xLeftBoundary,xRightBoundary,yNegativeAbsoluteValueOfXMinimum,yNegativeAbsoluteValueOfXMaximum,"negativeAbsoluteValueOfX");
        end = System.nanoTime();
        System.out.println("The estimated integral by dart throwing on the negative absolute value of x (" + xLeftBoundary + ", " + xRightBoundary + ") is: " + negativeAbsoluteOfXIntegral + ", \nand the time spent doing this in milliseconds= " + (end - start) + "\n");

        //INTEGRAL BY TAKING THE AVERAGE OF THE RANDOM Y VALUES SECTION----------------------------------------
        //Calculating x^3 integral by the mean of values at random locations in the interval
        start = System.nanoTime();
        xCubedIntegral = integrate.IntegralUsingMeanYValueAtRandomLocations(100000000,xLeftBoundary,xRightBoundary,"xCubedFormula");
        end = System.nanoTime();
        System.out.println("The estimated integral by the mean of values at random locations of x^3 from x (" + xLeftBoundary + ", " + xRightBoundary + ") is: " + xCubedIntegral + ", \nand the time spent doing this in milliseconds= " + (end - start) + "\n");

        //Calculating x^2 - x^3 integral by the mean of values at random locations in the interval
        start = System.nanoTime();
        xSquaredMinusXCubedIntegral = integrate.IntegralUsingMeanYValueAtRandomLocations(100000000,xLeftBoundary,xRightBoundary,"xSquaredMinusXtoTheThird");
        end = System.nanoTime();
        System.out.println("The estimated integral by the mean of values at random locations of x^2 - x^3 from x (" + xLeftBoundary + ", " + xRightBoundary + ") is: " + xSquaredMinusXCubedIntegral + ", \nand the time spent doing this in milliseconds = " + (end - start) + "\n");

        //Calculating negative absolute value of x's integral by the mean of values at random locations in the interval
        start = System.nanoTime();
        negativeAbsoluteOfXIntegral = integrate.IntegralUsingMeanYValueAtRandomLocations(100000000,xLeftBoundary,xRightBoundary,"negativeAbsoluteValueOfX");
        end = System.nanoTime();
        System.out.println("The estimated integral by taking the mean of values at random locations on the negative absolute value of x (" + xLeftBoundary + ", " + xRightBoundary + ") is: " + negativeAbsoluteOfXIntegral + ", \nand the time spent doing this in milliseconds = " + (end - start) + "\n");

        //INTEGRAL BY USING THE TRAPEZOIDAL RULE SECTION----------------------------------------
        //Calculating x^3 integral by the mean of values at random locations in the interval
        start = System.nanoTime();
        xCubedIntegral = integrate.IntegralUsingTrapezoidRule(100000000,xLeftBoundary,xRightBoundary,"xCubedFormula");
        end = System.nanoTime();
        System.out.println("The estimated integral by using the trapezoidal rule on x^3 from x (" + xLeftBoundary + ", " + xRightBoundary + ") is: " + xCubedIntegral + ", \nand the time spent doing this in milliseconds = " + (end - start) + "\n");

        //Calculating x^2 - x^3 integral by the mean of values at random locations in the interval
        start = System.nanoTime();
        xSquaredMinusXCubedIntegral = integrate.IntegralUsingTrapezoidRule(100000000,xLeftBoundary,xRightBoundary,"xSquaredMinusXtoTheThird");
        end = System.nanoTime();
        System.out.println("The estimated integral by using the trapezoidal rule on x^2 - x^3 from x (" + xLeftBoundary + ", " + xRightBoundary + ") is: " + xSquaredMinusXCubedIntegral + ", \nand the time spent doing this in milliseconds = " + (end - start) + "\n");

        //Calculating negative absolute value of x's integral by the mean of values at random locations in the interval
        start = System.nanoTime();
        negativeAbsoluteOfXIntegral = integrate.IntegralUsingTrapezoidRule(100000000,xLeftBoundary,xRightBoundary,"negativeAbsoluteValueOfX");
        end = System.nanoTime();
        System.out.println("The estimated integral by using the trapezoidal rule on the negative absolute value of x (" + xLeftBoundary + ", " + xRightBoundary + ") is: " + negativeAbsoluteOfXIntegral + ", \nand the time spent doing this in milliseconds = " + (end - start) + "\n");
        System.out.println("End of the 100,000,000 sample section----------------------------------------");




    }

    public double xCubedFormula(double input)
    {
        return input*input*input;
    }

    public double xSquaredMinusXtoTheThird(double input)
    {
        return (input*input - input*input*input);
    }

    public double negativeAbsoluteValueOfX(double input)
    {
        return -1*Math.abs(input);
    }

    public float dartThrowing(int numberOfDots, int xLeft, int xRight, double yMin, double yMax, String formulaName)
    {
        MonteCarloIntegration integration = new MonteCarloIntegration();
        double x, y; //coordinates
        float integral = 0; //the net area under the curve
        int mAboveXAxis = 0; //the number of dots that land under the function above x axis
        int mBelowXAxis = 0; //the number of dots that land under the function below x axis

        Random num = new Random();

        for (int i = 0; i < numberOfDots; i++)
        {
            x = num.nextDouble()*(xRight - xLeft) + xLeft;// + (xRight + xLeft); //generates random x values for the rectangle, next double generates values between 0.0 and 1.0
            y = num.nextDouble()*(yMax - yMin) + yMin; //generates random x values for the rectangle

            if (formulaName.equals("xCubedFormula"))
            {
                //System.out.println("I am trying coordinates : " + x + ", " + y);
                if((integration.xCubedFormula(x) < y) && (y < 0)) //integrating below x-axis
                {
                    mBelowXAxis--;
                    //System.out.println("I think that (" + x + ", " + y + ") is negatively integrated");
                }

                if((integration.xCubedFormula(x) > y) && (y > 0)) //integrating above x-axis
                {
                    mAboveXAxis++;
                    //System.out.println("I think that (" + x + ", " + y + ") is positively integrated");
                }
            }

            if (formulaName.equals("xSquaredMinusXtoTheThird"))
            {
                if((integration.xSquaredMinusXtoTheThird(x) < y) && (y < 0)) //integrating below x-axis
                {
                    mBelowXAxis--;
                }

                if((integration.xSquaredMinusXtoTheThird(x) > y) && (y > 0)) //integrating above x-axis
                {
                    mAboveXAxis++;
                }
            }

            if (formulaName.equals("negativeAbsoluteValueOfX"))
            {
                if((integration.negativeAbsoluteValueOfX(x) < y) && (y < 0)) //integrating below x-axis
                {
                    mBelowXAxis--;
                }

                if((integration.negativeAbsoluteValueOfX(x) > y) && (y > 0)) //integrating above x-axis
                {
                    mAboveXAxis++;
                }
            }
        }
        float mAbove = mAboveXAxis; //mAbove temporarily holder of mAboveXAxis
        float mBelow = mBelowXAxis; //mBelow temporarily holder of mBelowXAxis
        //System.out.println("NUMBER OF DOTS ABOVE X AXIS : " + mAbove);
        //System.out.println("NUMBER OF DOTS BELOW X AXIS : " + mBelow);

        float numberOfDots2 = numberOfDots; //numberOfDots2 temporarily holder of numberOfDots

        float areaOfRectangle = (float)((xRight - xLeft)*(yMax - yMin));
        float areaAboveXAxis = areaOfRectangle*(mAbove/numberOfDots2);
        float areaBelowXAxis = areaOfRectangle*(mBelow/numberOfDots2);
        //System.out.println("Area above X AXIS: " + areaAboveXAxis);
        //System.out.println("Area above X AXIS: " + areaBelowXAxis);
        integral = areaAboveXAxis + areaBelowXAxis;
        return  integral;
    }

    public float IntegralUsingMeanYValueAtRandomLocations(int numberOfDots, int xLeft, int xRight, String formulaName)
    {
        MonteCarloIntegration integration2 = new MonteCarloIntegration();
        double x, yTotal; //coordinates
        yTotal = 0;
        Random num = new Random();

        for (int i = 0; i < numberOfDots; i++)
        {
            x = num.nextDouble()*(xRight - xLeft) + xLeft;// + (xRight + xLeft); //generates random x values for the rectangle, next double generates values between 0.0 and 1.0

            if (formulaName.equals("xCubedFormula"))
            {
                yTotal = yTotal + integration2.xCubedFormula(x);
            }

            if (formulaName.equals("xSquaredMinusXtoTheThird"))
            {
                yTotal = yTotal + integration2.xSquaredMinusXtoTheThird(x);
            }

            if (formulaName.equals("negativeAbsoluteValueOfX"))
            {
                yTotal =yTotal + integration2.negativeAbsoluteValueOfX(x);
            }
        }

        float numberOfDots2 = numberOfDots; //numberOfDots2 temporarily holder of numberOfDots
        float yTotalTemp = (float) yTotal;
        float yValueAverage = yTotalTemp/numberOfDots;

        float areaOfRectangle = (xRight - xLeft)*yValueAverage;

        return areaOfRectangle;
    }

    public float IntegralUsingTrapezoidRule(int numberOfYValuesToGet, int xLeft, int xRight, String formulaName)
    {
        MonteCarloIntegration integration3 = new MonteCarloIntegration();

        double[] yValueArray = new double[numberOfYValuesToGet];
        double areaTotal = 0;
        double range = xRight - xLeft;
        double yTotal = 0;

        for (int i = 0; i < numberOfYValuesToGet; i++)
        {
            if (formulaName.equals("xCubedFormula"))
            {
                if (i == 0)
                {
                    yValueArray[i] = 0.5*integration3.xCubedFormula(xLeft);
                }

                if (i == (numberOfYValuesToGet - 1))
                {
                    yValueArray[i] = 0.5*integration3.xCubedFormula(xRight);
                }

                if (!(i == (numberOfYValuesToGet - 1)) && !(i == 0))
                {
                    double iTemp = i;
                    double offset = range*(iTemp/(numberOfYValuesToGet - 1));
                    double xValueToUse = xLeft + offset;

                    yValueArray[i] = integration3.xCubedFormula(xValueToUse);

                }
            }

            if (formulaName.equals("xSquaredMinusXtoTheThird"))
            {
                if (i == 0)
                {
                    yValueArray[i] = 0.5*integration3.xSquaredMinusXtoTheThird(xLeft);
                }

                if (i == (numberOfYValuesToGet - 1))
                {
                    yValueArray[i] = 0.5*integration3.xSquaredMinusXtoTheThird(xRight);
                }

                if (!(i == (numberOfYValuesToGet - 1)) && !(i == 0))
                {
                    double iTemp = i;
                    double offset = range*(iTemp/(numberOfYValuesToGet - 1));
                    double xValueToUse = xLeft + offset;

                    yValueArray[i] = integration3.xSquaredMinusXtoTheThird(xValueToUse);
                }
            }

            if (formulaName.equals("negativeAbsoluteValueOfX"))
            {

                if (i == 0)
                {
                    yValueArray[i] = 0.5*integration3.negativeAbsoluteValueOfX(xLeft);
                }

                if (i == (numberOfYValuesToGet - 1))
                {
                    yValueArray[i] = 0.5*integration3.negativeAbsoluteValueOfX(xRight);
                }

                if (!(i == (numberOfYValuesToGet - 1)) && !(i == 0))
                {
                    double iTemp = i;
                    double offset = range*(iTemp/(numberOfYValuesToGet - 1));
                    double xValueToUse = xLeft + offset;

                    yValueArray[i] = integration3.negativeAbsoluteValueOfX(xValueToUse);
                }


            }
        }

        for (int i = 0; i < numberOfYValuesToGet; i++)
        {
            yTotal = yTotal + yValueArray[i];
        }

        double interval = (double)((float)1/(float)(numberOfYValuesToGet - 1));
        double deltaX = range*interval;

        areaTotal = deltaX*yTotal;


        return (float) areaTotal;
    }
}
