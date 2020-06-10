'''
Description: Mini Assembly Language Program Checker
Author: Adam Schaible   
Version:1.0
'''
import os
from pathlib import Path
import datetime
filename_path = input("\nThis program checks programs written in Mini Assembly Language to see if they are valid."
                 "\n\nEnter a valid .mal filename_path location, such as “testfile.mal” if the file is in the "
                 "same directory of where this program is stored: ")
file_name_valid = os.path.isfile(filename_path)
directory = os.path.dirname(filename_path)
file_name_no_ext = Path(filename_path).stem
extension_is_mal = filename_path.endswith(".mal")
filename_path_copy = filename_path
destination_file = filename_path_copy.replace(".mal", ".log")

# Error report types tally
ill_formed_label_tally = 0
invalid_opcode_tally = 0
too_many_operands_tally = 0
too_few_operands_tally = 0
ill_formed_identifier_tally = 0
ill_formed_literal_tally = 0
end_error_tally = 0
# Error report types tally end

# Warning report types tally
branch_to_non_existent_label = 0
label_never_branched_to = 0

label_list = []
branch_list = []
end_list = []
current_operand_code = ""
log = 0  # here to make .log file reader global

if not file_name_valid:
    print("\nYou entered: " + filename_path + " and this is not a path to a file, please rerun the program and enter a "
                                              "valid file path")
if not extension_is_mal:
    print("You did not enter a filename_ending with .mal, please rerun the program and enter a filename ending in .mal")


def stripped(raw):
    shortened = ""
    semicolon_counter = 0
    semicolon_found = False
    for y in raw:
        if y == ';':
            semicolon_found = True
        if not semicolon_found:
            semicolon_counter = semicolon_counter + 1
    if not semicolon_found:
        shortened = raw
    elif semicolon_found and semicolon_counter > 0:
        shortened = raw[0:(semicolon_counter - 1)] + '\n'

    blank = True
    if shortened != "":
        for z in raw:
            if (z != ' ') and (z != '\n') and (z != '\t'):
                blank = False

    return shortened, blank


def parsing(raw):
    parsed = []
    instruction_part = ""
    index = 0
    for x in raw:
        parse_the_word = False
        hit_colon = False
        if index > 0:
            test = (raw[index - 1] != ' ') and (raw[index - 1] != ',') and (raw[index - 1] != ':')
            if x == ':':
                parse_the_word = True
                parsed.append(':')
            elif x == ' ' and test:
                parse_the_word = True
            elif x == ',' and test:
                parse_the_word = True
        if parse_the_word:
            if hit_colon:
                parsed.append(':')
            parsed.append(instruction_part)
            instruction_part = ""
        if (x != ' ') and (x != ',') and (x != ':') and (x != '\n') and (x != '\t'):
            instruction_part = instruction_part + x
        index = index + 1

        raw_length = 0

        for g in raw:
            raw_length = raw_length + 1

        if(raw_length == index) and (instruction_part != ""):
            parsed.append(instruction_part)
            instruction_part = ""
    parse_length = 0

    for x in parsed:
        parse_length = parse_length + 1

    if parsed[0] == '':
        parsed.pop(0)
    return parsed


