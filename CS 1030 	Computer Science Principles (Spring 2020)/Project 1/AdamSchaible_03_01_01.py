# Adam Schaible
# 2/13/2020
# CS 1030
# Python Project # 1 - AdamSchaible_03_01_01.py - Converting a Height to Meters
import sys

feet = input("Enter the feet: ")  # section 1.1

if feet == "":  # section 1.2
    sys.exit(0)

inches = input("Enter the inches: ")  # section 1.3

if inches == "":  # 1.3 continued
    sys.exit(0)

feet = int(feet)  # section 1.4 - note 12 inches per foot
inches = int(inches)
total_Inches = (12*feet) + inches

if total_Inches >= 96:
    print("This person is really tall")

centimeters = total_Inches * 2.54  # section 1.5 - note 2.54 inches to centimeter

meters = round((centimeters/100), 2)  # section 1.6 - note 100 cm per 1 meter

print("The original height is: " + "feet: " + format(feet, '.2f') + " inches: " + format(inches, '.2f'))  # section 1.7
print("The equivalent height in meters is: " + format(meters, '.2f'))
# section 1.8 program exits automatically after last line of code
