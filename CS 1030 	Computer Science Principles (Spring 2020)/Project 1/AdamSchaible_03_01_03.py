# Adam Schaible
# 2/13/2020
# CS 1030
# Python Project # 1 - AdamSchaible_03_01_03.py
# Print the color of a square on a chessboard

import sys

# Section 3.1
print("A chess board consists of columns and rows")
print("The columns are letters labeled a-h and the rows are digits labeled 1-8")
print("The bottom left square is the origin")
print("The user will enter the column followed by the row of the square with no spaces or additional characters")
print("The color of the square will be printed out to the user as white or black\n")

# Section 3.2
chessboard_Coordinate = str(input("Enter the coordinate on the chessboard to get its color (such as a8): "))

# Section 3.3
if len(chessboard_Coordinate) != 2:
    print("Error, you did not enter a coordinate that consists of only a letter and a number")
    print("You entered: " + chessboard_Coordinate)
    sys.exit(0)
column = chessboard_Coordinate[0]
row = chessboard_Coordinate[1]

acceptable_Columns = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h']
acceptable_Rows = [1, 2, 3, 4, 5, 6, 7, 8]

columns_Ok = 0
for some_Column in acceptable_Columns:
    if some_Column == column:
        columns_Ok = 1

rows_Ok = 0
if row.isnumeric():
    for some_Row in acceptable_Rows:
        if some_Row == int(row):
            rows_Ok = 1

if columns_Ok != 1:
    print("You have entered an invalid column, column is not in range of a-h")

if rows_Ok != 1:
    print("You have entered an invalid row, row is not in range of 1-8")

if (columns_Ok != 1) or (rows_Ok != 1):
    print("You entered: " + chessboard_Coordinate)
    sys.exit(0)

# Section 3.4
columns_With_Bottom_Black_Square = ['a', 'c', 'e', 'g']
bottom_Color_Of_Color = "white"  # white is the default

for some_Column in columns_With_Bottom_Black_Square:
    if some_Column == column:
        bottom_Color_Of_Color = 'black'

black_Bottom_Column_From_Bottom_To_Top = ['black', 'white', 'black', 'white', 'black', 'white', 'black', 'white']
color_Of_Square = ""

if bottom_Color_Of_Color == 'black':
    color_Of_Square = black_Bottom_Column_From_Bottom_To_Top[int(row) - 1]
else:
    temp_Color = black_Bottom_Column_From_Bottom_To_Top[int(row) - 1]
    if temp_Color == "white":
        color_Of_Square = "black"
    else:
        color_Of_Square = "white"

print("Square " + chessboard_Coordinate + " is " + color_Of_Square)

# section 3.5 tested different invalid squares
# section 3.6 program exits on its own after running code