if file_name_valid and extension_is_mal:
    label_list = []
    branch_list = []

    mal = open(filename_path, 'r')
    log = open(destination_file, 'w')
    log.write("_____MAL PROGRAM EVALUATION LOG_____\n\n")
    log.write("INPUTTED MAL LOCATION: " + filename_path + "\n")
    log.write("OUTPUTTED LOG LOCATION: " + destination_file + "\n")
    current_time = datetime.datetime.now()
    log.write("PROCESS DATE: " + current_time.strftime("%m-%d-%Y   %H:%M") + "\n")
    log.write("Name: Adam Schaible" + "\n")
    log.write("Class: CS3210" + "\n")
    log.write("-------------------------------------" + "\n\n")
    log.write("original MAL program listing:\n\n")
    line_number = 1
    for line in mal:
        log.write(str(line_number) + ". " + line)
        line_number = line_number + 1
    mal.seek(0)  # resets position in mal file object'
    log.write("\n----------------------------\n")
    log.write("\nStripped MAL program Listing:\n\n")
    line_number = 1
    for line in mal:
        shortened2, blank2 = stripped(line)
        if not blank2:
            log.write(str(line_number) + ". " + shortened2)
        line_number = line_number + 1
    mal.seek(0)  # resets position in mal file object'

    log.write("\n----------------------------\n")
    log.write("\nError and Warning Report Listing:\n\n")
    line_number = 1
    for line in mal:
        line_list = []
        shortened2, blank2 = stripped(line)

        if not blank2:
            line_list = parsing(shortened2)

        number_of_elements_in_line_list = 0

        for c in line_list:
            number_of_elements_in_line_list = number_of_elements_in_line_list + 1

        # start of checking for ill formed label errors
        label_preceding_instruction = False
        if number_of_elements_in_line_list > 1:
            if line_list[0] == ':':

                label_list.append([line_list[1], str(line_number) + ". " + shortened2])
                label_preceding_instruction = True

        shift = 0

        if label_preceding_instruction:
            shift = 2

        conditional_branch = False
        unconditional_branch = False

        if shift + 4 == number_of_elements_in_line_list:
            conditional_branch = line_list[shift] == "BGT" or line_list[shift] == "BLT" or line_list[shift] == "BEQ"

        if shift + 2 == number_of_elements_in_line_list:
            unconditional_branch = line_list[shift] == "BR"

        if conditional_branch or unconditional_branch or label_preceding_instruction:
            temp = []
            if conditional_branch:
                temp.append(line_list[3 + shift])
                branch_list.append([line_list[3 + shift], str(line_number) + ". " + shortened2])
            if unconditional_branch:
                temp.append(line_list[1 + shift])
                branch_list.append([line_list[1 + shift], str(line_number) + ". " + shortened2])
            if label_preceding_instruction:
                temp.append(line_list[1])
            for slot in temp:
                count = 0
                non_alpha_in_label = False
                for a in slot:
                    count = count + 1
                    if not a.isalpha():
                        non_alpha_in_label = True
                if non_alpha_in_label:
                    ill_formed_label_tally = ill_formed_label_tally + 1
                    log.write(str(line_number) + ". " + shortened2)
                    log.write("**Error: ill formed label - non-alpha found in label: " + slot + " please use only A-Z or a-z in label.\n")
                if count > 5:
                    ill_formed_label_tally = ill_formed_label_tally + 1
                    log.write(str(line_number) + ". " + shortened2)
                    log.write("Error:ill formed label - more than 5 characters in your label " + slot + ", please make your label "
                              "five characters or less.\n")
        # end of checking for label errors______________________________________

        # start of checking for invalid opcode
        opcode_checking_for = ""
        acceptable_opcodes = ["LOAD", "LOADI", "STORE", "ADD", "INC", "SUB", "DEC", "BEQ", "BLT", "BGT", "BR", "NOOP",
                              "END"]
        opcode_valid = False

        label_only = number_of_elements_in_line_list == 2 and line_list[0] == ':'

        if not label_only:
            if number_of_elements_in_line_list >= shift + 1:
                opcode_checking_for = line_list[shift]

            if opcode_checking_for != "":
                for r in acceptable_opcodes:
                    if r == opcode_checking_for:
                        opcode_valid = True

            if not opcode_valid and not blank2:
                log.write(str(line_number) + ". " + shortened2)
                log.write("**Error: invalid opcode - your opcode " + opcode_checking_for + " is not valid, please "
                                                                                           "replace with a valid "
                                                                                           "opcode, such as "
                                                                                           "LOAD, LOADI, STORE, ADD, "
                          "INC, SUB, DEC, BEQ, BLT, BGT, BR, NOOP, END\n")
                invalid_opcode_tally = invalid_opcode_tally + 1

        # end of checking for invalid opcode______________________________________

        # too many and too few operand check
        operand_list = line_list
        if label_preceding_instruction:
            operand_list.pop(0)
            operand_list.pop(0)
        members = 0
        for y in operand_list:
            members = members + 1

        group1 = False
        group2 = False
        group3 = False
        group4 = False

        if members > 0:
            group1 = operand_list[0] == "NOOP" or operand_list[0] == "END"

            group2 = operand_list[0] == "INC" or operand_list[0] == "DEC" or operand_list[0] == "BR"

            group3 = operand_list[0] == "LOAD" or operand_list[0] == "LOADI" or operand_list[0] == "STORE"

            group4 = operand_list[0] == "ADD" or operand_list[0] == "SUB" or operand_list[0] == "BEG" or \
                operand_list[0] == "BLT" or operand_list[0] == "BGT"
        too_many_operands = False
        too_few_operands = False
        suggested_operand_number = 0

        if group1:
            if members > 1:
                too_many_operands = True
                suggested_operand_number = 0

        if group2:
            if members > 2:
                too_many_operands = True
                suggested_operand_number = 1

            if members < 2:
                too_few_operands = True
                suggested_operand_number = 1

        if group3:
            if members > 3:
                too_many_operands = True
                suggested_operand_number = 2

            if members < 3:
                too_few_operands = True
                suggested_operand_number = 2

        if group4:
            if members > 4:
                too_many_operands = True
                suggested_operand_number = 3

            if members < 4:
                too_few_operands = True
                suggested_operand_number = 3

        if too_many_operands:
            log.write(str(line_number) + ". " + shortened2)
            log.write("**Error: too many operands for " + operand_list[0] + " Please use only " +
                      str(suggested_operand_number) + " operands\n")
            too_many_operands_tally = too_many_operands_tally + 1

        if too_few_operands:
            log.write(str(line_number) + ". " + shortened2)
            log.write("**Error: too few operands for " + operand_list[0] + " Please use " +
                      str(suggested_operand_number) + " operands\n")
            too_few_operands_tally = too_few_operands_tally + 1
        # end of too many and too few operand check_________________________________________

        if members >= 1:
            if operand_list[0] == "END":
                end_list.append("END")  # adding instances of end to end list
            current_operand_code = operand_list[0]  # gets the latest operand code

        # Ill formed identifier (memory name) error check
        if members >= 3:
            if operand_list[0] == "LOAD" or operand_list[0] == "STORE":
                counter = 0
                alpha = True

                for i in operand_list[2]:
                    counter = counter + 1
                    if not i.isalpha():
                        alpha = False
                if counter > 5:
                    log.write(str(line_number) + ". " + shortened2)
                    log.write("**Error: ill-formed identifier " + operand_list[2] + " - More than 5 characters used, "
                                                                                    "please use no more than 5 "
                                                                                    "characters for the identifier \n")
                    ill_formed_identifier_tally = ill_formed_identifier_tally + 1
                if not alpha:
                    log.write(str(line_number) + ". " + shortened2)
                    log.write("**Error: ill-formed identifier " + operand_list[2] + " - non alpha characters used in "
                                                                                    "identifier, please use only A-Z or"
                                                                                    " a-z in identifier\n")
                    ill_formed_identifier_tally = ill_formed_identifier_tally + 1

        # End of Ill formed identifier (memory name) error check___________________________

        # Ill formed literal check (numeric value used in operand of LOADI)

        if members >= 3:
            if operand_list[0] == "LOADI":
                literal = operand_list[2]
                octal = True
                unsigned = True
                sign = ""
                count = 0
                for o in literal:
                    count = count + 1

                if count > 0:
                    if literal[0] == '+' or literal[0] == '-':
                        sign = literal[0]
                        unsigned = False
                        if count > 1:
                            literal = literal[1:]
                        if count == 1:
                            literal = ""

                if not literal == "":
                    octal_characters = ['0', '1', '2', '3', '4', '5', '6', '7']
                    for i in literal:
                        octal_temp = False
                        for z in octal_characters:
                            if z == i:
                                octal_temp = True
                        if not octal_temp:
                            octal = False
                else:
                    octal = False

                if not octal:
                    log.write(str(line_number) + ". " + shortened2)
                    log.write("**Error: ill formed literal - the literal: " + operand_list[2] +
                              " is not octal, replace with a octal number\n")
                    ill_formed_literal_tally = ill_formed_literal_tally + 1

                if not unsigned:
                    log.write(str(line_number) + ". " + shortened2)
                    log.write("**Error: ill formed literal - the literal "
                              + operand_list[2] + " is signed, " + "remove the " + sign +
                              " from your literal\n")
                    ill_formed_literal_tally = ill_formed_literal_tally + 1
        # END of Ill formed literal check (numeric value used in operand of LOADI)________________________
        print("line list: " + str(line_list))  # here just to check line_list output, delete when done
        line_number = line_number + 1
    # Start of label and branch warnings
