var game = document.getElementById('game');
var start = document.getElementById('start');
var north = document.getElementById('north');
var south = document.getElementById('south');
var east = document.getElementById('east');
var west = document.getElementById('west');
var items = document.getElementById('items');
var name2 = document.getElementById('name');

var currentRoom = 0;
var playerId = 0;

function startGame() {
    game.style.backgroundImage = 'url("PicturesGeneral/Title.png")';
    start.style.display = 'block';
    north.style.display = 'none';
    south.style.display = 'none';
    east.style.display = 'none';
    west.style.display = 'none';
    name2.style.display = 'block';
    name2.focus();
}

function addItem(item) {
    var img = new Image();
    img.src = item.picture;
    img.className = 'item';
    img.style.position = 'absolute';
    img.style.left = item.x+'px';
    img.style.top = item.y+'px';
    img.onclick = function() {
        pickUp(item.id);
        items.removeChild(img);
    };
    items.appendChild(img);
}

function show(obj) {
    start.style.display = 'none';
    name2.style.display = 'none';
    if (obj.north) {
        north.style.display = 'block';
    } else {
        north.style.display = 'none';
    }
    if (obj.south) {
        south.style.display = 'block';
    } else {
        south.style.display = 'none';
    }
    if (obj.east) {
        east.style.display = 'block';
    } else {
        east.style.display = 'none';
    }
    if (obj.west) {
        west.style.display = 'block';
    } else {
        west.style.display = 'none';
    }
    game.style.backgroundImage='url("'+obj.picture+'")';
    currentRoom = obj.room;
    playerId = obj.playerId;
    for (var i = 0; i < obj.items.length; i++) {
        var item = obj.items[i];
        addItem(item);
    }
}

function logIn() {
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'Gameserv', true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    var data = 'room='+encodeURIComponent(currentRoom);
    data += '&direction='+encodeURIComponent('NORTH');
    data += '&playerId='+encodeURIComponent(0);
    data += '&playerName='+encodeURIComponent(name2.value);
    xhr.send(data);

    xhr.onload = function() {
        try {
            var obj = JSON.parse(xhr.responseText);
            show(obj);
        } catch (ex) {
            alert(xhr.responseText);
        }
    };
}

function goTo(direction) {
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'Gameserv', true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    var data = 'room='+encodeURIComponent(currentRoom);
    data += '&direction='+encodeURIComponent(direction);
    data += '&playerId='+encodeURIComponent(playerId);
    xhr.send(data);

    xhr.onload = function() {
        try {
            var obj = JSON.parse(xhr.responseText);
            show(obj);
        } catch (ex) {
            alert(xhr.responseText);
        }
    };
}

function pickUp(itemId) {
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'PickUp', true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    var data = 'room='+encodeURIComponent(currentRoom);
    data += '&direction='+encodeURIComponent('PICK_UP');
    data += '&playerId='+encodeURIComponent(playerId);
    data += '&itemId='+encodeURIComponent(itemId);
    xhr.send(data);

    xhr.onload = function() {
        try {
            var obj = JSON.parse(xhr.responseText);
            show(obj);
        } catch (ex) {
            alert(xhr.responseText);
        }
    };
}

window.onload = startGame;


