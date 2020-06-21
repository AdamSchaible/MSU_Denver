# AdamSchaible_03_03_01.py
# 3/22/20
# CS 1030


# This program displays the conversion table from Celsius to Fahrenheit

def celsius_to_fahrenheit(celcius):
    """
    Function converts Celsius to Fahrenheit and returns the fahrenheit value.
    The returned Fahrenheit value is truncated to a integer,
    the problem did not specify the format of the returned Fahrenheit value.
    """

    # Celcius to Fahrenheit derived from https://www.rapidtables.com/convert/temperature/how-celsius-to-fahrenheit.html
    return int((celcius*(9/5)) + 32)


celcius_values = [0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100]

print("Celsius to Fahrenheit")
print(" Conversion table\n")  # space added before conversion, since that is the format in problem example
print("Celcius\t\tFahrenheit")  # tab values here and in following for loop generate proper table output format
for celcius in celcius_values:
    fahrenheit = celsius_to_fahrenheit(celcius)
    print(str(celcius) + "\t\t\t" + str(fahrenheit))
