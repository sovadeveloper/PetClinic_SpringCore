console.log("client.js успешно подключен")

function addClient(){
    let name = document.getElementById("name").value;
    let phone = document.getElementById("phone").value;
    let data = {
        name: name,
        phone: phone
    }
    $.ajax({
        url: '/api/client',
        type: 'POST',
        data: JSON.stringify(data),
        contentType: "application/json; charset=UTF-8",
        dataType: "json",
        success: function(result) {
            console.log("Клиент успешно добавлен")
            console.log(result)
            window.location.href = "/client";
        },
        error: function (result){
            console.log("Ошибка")
            console.log(result);
        }
    });
}

function editClient(id){
    let name = document.getElementById("nameValue").value;
    let phone = document.getElementById("phoneValue").value;
    let data = {
        id: id,
        name: name,
        phone: phone
    }
    $.ajax({
        url: '/api/client/' + id,
        type: 'PUT',
        data: JSON.stringify(data),
        contentType: "application/json; charset=UTF-8",
        dataType: "json",
        success: function(result) {
            console.log("Пользователь успешно отредактирован")
            console.log(result)
            window.location.href = "/client";
        },
        error: function (result){
            console.log("Ошибка")
            console.log(result);
        }
    });
}

function deleteClient(id){
    let data = {
        id: id
    }
    $.ajax({
        url: '/api/client/' + id,
        type: 'DELETE',
        data: JSON.stringify(data),
        contentType: "application/json; charset=UTF-8",
        dataType: "json",
        success: function(result) {
            console.log("Клиент успешно удален")
            console.log(result)
            window.location.href = "/client";
        },
        error: function (result){
            console.log("Ошибка")
            console.log(result);
        }
    });
}