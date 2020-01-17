//AJAX Abfrage an ViewController. Antwort wird in das Element Content geladen


//AJAX Abfrage an ViewController. Antwort wird in das Element Content geladen. Gesuchte Wirkung wird als Pfadvariable weitergegeben
function getWeedByEffect(effect){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState==4 && xhr.status==200){
            document.getElementById("content").innerHTML = xhr.response;
        }
    }
    xhr.open("GET", "/effects/" + effect);
    xhr.send();
}