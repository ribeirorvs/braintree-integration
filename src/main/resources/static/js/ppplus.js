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
                });
            })
    });
}
/*

*/