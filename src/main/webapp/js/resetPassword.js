var ResetPassword = ResetPassword || {};
ResetPassword.isEmail = "";

$(document).ready(function () {
   /* ResetPassword.getResetPasswordResult();*/
   $('#token').val(ResetPassword.getUrlParameter('token'));
    ResetPassword.checkFields();
});

ResetPassword.checkField = function checkField(input1, input2) {
    var value1 = input1.val();
    if (value1) {
        if (input1.next().hasClass("error1_show")) {
            input1.next().removeClass("error1_show").addClass("error1");
        }
        var value2 = input2.val();
        if (value1 === value2) {
            if (input1.next().next().hasClass('error2_show') || input2.next().next().hasClass('error2_show')) {
                input1.next().next().removeClass('error2_show').addClass('error2');
                input2.next().next().removeClass('error2_show').addClass('error2');
            }
            input1.removeClass("invalid").addClass("valid");
            input2.removeClass("invalid").addClass("valid");
        } else {
            input1.next().next().removeClass("error2").addClass("error2_show");
            input2.next().next().removeClass("error2").addClass("error2_show");
            input1.removeClass("valid").addClass("invalid");
            input2.removeClass("valid").addClass("invalid");
        }
    } else {
        input1.next().removeClass("error1").addClass("error1_show");
        input1.removeClass("valid").addClass("invalid");
    }
};

ResetPassword.checkFields = function checkFields() {
    $('#password').on('input', function () {
        var input1 = $(this);
        var input2 = $('#confirmedPassword');
        ResetPassword.checkField(input1, input2);
    });

    $('#confirmedPassword').on('input', function () {
        var input1 = $(this);
        var input2 = $('#password');
        ResetPassword.checkField(input1, input2);
    });
};

ResetPassword.validate = function validate() {
    var input1 = $('#password');
    var input2 = $('#confirmedPassword');

    if (input1.val() === "" && input2.val() === "") {
        input1.next().removeClass("error1").addClass("error1_show");
        input2.next().removeClass("error1").addClass("error1_show");
        input1.addClass("invalid");
        input2.addClass("invalid");
    }

    return !(!input1.hasClass('valid') || !input2.hasClass('valid'));
};

ResetPassword.getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++) {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] === sParam) {
            return decodeURIComponent(sParameterName[1]);
        }
    }
};

