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

function getPage(page, perPage, loaded)
{
    if (page < 1)
    {
        page = 1;
    }

    var rows = document.getElementsByClassName("rep-row");
    var i;
    var max = rows.length;
    var rangeStart = ((page - 1) * perPage);
    var rangeEnd = (page * perPage);

    if (rangeEnd > max)
    {
        rangeEnd = max;
    }

    if (loaded)
    {
      for(i=0; i<max; i++)
      {
          //rows[i].attributes.removeNamedItem("style");
          rows[i].style="";
         var j;
         var row = rows[i];
         for(j=0; j < 2; j++)
         {
            row = row.nextSibling;
            //window.alert(i + ",  " + row.innerHTML);
            row.style="";
            //row.attributes.removeNamedItem("style");
            row = row.nextSibling;
         }
      }
    }

     for(i=0; i<max; i++)
     {
        if((i < rangeStart) || (i >= rangeEnd))
        {
            rows[i].style.display='none';
            var j;
            var row = rows[i];
            for(j=0; j < 2; j++)
            {
                row = row.nextSibling;
                row.style.display='none';
                row = row.nextSibling;
            }
        }
     }
}