for t in label_list:
    matching_label_to_branch = False
    for i in branch_list:
        if t[0] == i[0]:
            matching_label_to_branch = True
    if not matching_label_to_branch:
        log.write(str(t[1]))
        log.write("**Warning, label: " + str(t[0]) + " is never branched to" + "\n")
        label_never_branched_to = label_never_branched_to + 1

for t in branch_list:
    matching_branch_to_label = False
    for i in label_list:
        if t[0] == i[0]:
            matching_branch_to_label = True
    if not matching_branch_to_label:
        log.write(str(t[1]))
        log.write("**Warning, branch: " + str(t[0]) + " goes to a non-existent label\n")
        branch_to_non_existent_label = branch_to_non_existent_label + 1

# End of label and branch warnings check__________________________________________________________

# Checking for only one end to be in program
total_ends = 0
for m in end_list:
    total_ends = total_ends + 1
if total_ends == 1 and current_operand_code == "ENDS":
    log.write("\n\n**END APPEARS ONLY ONCE IN THE PROGRAM AND IS THE LAST INSTRUCTION \n")
else:
    if not total_ends == 1:
        log.write("**Error: END appeared " + str(total_ends) + " times it should appear one time\n")
        end_error_tally = end_error_tally + 1
    if not current_operand_code == "END":
        log.write("**Error: END is not the last instruction in the program\n")
        end_error_tally = end_error_tally + 1

