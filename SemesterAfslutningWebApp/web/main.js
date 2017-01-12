var game = document.getElementById('game')
var start = document.getElementById('start')
var north = document.getElementById('north')
var south = document.getElementById('south')
var east = document.getElementById('east')
var west = document.getElementById('west')

var currentRoom = 0
var playerId = 0

function startGame() {
    game.style.backgroundImage = 'url("title.png")'
    start.style.display = 'block'
    north.style.display = 'none'
    south.style.display = 'none'
    east.style.display = 'none'
    west.style.display = 'none'
}

function goTo(direction) {
    var xhr = new XMLHttpRequest()
    xhr.open('POST', 'Gameserv', true)
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send('room='+currentRoom+'&direction='+direction+'&playerId='+playerId)

    xhr.onload = function() {
        try {
            var obj = JSON.parse(xhr.responseText)
            start.style.display = 'none'
            if (obj.north) {
                north.style.display = 'block'
            } else {
                north.style.display = 'none'
            }
            if (obj.south) {
                south.style.display = 'block'
            } else {
                south.style.display = 'none'
            }
            if (obj.east) {
                east.style.display = 'block'
            } else {
                east.style.display = 'none'
            }
            if (obj.west) {
                west.style.display = 'block'
            } else {
                west.style.display = 'none'
            }
            game.style.backgroundImage='url("'+obj.picture+'")'
            currentRoom = obj.room
            playerId = obj.playerId
        } catch (ex) {
            alert(xhr.responseText)
        }
    }
}

startGame()


