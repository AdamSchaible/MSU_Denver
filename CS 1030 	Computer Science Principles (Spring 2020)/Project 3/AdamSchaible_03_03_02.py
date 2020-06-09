# AdamSchaible_03_03_02.py
# 3/22/20
# CS 1030

"""
This Program takes in numbers and displays the average of those numbers and which of those numbers are below the
average, above the average or at the average. The only number that the program will not accept is 0, since for
the problem that this program is based on has only one number restriction, which is that the number cannot be 0,
so all non-zero numbers are accepted.
"""

list_of_numbers = []
"""
The problem that this program is based on does not require that the user has to place a number in the list,
which if the first number that the user enters is a 0 then no numbers will be placed in the list
and their cannot be an average of an empty list.  
"""

entering_numbers_into_list = True

print("This program takes numbers and tells you the average of those numbers, which numbers are below the average, "
      "at the average and above the average for numbers that are not equal to 0.")
print("Enter the number 0 when you have entered all of numbers that you wish to enter.\n")

while entering_numbers_into_list:
    user_input = input("Enter a number: ")
    valid_number = True
    try:
        float(user_input)  # testing if a input can be converted into a float
    except:
        valid_number = False

    if valid_number:
        if user_input == "0":
            entering_numbers_into_list = False  # User requested to stop putting numbers into list
        else:
            list_of_numbers.append(user_input)
    else:
        print("You entered : " + user_input + " , which is not a number, please enter a number.")

# Done placing numbers into list named entering_numbers_into_list

if not list_of_numbers:  # list_of_numbers has no numbers put into it by user
    print("You did not enter any non-zero numbers, so I cannot give you an average of numbers or "
          "make any number comparisons with respect to an average does not exist!")

else:

    # calculation below first converts list_of_numbers to a list of float numbers before dividing by the lists size
    average = sum([float(i) for i in list_of_numbers]) / len(list_of_numbers)  # division by zero cannot happen here.

    numbers_below_average = []
    numbers_equal_to_average = []
    numbers_above_average = []

    for number in list_of_numbers:
        if float(number) > average:
            numbers_above_average.append(number)

        if float(number) == average:
            numbers_equal_to_average.append(number)

        if float(number) < average:
            numbers_below_average.append(number)

    if average.is_integer():  # if true convert the average as a float to an integer
        average = int(average)

    print("The average of all the numbers entered is : \n" + str(average) + "\n")

    """
    note: .join that is used in the lines below combines all elements in the list and places the string separator 
    ", " that is to the left of .join between each element to turn the list into a long string with each number 
    separated by a comma with a space after it.
    """

    print("The numbers that were entered that are below the average is : ")
    print(", ".join([str(number) for number in numbers_below_average]) + "\n")

    print("The numbers that were entered that are equal to the average is ")
    print(", ".join([str(number) for number in numbers_equal_to_average]) + "\n")

    print("The numbers that were entered that are above the average is : ")
    print(", ".join([str(number) for number in numbers_above_average]) + "\n")

"""    
Answer to question of allow for the possibility that there are no numbers below the average and/or no numbers above the 
average. (What does that mean about the numbers in the list?):

If there are no numbers below the average that means all numbers must be equal to or above the average.
If there are no numbers above the average then all numbers must be equal to the average or below the average. 
If there are no numbers above or below the average then all numbers must be equal to the average.
"""
