import json
import os
from microphone import Microphone

JSON_FILE = "data/microphones.json"


def save_to_file(microphones):
    data = [mic.to_dict() for mic in microphones]
    os.makedirs(os.path.dirname(JSON_FILE), exist_ok=True)

    with open(JSON_FILE, "w") as file:
        json.dump(data, file, indent=4)

    print(f"Data saved to {JSON_FILE}!")


def load_from_file():
    if os.path.exists(JSON_FILE):
        with open(JSON_FILE, "r") as file:
            data = json.load(file)
            return [Microphone.from_dict(item) for item in data]

    print("No file found. Starting fresh.")
    return []
