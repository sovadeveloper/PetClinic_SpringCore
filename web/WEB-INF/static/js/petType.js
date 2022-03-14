console.log("petType.js успешно подключен")

function addPetType(){
    let name = document.getElementById("name").value;
    let data = {
        name: name,
    }
    $.ajax({
        url: '/api/petType',
        type: 'POST',
        data: JSON.stringify(data),
        contentType: "application/json; charset=UTF-8",
        dataType: "json",
        success: function(result) {
            console.log("Тип питомца успешно добавлен")
            console.log(result)
            window.location.href = "/petType";
        },
        error: function (result){
            console.log("Ошибка")
            console.log(result);
        }
    });
}

function editPetType(id){
    let name = document.getElementById("nameValue").value;
    let data = {
        id: id,
        name: name,
    }
    $.ajax({
        url: '/api/petType/' + id,
        type: 'PUT',
        data: JSON.stringify(data),
        contentType: "application/json; charset=UTF-8",
        dataType: "json",
        success: function(result) {
            console.log("Тип питомца успешно отредактирован")
            console.log(result)
            window.location.href = "/petType";
        },
        error: function (result){
            console.log("Ошибка")
            console.log(result);
        }
    });
}

function deletePetType(id){
    let data = {
        id: id
    }
    $.ajax({
        url: '/api/petType/' + id,
        type: 'DELETE',
        data: JSON.stringify(data),
        contentType: "application/json; charset=UTF-8",
        dataType: "json",
        success: function(result) {
            console.log("Тип питомца успешно удален")
            console.log(result)
            window.location.href = "/petType";
        },
        error: function (result){
            console.log("Ошибка")
            console.log(result);
        }
    });
}