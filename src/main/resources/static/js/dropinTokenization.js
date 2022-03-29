var buttonToken = document.querySelector('#tokenization-submit-button');
          
braintree.dropin.create({
    // Insert your tokenization key here
    authorization: 'sandbox_tvshsy3g_w8b33jczk3ys342p',
    container: '#tokenization-dropin-container'
}, function (createErr, instance) {
    buttonToken.addEventListener('click', function () {
    console.log("Aqui");
    instance.requestPaymentMethod(function (requestPaymentMethodErr, payload) {
        // When the user clicks on the 'Submit payment' button this code will send the
        // encrypted payment information in a variable called a payment method nonce
        $.ajax({
        type: 'POST',
        url: '/checkout',
        data: {'paymentMethodNonce': payload.nonce}
        }).done(function(result) {
        // Tear down the Drop-in UI
        instance.teardown(function (teardownErr) {
            if (teardownErr) {
            console.error('Could not tear down Drop-in UI!');
            } else {
            console.info('Drop-in UI has been torn down!');
            // Remove the 'Submit payment' button
            $('#tokenization-submit-button').remove();
            }
        });

        if (result.success) {
            $('#tokenization-checkout-message').html('<h1>Success</h1><p>Your Drop-in UI is working! Check your <a href="https://sandbox.braintreegateway.com/login">sandbox Control Panel</a> for your test transactions.</p><p>Refresh to try another transaction.</p>');
        } else {
            console.log(result);
            $('#tokenization-checkout-message').html('<h1>Error</h1><p>Check your console.</p>');
        }
        });
    });
    });
});