# End of Checking for only one end to be in program

total_errors = ill_formed_label_tally + invalid_opcode_tally + too_many_operands_tally + \
        too_few_operands_tally + ill_formed_identifier_tally + ill_formed_literal_tally + end_error_tally

log.write("\n--------------------------------------\n")
log.write("\nTotal Errors: " + str(total_errors) + "\n")

if end_error_tally > 0:
    s = ""
    if end_error_tally > 1:
        s = "s"
    log.write("\t" + str(end_error_tally) + " END error" + s + "\n")

if ill_formed_label_tally > 0:
    s = ""
    if ill_formed_label_tally > 1:
        s = "s"
    log.write("\t" + str(ill_formed_label_tally) + " Ill formed label" + s + "\n")

if invalid_opcode_tally > 0:
    s = ""
    if invalid_opcode_tally > 1:
        s = "s"
    log.write("\t" + str(invalid_opcode_tally) + " Invalid opcode" + s + "\n")

if too_many_operands_tally > 0:
    s = ""
    if too_many_operands_tally > 1:
        s = "s"
    log.write("\t" + str(too_many_operands_tally) + " Too many operand" + s + "\n")

if too_few_operands_tally > 0:
    s = ""
    if too_few_operands_tally > 1:
        s = "s"
    log.write("\t" + str(too_few_operands_tally) + " Too few operand" + s + "\n")

if ill_formed_identifier_tally > 0:
    s = ""
    if ill_formed_identifier_tally > 1:
        s = "s"
    log.write("\t" + str(ill_formed_identifier_tally) + " Ill formed identifier" + s + "\n")

if ill_formed_literal_tally > 0:
    s = ""
    if ill_formed_literal_tally > 1:
        s = "s"
    log.write("\t" + str(ill_formed_literal_tally) + " Ill formed literal" + s + "\n")

total_warnings = branch_to_non_existent_label + label_never_branched_to
log.write("\n--------------------------------------\n")
log.write("\nTotal warnings: " + str(total_warnings) + "\n")

if branch_to_non_existent_label > 0:
    s = ""
    if branch_to_non_existent_label > 1:
        s = "es"
    log.write("\t" + str(branch_to_non_existent_label) + " branch" + s + " go to a non-existent label\n")

if label_never_branched_to:
    s = ""
    if label_never_branched_to > 1:
        s = "s"
    log.write("\t" + str(label_never_branched_to) + " label" + s + " never branched to\n")


log.write("\n--------------------------------------\n")
message = ""
if total_errors == 0:
    message = "Processing Complete -- MAL program is valid"
else:
    message = "Processing Complete -- MAL program is invalid"
log.write(message)

mal.seek(0)  # resets position in mal file object'
mal.close()
log.close()