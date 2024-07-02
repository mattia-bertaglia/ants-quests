
var fixedDays = [6, 0, 1, 2, 3, 4, 5];
var months = ["Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"];
var dayOfWeek;  //Immagaziniamo l'attuale giorno della settimana (dom - lun - mar - mer - gio - ven - sab)
//VALORI DA 0 - 6 (domenica = 0, sabato = 6)

var dayOfMonth; //Immagaziniamo l'attuale giorno del mese (da 1 a 31)
var dataAttuale = new Date();
console.log(dataAttuale.getMonth());

var actualMonthInTable = dataAttuale.getMonth();
var actualYearInTable = dataAttuale.getFullYear();

var agenda = [];
agenda[1] = "sofuvbof";
agenda[4] = "dentista alle 15";
agenda[15] = "andiamo dalla nonna";
agenda[19] = "appuntamento con avvocato";
agenda[31] = "dsovfihwsvdoi";

var agenda2 = [];
agenda2[1] = "oculista alle 15";
agenda2[11] = "andiamo dalla zia";
agenda2[29] = "appuntamento con commercialista";

var mappaAgenda = new Map();
mappaAgenda.set('Settembre 2023', agenda);
mappaAgenda.set('Novembre 2023', agenda2);

var keyCurrentDate;


function generateTable(anno, mese) {
    var table = document.getElementById("pippo");
    var data = new Date(anno, mese);
    var dataInizioMeseActual = new Date(data.getFullYear(), data.getMonth(), 1);

    console.log(mese);
    keyCurrentDate = months[mese] + ' ' + anno;
    console.log(mappaAgenda);
    if (!mappaAgenda.has(keyCurrentDate)) {
        mappaAgenda.set(keyCurrentDate, []);
    }
    console.log(mappaAgenda);


    //console.log(dataInizioMeseActual.getDay());

    document.getElementById("mese").innerText = keyCurrentDate;
    dayOfWeek = data.getDay();
    dayOfMonth = data.getDate();

    var nDay = 1;
    var appoggio = "";
    for (var i = 0; i < 6; i++) { //Ciclo delle righe/settimane
        appoggio += "<tr>";
        for (var j = 0; j < 7; j++) { //ciclo dei singoli giorni della settimana

            if ((nDay == 1 && fixedDays[dataInizioMeseActual.getDay()] == j) || (nDay >= 2 && nDay <= getLastDate(data))) {

                if (nDay == dataAttuale.getDate() && mese == dataAttuale.getMonth() && anno == dataAttuale.getFullYear()) {
                    appoggio += "<td>" + getDayButton(nDay++, keyCurrentDate, "actualDay") + "</td>";
                }
                else {
                    appoggio += "<td>" + getDayButton(nDay++, keyCurrentDate) + "</td>";
                }

            }
            else {
                appoggio += "<td>  </td>";
            }
        }

        appoggio += "</tr>";
    }
    table.innerHTML += appoggio;
}

function getDayButton(nDay, keyCurrentDate, actualDay) {
    return "<button id='" + nDay + "' class='dayButton " + actualDay + " " + (mappaAgenda.get(keyCurrentDate)[nDay] != null ? "dayEvent" : "") + "' onclick='viewDay(" + nDay + ")'>" + nDay + "</button>";
}

function nextMonth() {
    document.getElementById("pippo").innerHTML = "<tr><th>Lunedì</th><th>Martedì</th><th>Mercoledì</th><th>Giovedì</th><th>Venerdì</th><th>Sabato</th><th>Domenica</th></tr>";

    if (actualMonthInTable == 11) {
        actualMonthInTable = 0;
        actualYearInTable++;
    }
    else {
        actualMonthInTable++;
    }
    generateTable(actualYearInTable, actualMonthInTable);
}

function previousMonth() {
    document.getElementById("pippo").innerHTML = "<tr><th>Lunedì</th><th>Martedì</th><th>Mercoledì</th><th>Giovedì</th><th>Venerdì</th><th>Sabato</th><th>Domenica</th></tr>";
    if (actualMonthInTable == 0) {
        actualMonthInTable = 11;
        actualYearInTable--;
    }
    else {
        actualMonthInTable--;
    }
    generateTable(actualYearInTable, actualMonthInTable);
}

function getLastDate(actualDate) {
    var data = new Date(actualDate.getFullYear(), actualDate.getMonth() + 1, 0);
    return data.getDate();
}

function viewDay(day) {
    document.getElementById("modal").style.display = 'block';
    document.getElementById("detailsDay").innerHTML = day + ' ' + keyCurrentDate;
    document.getElementById("detailsArea").setAttribute('detailsDay', day);
    document.getElementById("detailsArea").value = mappaAgenda.get(keyCurrentDate)[day] != null ? mappaAgenda.get(keyCurrentDate)[day] : "";
}

function aggiungiEvento() {
    var newEvento = document.getElementById("detailsArea");
    mappaAgenda.get(keyCurrentDate)[newEvento.getAttribute('detailsDay')] = newEvento.value;
    console.log(mappaAgenda.get(keyCurrentDate));
    document.getElementById(newEvento.getAttribute('detailsDay')).style.borderBottom = "solid 10px navy";
    document.getElementById('modal').style.display = 'none';
}

function eliminaEvento() {
    var newEvento = document.getElementById("detailsArea");
    mappaAgenda.get(keyCurrentDate)[newEvento.getAttribute('detailsDay')] = '';
    document.getElementById(newEvento.getAttribute('detailsDay')).style.borderBottom = "solid 1px";
    document.getElementById(newEvento.getAttribute('detailsDay')).style.borderColor = "grey";
    document.getElementById('modal').style.display = 'none';
}

/*generateTable(actualMonthInTable, actualYearInTable);*/
generateTable(dataAttuale.getFullYear(), dataAttuale.getMonth());
