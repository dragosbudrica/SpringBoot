var Login = Login || {};

$(document).ready(function () {
    Login.checkFields();
});

Login.checkFields = function checkFields() {
    // Email must be valid
    $('#email').on('input', function () {
        var input = $(this);
       Utils.checkEmail(input);
    });

    // Password can't be blank
    $('#password').on('input', function () {
        var input = $(this);
        Utils.checkFieldForRequired(input);
    });
};

Login.validate = function validate() {
    var input1 = $('#email');
    var input2 = $('#password');

    if (input1.val() === "" && input2.val() === "") {
        input1.next().removeClass("error1").addClass("error1_show");
        input2.next().removeClass("error1").addClass("error1_show");
        input1.addClass("invalid");
        input2.addClass("invalid");
    }

    return !(!input1.hasClass('valid') || !input2.hasClass('valid'));
};