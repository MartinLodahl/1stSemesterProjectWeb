var game = document.getElementById('game')
var start = document.getElementById('start')
var north = document.getElementById('north')
var south = document.getElementById('south')
var east = document.getElementById('east')
var west = document.getElementById('west')

var currentRoom = 1

function goTo(direction) {
    var data = new FormData()
    data.append('room',""+ currentRoom)
    data.append('direction', direction)

    var xhr = new XMLHttpRequest()
    xhr.open('POST', 'Gameserv', true)
    xhr.send('room='+currentRoom+'&direction='+direction)
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

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
        } catch (ex) {
            alert(xhr.responseText)
        }
    }
}



