class Microphone:
    def __init__(self, id, brand, model, price):
        self.id = id
        self.brand = brand
        self.model = model
        self.price = price

    def to_dict(self):
        return {
            "id": self.id,
            "brand": self.brand,
            "model": self.model,
            "price": self.price
        }

    @staticmethod
    def from_dict(data):
        return Microphone(
            data["id"],
            data["brand"],
            data["model"],
            data["price"]
        )
