function myFunction(current) {
    //var current = document.getElementById("cur-page").value;
    var max = document.getElementById("max-items").value;
    var perPage = document.getElementById("per-page-select").value;
    var pageList = document.getElementById("page-list");
    var pLength = pageList.childNodes.length;
    var maxPage = Math.ceil(max / perPage);

    getPage(current, perPage, true);

    //window.alert(pageList.innerHTML);
    var html = "";

    for(var j=1; j <= maxPage; j++)
    {
        html += "<li id='page" + j + "'";

        if(j == current)
        {
            html += " class='active'";
        }

        html += "> <button type='button' ";
        html += "onclick='myFunction(" + j + ")'>";
        html += j;
        html += "</button></li>";
    }

    pageList.innerHTML = html;
}

function myFunction3(current) {
    //var current = document.getElementById("cur-page").value;
    var max = document.getElementById("max-items").value;
    var perPage = document.getElementById("per-page-select").value;
    var pageList = document.getElementById("page-list");
    var pLength = pageList.childNodes.length;
    //var i, j;
    //var button;
    var maxPage = Math.ceil(max / perPage);
    //window.alert(pageList.childNodes.length + "\n" + pageList.innerHTML);

     getPage(current, perPage, true);


    for(var i = pLength-1; i >= 0; i--)
    {
        //window.alert(" delete ID: " + pageList.childNodes[i].getAttribute("id"))
        pageList.removeChild(pageList.childNodes[i]);
    }

    //window.alert(pageList.childNodes.length + "\n" + pageList.innerHTML);
    //window.alert("Page: " + current + ", " + maxPage + ", " + perPage);

    for(var j=1; j <= maxPage; j++)
    {
        var newElement = document.createElement('li');
        newElement.setAttribute("id", "page" + j);

        if(j == current)
        {
            newElement.setAttribute("class", "active");
        }

        var link = document.createElement('a');
           link.setAttribute('href', '#');
           link.innerHTML = j;
           link.onclick = (function() {
              var currentJ = j;
              return function() {
                  myFunction3(currentJ);
              }
           })();

           newElement.appendChild(link);
           pageList.appendChild(newElement);
    }
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

