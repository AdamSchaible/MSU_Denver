# Adam Schaible
# CS 1030 - 003
# Assignment # 2

# AdamSchaible_03_02_02.py

import random
from statistics import mean

loop = True

while loop:
    # section 2.1, 2.9
    response = input("How many simulations do you want to run? : ")
    number_of_simulations = -1
    if response == "":
        break

    if response.isnumeric():
        temp = int(response)
        if temp > 0:
            number_of_simulations = temp
        else:
            break
    else:
        break

    counter = 0
    accumulation_of_number_of_flips = []
    minimum_number_of_flips_to_get_three_in_a_row = 0
    highest_number_of_flips_to_get_three_in_a_row = 0


    while counter < number_of_simulations:  # section 2.2, 2.7

        number_of_flips = 1
        flip_record = ""
        while loop:

            # section 2.4
            coin = str(random.randint(1, 2))
            coin = coin.replace("1", "H")
            coin = coin.replace("2", "T")
            coin_combo = str(coin) + " "

            flip_record += coin_combo

            if "H H H" in flip_record or "T T T" in flip_record:  # section 2.3
                # section 2.6
                print("The flips in current simulation : " + flip_record)
                accumulation_of_number_of_flips.append(number_of_flips)
                minimum_number_of_flips_to_get_three_in_a_row = min(accumulation_of_number_of_flips)
                highest_number_of_flips_to_get_three_in_a_row = max(accumulation_of_number_of_flips)

                break
            number_of_flips += 1  # section 2.5
        counter += 1

    # section 2.8
    print("The average number of flips is : " + str(mean(accumulation_of_number_of_flips)))
    print("The minimum number of flips is : " + str(minimum_number_of_flips_to_get_three_in_a_row))
    print("The maximum number of flips is : " + str(highest_number_of_flips_to_get_three_in_a_row))
