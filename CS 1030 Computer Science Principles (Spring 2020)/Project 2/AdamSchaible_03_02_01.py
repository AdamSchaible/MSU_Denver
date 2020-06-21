# Adam Schaible
# CS 1030 - 003
# Assignment # 2

# AdamSchaible_03_02_01.py

grades = ["A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "F"]
points = [4.2, 4.0, 3.9, 3.7, 3.2, 3.0, 2.8, 2.2, 2.0, 1.8, 1.2, 0]

quit = False
while not quit:
    response = input("If you would like to quit enter 'yes' here: ")  # section 1.1
    if response == "yes":
        break
    line = " "

    points_total = 0
    number_of_grades = 0
    grades_entered = []
    while line != "":  # section 1.2
        line = input("Enter the next grade: ")  # section 1.3

        if line not in grades:
            print("You entered the invalid grade of " + line)
            print("Valid grades are : A+, A, A-, B+, B, B-, C+, C, C-, D+, D, F")
        else:
            points_total += points[grades.index(line)]  # section 1.4
            number_of_grades += 1
            grades_entered.append(line)

        if line == "" and number_of_grades == 0:  # section 1.5
            print("No GPA calculated")
            line = " "
    for grade in grades_entered:
        print("The grade " + grade + " has a GPA of " + str(points[grades.index(grade)]))

    overall_average = points_total/number_of_grades  # section 1.6
    print("The overall average of all GPA's that were processed is : " + str(format(overall_average, '.2f')))

    # The information required to produce the overall average is the sum of all the grades after
    # they have been converted to GPA along with a tally of how many grades there were


