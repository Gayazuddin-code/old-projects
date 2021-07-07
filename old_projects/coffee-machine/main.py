resources = {"water": 1000,
             "milk": 1000,
             "coffee": 1000,
             "money": 0,
             }


def required_items(coffee_type):
    coffee_available = ["espresso", "latte", "cappuccino"]
    if coffee_type not in coffee_available:
        print("Drink not available in the menu.")
        return
    deposited_money = 0
    try:
        deposited_money = float(input("Inset the coins :"))
    except ValueError:
        print("enter valid amount.")
    required_money = transaction(coffee_type, deposited_money)
    change = deposited_money - required_money
    if required_money == 0:
        return
    if coffee_type == "espresso":
        required_resources = {"water": 50, "milk": 0, "coffee": 18, "money": required_money, }
        making_coffee(coffee_type, required_resources, change)
    elif coffee_type == "latte":
        required_resources = {"water": 200, "milk": 150, "coffee": 24, "money": required_money, }
        making_coffee(coffee_type, required_resources, change)
    elif coffee_type == "cappuccino":
        required_resources = {"water": 250, "milk": 100, "coffee": 24, "money": required_money, }
        making_coffee(coffee_type, required_resources, change)


def transaction(coffee_type, deposited_money):
    cost_coffee = {
        "espresso": 3,
        "latte": 7,
        "cappuccino": 10,
    }
    if deposited_money < cost_coffee[coffee_type]:
        print("Sorry that's not enough money.")
        return 0
    else:
        return cost_coffee[coffee_type]


def making_coffee(coffee_type, required_resources, change):
    make = 0
    for key in resources:
        if key == "money":
            continue
        else:
            if resources[key] < required_resources[key]:
                print(f"Sorry there is not enough {key}.")
                make = 1
    if make != 1:
        for key in resources:
            if key == "money":
                resources[key] += required_resources[key]
                continue
            resources[key] -= required_resources[key]
        print(f"\nHere is your {coffee_type.title()}. Enjoy!")
        if change > 0:
            print(f"Here is ${round(change, 2)} dollars in change.")


while True:
    choice = input("\nWhat would you like? (espresso/latte/cappuccino)\n>")
    choice = choice.lower()
    if choice == "off":
        break
    elif choice == "report":
        for item, amount in resources.items():
            if item == "money":
                print(f"{item} : ${amount}")
                continue
            print(f"{item} : {amount} ml")
    else:
        required_items(choice)
