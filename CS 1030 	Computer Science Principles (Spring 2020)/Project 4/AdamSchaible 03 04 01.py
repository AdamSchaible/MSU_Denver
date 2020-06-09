# Name Adam Schaible
# 4/10/20
# AdamSchaible 03 04 01.py


letters_to_points_dictionary = {'A': 1, 'E': 1, 'I': 1, 'L': 1, 'N': 1, 'O': 1, 'R': 1,
                                'S': 1, 'T': 1, 'U': 1, 'D': 2, 'G': 2, 'B': 3, 'C': 3, 'M': 3,
                                'P': 3, 'F': 4, 'H': 4, 'V': 4, 'W': 4, 'Y': 4, 'K': 5, 'J': 8,
                                'X': 8, 'Q': 10, 'Z': 10}

file_name = '1030 Project 04 01 Words.txt'

total_points = 0

with open(file_name) as file_being_read:
    print("Word              Points")
    for word in file_being_read:
        points = 0
        word = word.replace("\n", '')  # remove the newline from each word

        if 0 < len(word) < 10:  # if length of line is >=10 characters or has 0 characters the points assigned is zero
            for character in word:
                if character.upper() in letters_to_points_dictionary:
                    points += letters_to_points_dictionary[character.upper()]

        while len(word) < 20:  # this is here just to make the output be formatted more nicely
            word += ' '

        total_points += points
        print(word.upper() + str(points))

print("\nTotal points        " + str(total_points))
file_being_read.close()
