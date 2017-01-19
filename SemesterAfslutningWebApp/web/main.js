var gameDiv = document.getElementById('game');
var startImg = document.getElementById('start');
var northImg = document.getElementById('north');
var southImg = document.getElementById('south');
var eastImg = document.getElementById('east');
var westImg = document.getElementById('west');
var itemsDiv = document.getElementById('items');
var nameInput = document.getElementById('input-name');
var nameLabel = document.getElementById('label-name');
var nameDiv = document.getElementById("name-div");
var hpDiv = document.getElementById("hp-div");
var hpBar = document.getElementById("hp-bar");
var statDiv = document.getElementById("stat-div");
var overlayDiv = document.getElementById("overlay-div");
var highscoresDiv = document.getElementById("highscores-div");

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
    gameDiv.style.backgroundImage = 'url("PicturesGeneral/Title.png")';
    startImg.style.display = 'block';
    northImg.style.display = 'none';
    southImg.style.display = 'none';
    eastImg.style.display = 'none';
    westImg.style.display = 'none';
    nameLabel.style.display = 'block';
    nameInput.style.display = 'block';
    nameInput.value = '';
    hpDiv.style.display = 'none';
    nameInput.focus();
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
        itemsDiv.removeChild(img);
    };
    itemsDiv.appendChild(img);
}

function addMonster(monster) {
    var img = new Image();
    img.src = monster.picture;
    img.className = 'monster';
    img.style.position = 'absolute';
    img.style.left = monster.x+'px';
    img.style.top = monster.y+'px';
    img.onclick = function() {
        attack(monster.id);
        itemsDiv.removeChild(img);
    };
    itemsDiv.appendChild(img);
}

function showHighscores(obj) {
    if (obj.player.health > 0) {
        overlayDiv.style.backgroundColor = 'rgba(128, 128, 128, 0.5)';
    }
    overlayDiv.style.display = 'block';
    var y = 0;
    for (var i = 0; i < obj.highscores.length; i++) {
        var name = document.createElement('div');
        name.style.position = 'absolute';
        name.style.top = y + 'px';
        name.style.left = '300px';
        name.style.width = '600px';
        name.style.textAlign = 'left';
        name.innerText = obj.highscores[i].name;
        highscoresDiv.appendChild(name);
        
        var score = document.createElement('div');
        score.style.position = 'absolute';
        score.style.top = y + 'px';
        score.style.left = '300px';
        score.style.width = '600px';
        score.style.textAlign = 'right';
        score.innerText = obj.highscores[i].score;
        
        highscoresDiv.appendChild(score);
        y += 100;
    }
    startImg.style.display = 'block';
    nameLabel.style.display = 'block';
    nameInput.style.display = 'block';
    nameInput.focus();
}

function show(obj) {
    while (highscoresDiv.firstChild) {
        highscoresDiv.removeChild(highscoresDiv.firstChild);
    }
    overlayDiv.style.display = 'none';
    nameDiv.innerText = obj.player.name;
    startImg.style.display = 'none';
    nameLabel.style.display = 'none';
    nameInput.style.display = 'none';
    if (obj.north) {
        northImg.style.display = 'block';
    } else {
        northImg.style.display = 'none';
    }
    if (obj.south) {
        southImg.style.display = 'block';
    } else {
        southImg.style.display = 'none';
    }
    if (obj.east) {
        eastImg.style.display = 'block';
    } else {
        eastImg.style.display = 'none';
    }
    if (obj.west) {
        westImg.style.display = 'block';
    } else {
        westImg.style.display = 'none';
    }
    gameDiv.style.backgroundImage='url("'+obj.picture+'")';
    currentRoom = obj.room;
    playerId = obj.playerId;
    while (itemsDiv.firstChild) {
        itemsDiv.removeChild(itemsDiv.firstChild);
    }
    for (var i = 0; i < obj.items.length; i++) {
        var item = obj.items[i];
        addItem(item);
    }
    for (var i = 0; i < obj.monsters.length; i++) {
        var monster = obj.monsters[i];
        addMonster(monster);
    }
    hpDiv.style.display = 'block';
    // start: 36, stop: 464, diff: 428
    hpBar.style.width = Math.floor(36 + obj.player.health * 428 / 100) + 'px';
    statDiv.innerText = 'att: '+obj.player.attack+' | def: '+
            obj.player.defense+' | gold: '+obj.player.gold;
    if (obj.gameOver) {
        showHighscores(obj);
        return;
    }
}

function logIn() {
    ajax({
        room: currentRoom,
        action: 'START',
        direction: 'NORTH',
        playerId: 0,
        playerName: nameInput.value
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

function attack(monsterId) {
    ajax({
        room: currentRoom,
        action: 'ATTACK',
        direction: '',
        playerId: playerId,
        monsterId: monsterId
    }, function (obj) {
        show(obj);
    });
}

window.onload = startGame;


