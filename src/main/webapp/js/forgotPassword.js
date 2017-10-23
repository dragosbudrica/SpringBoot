var ForgotPassword = ForgotPassword || {};

$(document).ready(function () {
    ForgotPassword.checkEmail();
});

ForgotPassword.checkEmail = function checkEmail() {

    // Email must be valid
    $('#email').on('input', function () {
        var input = $(this);
        Utils.checkEmail(input);
    });
};

ForgotPassword.validate = function validate() {
    var input = $('#email');

    if (input.val() === "") {
        input.next().removeClass("error1").addClass("error1_show");
        input.addClass("invalid");
    }

   return input.hasClass("valid");
};
