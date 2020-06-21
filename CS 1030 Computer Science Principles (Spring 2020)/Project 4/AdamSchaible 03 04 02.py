# Name Adam Schaible
# 4/10/20
# AdamSchaible 03 04 02.py

letters_to_frequency_dictionary = {'A': 0, 'B': 0, 'C': 0, 'D': 0, 'E': 0, 'F': 0, 'G': 0,
                                   'H': 0, 'I': 0, 'J': 0, 'K': 0, 'L': 0, 'M': 0, 'N': 0, 'O': 0,
                                   'P': 0, 'Q': 0, 'R': 0, 'S': 0, 'T': 0, 'U': 0, 'V': 0, 'W': 0,
                                   'X': 0, 'Y': 0, 'Z': 0}

input_file_name = '1030 Project 04 02 Sentences.txt'

with open(input_file_name) as file_being_read:
    for line in file_being_read:
        line = line.replace("\n", '')  # removes the newline
        print(line)

        for character in line:
            if character.upper() in letters_to_frequency_dictionary:
                letters_to_frequency_dictionary[character.upper()] += 1  # increment the frequency count for letter
file_being_read.close()

output_file_name = 'AdamSchaible 03 04 02 Output.txt'
with open(output_file_name, 'w') as output:

    print("Letter Frequency")
    output.write("Letter Frequency\n")  # write to file
    for key_value in letters_to_frequency_dictionary:
        print(key_value + "\t\t" + str(letters_to_frequency_dictionary[key_value]))
        output.write(key_value + "\t\t" + str(letters_to_frequency_dictionary[key_value]) + "\n")  # write to file

output.close()


