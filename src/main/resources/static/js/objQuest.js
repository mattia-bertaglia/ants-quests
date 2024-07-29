class Quest {
    constructor() {
        this.idQst = "";
        this.titolo = "";
        this.attivo = false;
        this.categoria = { idCat: "", nome: "" }; // Oggetto per categoria
        this.domanda = []; // ci andranno dentro degli oggetti di tipo Domanda
    }
}

class Domanda {
    constructor() {
        this.idQstDet = "";
        this.domanda = "";
        this.risp = []; // ci andranno dentro degli oggetti di tipo Riposta
    }
}

class Risposta {
    constructor() {
        this.idAns = "";
        this.risposta = "";
        this.corretta = false;
    }
}
