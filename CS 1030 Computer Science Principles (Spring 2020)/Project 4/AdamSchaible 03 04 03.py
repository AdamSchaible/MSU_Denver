# Name Adam Schaible
# 4/10/20
# AdamSchaible 03 04 03.py

input_file_name = '1030 Project 04 03 Files.txt'

list_of_files_to_read = []

with open(input_file_name) as file_being_read:
    for filename in file_being_read:
        list_of_files_to_read.append(filename.replace("\n", '') + ".txt")  # removes newline before appending filename
file_being_read.close()

output_file_name = 'AdamSchaible 03 04 03 Output.txt'

with open(output_file_name, 'w') as output:
    for file in list_of_files_to_read:
        with open(file, 'r') as individual_file:
            for line in individual_file:
                output.write(line)
                print(line.replace("\n", ''))
        individual_file.close()
        output.write("\n")  # Needed to correctly format output at end of each file fragment
output.close()
