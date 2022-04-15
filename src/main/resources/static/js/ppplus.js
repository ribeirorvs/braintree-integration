var ppp;
var approvalUrl;
var executeUrl;
var token;

function takeAuthToken(){
    $.ajax({
        type: 'GET',
        url: '/aToken'
    }).done(function(result) {
        token = result;
        $.ajax({
            type: 'GET',
            url: '/create-payment',
            data: {'token': token}
        }).done(function(result) {
            result = JSON.parse(result);
            approvalUrl = result[1].href;
            executeUrl = result[2].href;
            console.log(approvalUrl);
            ppp = PAYPAL.apps.PPP({
                "approvalUrl": approvalUrl,
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
            console.log(data['result']['payer']['payer_info']['payer_id']);
            console.log(executeUrl);
            console.log(token);
            $.ajax({
                type: 'GET',
                url: '/execute-payment',
                data: {
                    'paymentId': executeUrl,
                    'payerId': data['result']['payer']['payer_info']['payer_id'],
                    'token': token
                }
            })
        } else {
            console.warn("Não ainda");
        }
    } catch(exc) {

    }
}
