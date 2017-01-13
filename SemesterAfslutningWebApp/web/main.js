var game = document.getElementById('game');
var start = document.getElementById('start');
var north = document.getElementById('north');
var south = document.getElementById('south');
var east = document.getElementById('east');
var west = document.getElementById('west');
var items = document.getElementById('items');
var name2 = document.getElementById('input-name');
var labelName = document.getElementById('label-name');

var currentRoom = 0;
var playerId = 0;

function ajax(options, callback) {
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'Gameserv', true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    var data = '';
    for (var key in options) {
        var value = options[key];
        data += '&'+key+'='+encodeURIComponent(value);
    }
    xhr.send(data.substr(1));
    xhr.onload = function () {
        try {
            var obj = JSON.parse(xhr.responseText);
            callback(obj);
        } catch (ex) {
            alert(xhr.responseText);
        }
    };
}

function startGame() {
    game.style.backgroundImage = 'url("PicturesGeneral/Title.png")';
    start.style.display = 'block';
    north.style.display = 'none';
    south.style.display = 'none';
    east.style.display = 'none';
    west.style.display = 'none';
    labelName.style.display = 'block';
    name2.style.display = 'block';
    name2.value = '';
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
    labelName.style.display = 'none';
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
    while (items.firstChild) {
        items.removeChild(items.firstChild);
    }
    for (var i = 0; i < obj.items.length; i++) {
        var item = obj.items[i];
        addItem(item);
    }
}

function logIn() {
    ajax({
        room: currentRoom,
        action: 'START',
        direction: 'NORTH',
        playerId: 0,
        playerName: name2.value,
    }, function(obj) {
        show(obj);
    });
}

function goTo(direction) {
    ajax({
        room: currentRoom,
        action: 'GOTO',
        direction: direction,
        playerId: playerId
    }, function(obj) {
        show(obj);
    });
}

function pickUp(itemId) {
    ajax({
        room: currentRoom,
        action: 'PICKUP',
        direction: '',
        playerId: playerId,
        itemId: itemId
    }, function (obj) {
        show(obj);
    });
}

window.onload = startGame;


