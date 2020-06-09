# AdamSchaible_03_03_03.py
# 3/22/20
# CS 1030

# With province dictionary below the key is the first character of the postal code, the value is the province name
province_dictionary = {'A': 'Newfoundland', 'B': 'Nova Scotia', 'C': 'Prince Edward Island',
                       'E': 'New Brunswick', 'G': 'Quebec', 'H': 'Quebec', 'J': 'Quebec',
                       'K': 'Ontario', 'L': 'Ontario', 'M': 'Ontario', 'N': 'Ontario',
                       'P': 'Ontario', 'R': 'Manitoba', 'S': 'Saskatchewan', 'T': 'Alberta',
                       'V': 'British Columbia', 'X': 'Nunavut or Northwest Territories',
                       'Y': 'Yukon'}

reading_postal_codes = True

while reading_postal_codes:
    code = input("Input a Canadian postal code: ")

    if code == "":  # This ends the program when the user presses just the enter key when asked to enter a postal code
        break

    # Start of validating postal code
    postal_code_length_ok = len(code) == 7

    if not postal_code_length_ok:
        print("You Entered the postal code of : " + code)
        print("A Canadian postal code needs to be exactly 7 characters long.")
        print("The entered postal code is " + str(len(code)) + " characters long.")

    space_after_third_character = False  # default value if condition below is false

    if postal_code_length_ok:
        if code[3] == " ":  # There is a space after the 3rd character in the Canadian postal code
            space_after_third_character = True
        else:
            print("Error, there is no space after 3rd character in your Canadian postal code. ")
            print("The Canadian postal code you entered is : " + code)

    if postal_code_length_ok and space_after_third_character:

        # Note: In Canadian postal codes all letters in code are required to be uppercase.

        valid_digits_in_postal_code = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']

        # ===========================================================
        # Checking for errors in Canadian postal code...

        # Line below checks if the 1st character in the postal code is a valid key for a province
        valid_first_character = code[0] in province_dictionary.keys()

        valid_second_character = code[1] in valid_digits_in_postal_code  # Is the 2nd character a digit?

        valid_third_character = code[2].isupper()  # Is 3rd character a uppercase letter?

        # Note code[3] has a space, checked for this condition earlier

        valid_fourth_character = code[4] in valid_digits_in_postal_code  # Is the 4th character a digit?

        valid_fifth_character = code[5].isupper()  # Is 5th character a uppercase letter?

        valid_sixth_character = code[6] in valid_digits_in_postal_code  # Is the 6th character is a digit?

        # Displaying errors found in Canadian postal code...
        
        if not valid_first_character:
            print("Error, your 1st character you entered is not a valid code for a Canadian province")
            print("The 1st character you entered is : " + code[0])

        if not valid_second_character:
            print("Error, your 2nd character you entered is not valid for a rural or urban address, this should be"
                  " a digit.")
            print("The 2nd character you entered is : " + code[1])

        if not valid_third_character:
            print("Error, your 3rd character you entered is not valid, this should be an upper case letter.")
            print("The 3rd character you entered is : " + code[2])

        if not valid_fourth_character:
            print("Error, your 4th character you entered is not valid, this should be a digit.")
            print("The 4nd character you entered is : " + code[4])

        if not valid_fifth_character:
            print("Error, your 5th character you entered is not valid, this should be an upper case letter.")
            print("The 5th character you entered is : " + code[5])

        if not valid_sixth_character:
            print("Error, your 6th character you entered is not valid, this should be a digit.")
            print("The 6th character you entered is : " + code[6])

        # Checking if entire Canadian postal code is valid

        postal_code_is_valid = (valid_first_character and valid_second_character and valid_third_character and
                                valid_fourth_character and valid_fifth_character and valid_sixth_character)

        if postal_code_is_valid:
            print("The province associated with your Canadian postal code is : " + province_dictionary.get(code[0]))
            if code[1] == '0':
                print("This is a rural address.")
            else:
                print("This is an urban address.")
