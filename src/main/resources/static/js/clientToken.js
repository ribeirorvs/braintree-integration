var clientToken = document.getElementById("clientToken");

function generateClientToken(){

    $.ajax({
        type: 'GET',
        url: '/client-token'
        }).done(function(result) {
        // Tear down the Drop-in UI
            clientToken.value = result
            console.info(clientToken)
    });
}