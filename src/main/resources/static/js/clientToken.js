var clientToken = document.getElementById("clientToken");

function generateClientToken(){

    $.ajax({
        type: 'GET',
        url: '/client-token'
        }).done(function(result) {
            clientToken.value = result
            console.info(clientToken)
    });
}