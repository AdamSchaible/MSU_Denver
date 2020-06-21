# Adam Schaible
# 2/13/2020
# CS 1030
# Python Project # 1 - AdamSchaible_03_01_02.py
# Converting miles per gallon to kilometers per liter

import sys

miles_Per_Gallon = float(input("Enter the number of miles per gallon: "))  # section 2.1

if miles_Per_Gallon > 0:  # section 2.2
    pass
else:
    sys.exit(0)

# sections 2.3, 2.4 combined
kilometers_Per_Mile = 1.609344/1  # source https://duckduckgo.com/
gallons_Per_Liter = 0.2641729/1  # source https://duckduckgo.com/
kilometers_Per_Liter = miles_Per_Gallon * kilometers_Per_Mile * gallons_Per_Liter

# section 2.5
print("Miles per gallon: " + format(miles_Per_Gallon, '.1f'))
print("Kilometers per liter: " + format(kilometers_Per_Liter, '.1f'))
# section 2.6 program exits automatically at end of code
