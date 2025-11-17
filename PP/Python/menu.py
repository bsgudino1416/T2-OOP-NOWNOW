from microphone import Microphone
from file_manager import save_to_file, load_from_file


def show_menu():
    microphones = load_from_file()

    while True:
        print("\n===== MICROPHONE MENU =====")
        print("1. Add microphone")
        print("2. Show microphones")
        print("3. Save to JSON")
        print("4. Exit")

        option = input("Option: ")

        if option == "1":
            id = input("ID: ")
            brand = input("Brand: ")
            model = input("Model: ")
            price = float(input("Price: "))

            microphones.append(Microphone(id, brand, model, price))
            print("Microphone added!")

        elif option == "2":
            if not microphones:
                print("No microphones registered.")
            else:
                print("\n--- Microphone List ---")
                for mic in microphones:
                    print(f"{mic.id} - {mic.brand} - {mic.model} - ${mic.price}")

        elif option == "3":
            save_to_file(microphones)

        elif option == "4":
            print("Exiting program...")
            break

        else:
            print("Invalid option.")
