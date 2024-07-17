class Quest {
    constructor() {
        this.idQst = "";
        this.titolo = "";
        this.categoria = { idCat: "", nome: "" }; // Oggetto per categoria
        this.domande = []; // ci andranno dentro degli oggetti di tipo Domanda
    }
}

class Domanda {
    constructor() {
        this.idQstDet = "";
        this.domanda = "";
        this.risposte = []; // ci andranno dentro degli oggetti di tipo Riposta
    }
}

class Risposta {
    constructor() {
        this.idAns = "";
        this.risposta = "";
        this.corretta = false;
    }
}


/*

    <head>
        <script src="/objQuest.js"></script>
        <script>
            const quest = new Quest();
            quest.idQst = '[[${ quest.idQst }]]';
            quest.titolo = '[[${ quest.titolo }]]';
            quest.categoria = { idCat: '[[${ quest.categoriequest.idCat }]]', nome: '[[${ quest.categoriequest.nome }]]' };

            let domanda;
            let risposta;

        </script>

    <li class="list-group-item" th:each="domanda : ${quest.domanda}">
        <script>
            domanda = new Domanda();
            domanda.idQstDet = '[[${domanda.idQstDet}]]';
            domanda.domanda = '[[${domanda.domanda}]]';
        </script>


    <ol class="list-group list-group-numbered">
        <th:block th:each="risposta : ${domanda.risp}">
            <script>
                ris = new Risposta();
                ris.idAns = [[${risposta.idAns}]];
                ris.risposta = '[[${risposta.risposta}]]';
                ris.corretta = [[${risposta.corretta}]];
                domanda.risposte.push(ris);
            </script>
            <li th:class="${risposta.corretta} ? 'list-group-item list-group-item-success' : 
            'list-group-item list-group-item-danger'"
                th:text="${risposta.risposta}">
            </li>
        </th:block>
    </ol>

    </details>
        <script>
            quest.domande.push(domanda);
        </script>


*/