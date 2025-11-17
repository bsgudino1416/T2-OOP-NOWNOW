const readline = require("readline");
const Microphone = require("./microphone");
const fm = require("./fileManager");

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

let microphones = fm.loadFromFile();

function menu() {
    console.log("\n1. Agregar micrófono");
    console.log("2. Mostrar lista");
    console.log("3. Guardar en JSON");
    console.log("4. Salir");

    rl.question("Opción: ", opt => {
        switch (opt) {
            case "1":
                rl.question("ID: ", id => {
                    rl.question("Marca: ", brand => {
                        rl.question("Modelo: ", model => {
                            rl.question("Precio: ", price => {
                                microphones.push(new Microphone(id, brand, model, parseFloat(price)));
                                menu();
                            });
                        });
                    });
                });
                break;

            case "2":
                microphones.forEach(m => {
                    console.log(`${m.id} - ${m.brand} - ${m.model} - $${m.price}`);
                });
                menu();
                break;

            case "3":
                fm.saveToFile(microphones);
                menu();
                break;

            case "4":
                console.log("Saliendo...");
                rl.close();
                break;

            default:
                console.log("Opción inválida.");
                menu();
        }
    });
}

console.log("Microphone Manager");
menu();
