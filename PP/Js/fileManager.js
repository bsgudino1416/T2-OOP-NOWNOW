const fs = require("fs");
const Microphone = require("./microphone");

const JSON_FILE = "data/microphones.json";

function saveToFile(microphones) {
    fs.mkdirSync("data", { recursive: true });

    const data = microphones.map(m => ({
        id: m.id,
        brand: m.brand,
        model: m.model,
        price: m.price
    }));

    fs.writeFileSync(JSON_FILE, JSON.stringify(data, null, 4));
    console.log("Datos guardados en " + JSON_FILE);
}

function loadFromFile() {
    if (!fs.existsSync(JSON_FILE)) {
        console.log("No existe archivo, iniciando vacío.");
        return [];
    }

    const raw = fs.readFileSync(JSON_FILE);
    const arr = JSON.parse(raw);

    return arr.map(obj => new Microphone(obj.id, obj.brand, obj.model, obj.price));
}

module.exports = { saveToFile, loadFromFile };
