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
function getSpecies(){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function (){
        if (xhr.readyState==4 && xhr.status==200){
            document.getElementById("content").innerHTML = xhr.response;
        }

    }
    xhr.open("GET",  "/species");
    xhr.send();
}

function getLoginView(){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState==4 && xhr.status==200){
            document.getElementById("content").innerHTML = xhr.response;
        }
    }
    xhr.open("GET", "/login");
    xhr.send();
}

function getBySpecies(species){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState==4 && xhr.status==200){
            document.getElementById("content").innerHTML = xhr.response;
        }
    }
    xhr.open("GET", "/species/" + species);
    xhr.send();
}

function getNameView(){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState==4 && xhr.status==200){
            document.getElementById("content").innerHTML = xhr.response;
        }
    }
    xhr.open("GET", "/name");
    xhr.send();
}

function getNameSearchResults(){
    var searchString = document.getElementById("nameSearch").value;
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState==4 && xhr.status==200){
            document.getElementById("content").innerHTML = xhr.response;
        }
    }
    xhr.open("POST","/name" );
    xhr.send(searchString);
}

function getWeedComplete(name){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState==4 && xhr.status==200){
            document.getElementById("content").innerHTML = xhr.response;
        }
    }
    xhr.open("GET","/name/" + name);
    xhr.send();
}
function getRegisterView(){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState==4 && xhr.status==200){
            document.getElementById("content").innerHTML = xhr.response;
        }
    }
    xhr.open("GET", "/register");
    xhr.send();
}

function sendPost(weedId, name){
    var object = {
        title : document.getElementById("commentTitle").value,
        body: document.getElementById("commentBody").value,
        author: null,
        weedId: weedId};

    var package= JSON.stringify(object);
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState==4 && xhr.status==200){
            document.getElementById("content").innerHTML = xhr.response;
        }
    }
    xhr.open("POST", "/posts/" + name);
    xhr.send(package);
}


