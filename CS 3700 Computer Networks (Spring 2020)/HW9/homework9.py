# Name Adam Schaible
# Date 4/25/20
# CS 3700

# Homework 9 - Dijkstra's Algorithm
import sys
number_of_routers = -1

# 1
while not number_of_routers >= 2:

    routers = input("Enter the number of routers that are in the network (2 routers minimum): ")

    if routers.isnumeric() and not routers.__contains__("."):
        if int(routers) >= 2:
            number_of_routers = int(routers)
        else:
            print("You entered (" + routers + "), for the number of routers")
            print("Please enter a whole number of 2 or greater for the number of routers\n")
    else:
        print("You entered (" + routers + "), for the number of routers")
        print("Please enter a whole number of 2 or greater for the number of routers\n")

# 2 -----------------------------------------------------------------------------------------------------------------
# initializing cost_matrix as -1 for infinity
cost_matrix = [[-1 for x in range(number_of_routers)] for y in range(number_of_routers)]  # cost_matrix[row][column]


def read_cost_file(file_name):
    error_found = False
    with open(file_name) as matrix_file:
        counter = 1
        for line in matrix_file:
            line_with_newline_removed = line.replace("\n", "")
            split_line = line_with_newline_removed.split("\t")
            while split_line[-1] == "":
                split_line.pop()
            if len(split_line) == 3:

                # ERROR CHECKING SECTION ==============================================

                # checking split_line[0] for errors

                if not split_line[0].isnumeric() or split_line[0].__contains__("."):
                    error_found = True
                elif int(split_line[0]) < 0 or int(split_line[0]) >= number_of_routers:
                    error_found = True

                # checking split_line[1] for errors
                if not split_line[1].isnumeric() or split_line[1].__contains__("."):
                    error_found = True
                elif int(split_line[1]) < 0 or int(split_line[1]) >= number_of_routers:
                    error_found = True

                # checking split_line[2] for errors
                if not split_line[2].isnumeric() or split_line[2].__contains__("."):
                    error_found = True
                elif int(split_line[2]) < 1:
                    error_found = True

                if not error_found:
                    cost_matrix[int(split_line[0])][int(split_line[1])] = int(split_line[2])
                    cost_matrix[int(split_line[1])][int(split_line[0])] = int(split_line[2])
                # END OF ERROR CHECKING SECTION ========================================

            if error_found:
                print("Error found in row : " + line.replace("\n", ""))
                print("At line : " + str(counter) + " of the cost input file\n")
                break
            counter = counter + 1

    matrix_file.close()
    return error_found


error_in_cost_file = read_cost_file("topo.txt")
while error_in_cost_file:
    cost_matrix = [[-1 for x in range(number_of_routers)] for y in range(number_of_routers)]  # reinitializing
    error_in_cost_file = False  # resetting
    name = input("Enter the name of a cost input file, such as (topo.txt) : ")
    error_in_cost_file = read_cost_file(name)  # ignore without usage error pycharm generates

for num in range(0, number_of_routers):
    cost_matrix[num][num] = 0  # the distance to the same router = 0

# printing the cost matrix
print("\nCost Matrix: ")
print("infinity = *")

sys.stdout.write("  ")
column_names = " "
for num in range(0, number_of_routers):
    column_names = column_names + "V" + str(num) + " "
print(column_names)

row_counter = 0
for x in cost_matrix:
    sys.stdout.write("V" + str(row_counter) + " ")
    print(str(x).replace("-1", "*"))
    row_counter = row_counter + 1
# end of printing the cost matrix

# 3 ----------------------------------------------------------------------------------------------------
# Initialization
nPrime = []
nPrime.append("V0")  # aka N', starts off by containing source router
yPrime = []  # aka Y', initially empty
dVectorD = cost_matrix[0]  # aka distance vector D
p = ['-' for x in range(number_of_routers)]  # predecessor node vector

counter = 0
for i in dVectorD:
    if i > 0:  # covers the "if I is adjacent to u"
        p[counter] = "V0"
    counter = counter + 1

# printing current values
print("\nValues after initialization")
print("N' = " + str(nPrime).replace("'", ''))
print("Y' = " + str(yPrime).replace("'", ''))
print("Distance Vector D = " + str(dVectorD).replace("-1", "infinity"))
print("Predecessor vector P = " + str(p).replace("'", ''))

# looping through Dijkstra's algorithm

iteration = 1

while len(nPrime) < number_of_routers:
    counter = 0
    minimum = None
    counter_value_at_minimum = 0

    while counter < number_of_routers:
        if not 'V' + str(counter) in nPrime:

            if not dVectorD[counter] == -1 and minimum is None and not dVectorD[counter] == 0:
                minimum = dVectorD[counter]
                counter_value_at_minimum = counter
            elif not dVectorD[counter] == -1 and minimum is not None and not dVectorD[counter] == 0:
                if dVectorD[counter] < minimum:
                    minimum = dVectorD[counter]
                    counter_value_at_minimum = counter
        counter = counter + 1

    if minimum is not None:

        kIndex = counter_value_at_minimum
        nPrime.append("V" + str(kIndex))  # add node k to N'
        yPrime.append([p[kIndex], 'V' + str(kIndex)])  # add edge (p(k), k) to Y'

        i = 0
        while i < number_of_routers:
            if not 'V' + str(i) in nPrime and cost_matrix[kIndex][i] > 0:
                if dVectorD[kIndex] + cost_matrix[kIndex][i] < dVectorD[i] or dVectorD[i] == -1:
                    dVectorD[i] = dVectorD[kIndex] + cost_matrix[kIndex][i]
                    p[i] = 'V' + str(kIndex)
            i = i + 1

    else:
        print("OPPS, no minimum was found in distance vector D")

    print("\nValues after iteration # " + str(iteration))
    print("N' = " + str(nPrime).replace("'", ''))
    print("Y' = " + str(yPrime).replace("'", ''))
    print("Distance Vector D = " + str(dVectorD).replace("-1", "infinity"))
    print("Predecessor vector P = " + str(p).replace("'", '') + "\n")
    iteration = iteration + 1

fwdTable = []

for i in nPrime:
    if not i == "V0":
        j = i
        jIndex = int(j.replace('V', ''))  # index of j
        while not p[jIndex] == "V0":
            j = p[jIndex]
            jIndex = int(p[jIndex].replace('V', ''))
        fwdTable.append([i, ("V0", j)])
fwdTable.sort()

print("Destination\t\tLink")
for i in fwdTable:
    print(str(i[0]) + "\t\t\t\t" + str(i[1]).replace("'", ''))
