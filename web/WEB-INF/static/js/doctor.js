console.log("doctor.js успешно подключен")

function addDoctor(){
    let name = document.getElementById("name").value;
    let data = {
        name: name,
    }
    $.ajax({
        url: '/api/doctor',
        type: 'POST',
        data: JSON.stringify(data),
        contentType: "application/json; charset=UTF-8",
        dataType: "json",
        success: function(result) {
            console.log("Доктор успешно добавлен")
            console.log(result)
            window.location.href = "/doctor";
        },
        error: function (result){
            console.log("Ошибка")
            console.log(result);
        }
    });
}

function editDoctor(id){
    let name = document.getElementById("nameValue").value;
    let data = {
        id: id,
        name: name,
    }
    $.ajax({
        url: '/api/doctor/' + id,
        type: 'PUT',
        data: JSON.stringify(data),
        contentType: "application/json; charset=UTF-8",
        dataType: "json",
        success: function(result) {
            console.log("Доктор успешно отредактирован")
            console.log(result)
            window.location.href = "/doctor";
        },
        error: function (result){
            console.log("Ошибка")
            console.log(result);
        }
    });
}

function deleteDoctor(id){
    let data = {
        id: id
    }
    $.ajax({
        url: '/api/doctor/' + id,
        type: 'DELETE',
        data: JSON.stringify(data),
        contentType: "application/json; charset=UTF-8",
        dataType: "json",
        success: function(result) {
            console.log("Доктор успешно удален")
            console.log(result)
            window.location.href = "/doctor";
        },
        error: function (result){
            console.log("Ошибка")
            console.log(result);
        }
    });
}