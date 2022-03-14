console.log("note.js успешно подключен")

function addNote(doctorId){
    let title = document.getElementById("title").value;
    let pet = document.getElementById("pet").value;
    let data = {
        title: title,
        pet: {
            id: pet,
            client: {
                id: 1
            },
            petType: {
                id: 1
            }
        },
        doctor: {
            id: doctorId
        }
    }
    console.log(getPetById(pet));
    $.ajax({
        url: '/api/note',
        type: 'POST',
        data: JSON.stringify(data),
        contentType: "application/json; charset=UTF-8",
        dataType: "json",
        success: function(result) {
            console.log("Запись успешно добавлен")
            console.log(result)
            window.location.href = "/doctor/" + doctorId;
        },
        error: function (result){
            console.log("Ошибка")
            console.log(result);
        }
    });
}

function getPetById(id){
    let data = {
        id: id
    }
    $.ajax({
        url: "/pet/" + id,
        type: "GET",
        data: JSON.stringify(data),
        contentType: "application/json; charset=UTF-8",
        dataType: "json",
        success: function (msg){
            console.log(msg);
        }
    })
}

function deleteNote(id, doctorId){
    let data = {
        id: id
    }
    $.ajax({
        url: '/api/note/' + id,
        type: 'DELETE',
        data: JSON.stringify(data),
        contentType: "application/json; charset=UTF-8",
        dataType: "json",
        success: function(result) {
            console.log("Запись успешно удалена")
            console.log(result)
            window.location.href = "/doctor/" + doctorId;
        },
        error: function (result){
            console.log("Ошибка")
            console.log(result);
        }
    });
}