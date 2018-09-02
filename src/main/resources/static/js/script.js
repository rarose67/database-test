function myFunction(stock) {
    var element = document.getElementById("table");
    var rows = document.getElementsByClassName("rep-row");
    var i;
    var row = this

    var nr = document.createElement("tr");
    nr.innerHTML = "<td>" + "stock.getSymbol()" + "</td>";

    row.appendChild(nr);

}

function myFunction2(title) {
        window.alert(title);
        }
