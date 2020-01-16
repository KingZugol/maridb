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

function sendLogin(){
    var data = {
        username: document.getElementById("username").value,
        password: document.getElementById("password").value
    }
    var payload= JSON.stringify(data);
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState==4 && xhr.status==200){
            window.location.reload();
        }
    }
    xhr.open("POST", "/login");
    xhr.send(payload);
}

function sendLogout(){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState==4 && xhr.status==200){
            window.location.reload();
        }
    }
    xhr.open("GET", "/logout");
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

function sendRegistration(){

    var data = {
        username: document.getElementById("username").value,
        password: document.getElementById("pwd").value
    };

    var payload = JSON.stringify(data);
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState==4 && xhr.status==200){
            document.getElementById("content").innerHTML = xhr.response;
        }
    }
    xhr.open("POST", "/register");
    xhr.send(payload);
}

function sendPost(weedId, name, author, userId){
    var object = {
        userId: userId,
        title : document.getElementById("commentTitle").value,
        body: document.getElementById("commentBody").value,
        author: author,
        weedId: weedId};

    var payload= JSON.stringify(object);
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState==4 && xhr.status==200){
            document.getElementById("content").innerHTML = xhr.response;
        }
    }
    xhr.open("POST", "/posts/" + name);
    xhr.send(payload);
}

function sendCommentDelete(name, payload){
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState==4 && xhr.status==200){
            document.getElementById("post").innerHTML = xhr.response;
        }
    }
    xhr.open("Delete", "/posts/" + name);
    xhr.send(payload);
}

function editComment(id){
    console.log(name);

    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState==4 && xhr.status==200){
            document.getElementById(id).innerHTML = xhr.response;
        }
    }
    xhr.open("GET", "/posts/edit/" + id);
    xhr.send();
}

function sendEditConfirm(postId){
    var data = {
        title: document.getElementById("editTitle").value,
        body: document.getElementById("editBody").value
    }
    var payload = JSON.stringify(data);
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState==4 && xhr.status==200){
            document.getElementById("post").innerHTML = xhr.response;
        }
    }
    xhr.open("PUT", "/posts/");
    xhr.setRequestHeader("postId", postId);
    xhr.send(payload);
}