count = 0
conv_num = 0
isNegative = False


def main(args):
    count = 0
    print("How many conversions would you like?")
    conv_num = int(input())
    while conv_num < 0:
        input("Please only enter a positive number")
        conv_num = int(input())


    while conv_num > 0:
        choice = int(input("inches(1) or cm(2)?"))
        while choice < 1 or choice > 2:
            input("Please only enter a between 1 and 2")
            choice = int(input())


        if choice == 1: 
            isInches = True
        else:
            isInches = False

        input_val = get_user_double(isInches)
        if input_val < 0:
            isNegative = True
        else:
            isNegative = False

        if isNegative:
            print("Please only enter a positive number")
        else:
            if isInches:
                print("Conversion is:", inches_to_meters(input_val))
            else:
                print("Conversion is:", cm_to_meters_or_inches(input_val, isInches))

        add_to_count()
        conv_num -= 1


    print("Completed", count, "conversions")


def get_user_double(isInches):
    if isInches:
        input_val = float(input("Please enter your inches to convert"))
        while input_val < 0:
            input_val = float(input("Please only enter a positive number"))
    else:
        input_val = float(input("Please enter your cm to convert"))
        while input_val < 0:
            input_val = float(input("Please only enter a positive number"))
    return input_val


def add_to_count():
    count += 1


def inches_to_meters(inches):
    if inches > 0 or inches <= 0:
        print("Converting now")
    count += 1
    conv_num -= 1
    return inches / 39.37


def cm_to_meters_or_inches(cm, to_inches):
    if to_inches:
        output = 39.37 * cm
    else:
        output = cm / 100
    count += 1
    conv_num -= 1
    return output