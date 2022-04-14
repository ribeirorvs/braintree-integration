var ppp;

function takeAuthToken(){
    $.ajax({
        type: 'GET',
        url: '/aToken'
    }).done(function(result) {
            $.ajax({
                type: 'GET',
                url: '/create-payment',
                data: {token: result}
            }).done(function(result) {
                ppp = PAYPAL.apps.PPP({
                    "approvalUrl": result,
                    "placeholder": "ppplus",
                    "mode": "sandbox",
                    "payerFirstName": "Eu",
                    "payerLastName": "Mesmo",
                    "payerPhone": "11900000000",
                    "payerEmail": "email@email.com",
                    "payerTaxId": "37128723803",
                    "payerTaxIdType": "BR_CPF",
                    "language": "pt_BR",
                    "country": "BR",
                    "rememberedCards": "customerRememberedCardHash",
                    "enableContinue": "continueButton",
                    "disableContinue": "continueButton"
                });
            })
    });
}

if(window.addEventListener){
    window.addEventListener("message", messageListener, false);
    console.debug("addEventListener successful");
} else if (window.attachtEvent){
    window.attachtEvent("onmessage", messageListener);
    console.log("attachEvent successful");
} else {
    console.log("Could not add or attach message listener")
}

function messageListener(event){
    try {
        var data = JSON.parse(event.data);
        if(data['action'] == "checkout"){
            console.log("Data: ");
            console.info(data);
            console.log("Result: ");
            console.info(data['result']);
        } else {
            console.warn("Não ainda");
        }
    } catch(exc) {

    }
}
