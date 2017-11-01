var RegistrationConfirmation = RegistrationConfirmation || {};

$(document).ready(function () {
    RegistrationConfirmation.getRegistrationConfirmationResult();
});

RegistrationConfirmation.getRegistrationConfirmationResult = function getRegistrationConfirmationResult() {
    $.ajax({
        url: '/getRegistrationConfirmationResult',
        type: 'GET',
        dataType: 'json',
        traditional: true,
        success: function (data) {
            var message = $("#message");
            if (data.code === 0) {
                message.find('h2').html(data.message);
                message.css('color', 'green');
            }
            else {
                message.find('h2').html(data.message);
                message.css('color', 'red');
            }
        },
        error: function (data) {
            console.log(data);
        }
    });
};