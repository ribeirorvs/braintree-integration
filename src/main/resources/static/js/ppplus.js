function takeAuthToken(){
    $.ajax({
        type: 'GET',
        url: '/aToken'
        }).done(function(result) {
            alert(result)
    });
}
/*
var ppp = PAYPAL.apps.PPP({
    "approvalUrl": "'.$approval_url.'",
    "placeholder": "ppplus",
    "mode": "sandbox",});
*/