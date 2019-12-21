function getEffects(){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState==4 && xhr.status==200){
            document.getElementById("content").innerHTML = xhr.response;
        }
    }
    xhr.open("GET", "/effects");
    xhr.send()
}
function getFlavor() {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState==4 && xhr.status==200){
            document.getElementById("content").innerHTML = xhr.response;
        }
    }
    xhr.open("GET", "/flavors");
    xhr.send()
}

function getWeedByFlavor(flavor){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState==4 && xhr.status==200){
            document.getElementById("content").innerHTML = xhr.response;
        }
    }
    xhr.open("GET", "/flavors/" + flavor);
    xhr.send();
}