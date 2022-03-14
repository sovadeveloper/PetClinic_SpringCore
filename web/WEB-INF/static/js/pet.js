console.log("pet.js успешно подключен")

function addPet(clientId){
    let name = document.getElementById("petName").value;
    let petType = document.getElementById("petType").value;
    let data = {
        name: name,
        client: {
            id: clientId
        },
        petType: {
            id: petType
        }
    }
    $.ajax({
        url: '/api/pet',
        type: 'POST',
        data: JSON.stringify(data),
        contentType: "application/json; charset=UTF-8",
        dataType: "json",
        success: function(result) {
            console.log("Питомец успешно добавлен")
            console.log(result)
            window.location.href = "/client/" + clientId;
        },
        error: function (result){
            console.log("Ошибка")
            console.log(result);
        }
    });
}

function editPet(petId, clientId, petTypeId){
    let name = document.getElementById("nameValue").value;
    let data = {
        id: petId,
        name: name,
        client: {
            id: clientId
        },
        petType: {
            id: petTypeId
        }
    }
    $.ajax({
        url: '/api/pet/' + petId,
        type: 'PUT',
        data: JSON.stringify(data),
        contentType: "application/json; charset=UTF-8",
        dataType: "json",
        success: function(result) {
            console.log("Питомец успешно отредактирован")
            console.log(result)
            window.location.href = "/client/" + clientId;
        },
        error: function (result){
            console.log("Ошибка")
            console.log(result);
        }
    });
}

function deletePet(id, clientId){
    let data = {
        id: id
    }
    $.ajax({
        url: '/api/pet/' + id,
        type: 'DELETE',
        data: JSON.stringify(data),
        contentType: "application/json; charset=UTF-8",
        dataType: "json",
        success: function(result) {
            console.log("Питомец успешно удален")
            console.log(result)
            window.location.href = "/client/" + clientId;
        },
        error: function (result){
            console.log("Ошибка")
            console.log(result);
        }
    });
}