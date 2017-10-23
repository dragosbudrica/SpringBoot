var NewAccount = NewAccount || {};
NewAccount.isSsn = "";

$(document).ready(function () {
    NewAccount.checkFields();
    NewAccount.prepareAccountCreation();
});

NewAccount.checkSsn = function checkSsn(input) {
    if (NewAccount.isSsn && !input.val()) {
        input.next().next().removeClass("error2_show").addClass("error2");
    }
    NewAccount.isSsn = input.val();
    if (NewAccount.isSsn) {
        if (input.next().hasClass("error1_show")) {
            input.next().removeClass("error1_show").addClass("error1");
        }
        var is_valid_ssn = NewAccount.checkValidSsn(input.val());
        if (is_valid_ssn) {
            input.removeClass("invalid").addClass("valid");
            if (input.next().next().hasClass("error2_show")) {
                input.next().next().removeClass("error2_show").addClass("error2");
            }
        } else {
            input.removeClass("valid").addClass("invalid");
            input.next().next().removeClass("error2").addClass("error2_show");
        }
    } else {
        input.removeClass("valid").addClass("invalid");
        input.next().removeClass("error1").addClass("error1_show");
    }
};

NewAccount.checkFields = function checkFields() {
    // First Name can't be blank
    $('#firstName').on('input', function () {
        var input = $(this);
        Utils.checkFieldForRequired(input);
    });

    // Last Name can't be blank
    $('#lastName').on('input', function () {
        var input = $(this);
        Utils.checkFieldForRequired(input);
    });

    // SSN must be valid
    $('#ssn').on('input', function () {
        var input = $(this);
        NewAccount.checkSsn(input);
    });

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

NewAccount.checkValidSsn = function checkValidSsn(cnp) {
    if (cnp.length < 13 || cnp.length > 13 || isNaN(cnp))
        return false;
    else {
        var sum = parseInt(cnp[0]) * 2 +
            parseInt(cnp[1]) * 7 +
            parseInt(cnp[2]) * 9 +
            parseInt(cnp[3]) +
            parseInt(cnp[4]) * 4 +
            parseInt(cnp[5]) * 6 +
            parseInt(cnp[6]) * 3 +
            parseInt(cnp[7]) * 5 +
            parseInt(cnp[8]) * 8 +
            parseInt(cnp[9]) * 2 +
            parseInt(cnp[10]) * 7 +
            parseInt(cnp[11]) * 9;

        var modulus = sum % 11;

        var valid = false;
        if ((modulus < 10 && modulus.toString() === cnp[12]) || (modulus === 10 && cnp[12].toString() === '1')) {
            valid = true;
        }

        return valid;
    }
};

NewAccount.validate = function validate() {
    var input1 = $('#firstName');
    var input2 = $('#lastName');
    var input3 = $('#ssn');
    var input4 = $('#email');
    var input5 = $('#password');

    if (input1.val() === "" && input2.val() === "" && input3.val() === "" && input4.val() === "" && input5.val() === "") {
        input1.next().removeClass("error1").addClass("error1_show");
        input2.next().removeClass("error1").addClass("error1_show");
        input3.next().removeClass("error1").addClass("error1_show");
        input4.next().removeClass("error1").addClass("error1_show");
        input5.next().removeClass("error1").addClass("error1_show");
        input1.addClass("invalid");
        input2.addClass("invalid");
        input3.addClass("invalid");
        input4.addClass("invalid");
        input5.addClass("invalid");
    }

    return !(!input1.hasClass('valid') || !input2.hasClass('valid') || !input3.hasClass('valid') || !input4.hasClass('valid') || !input5.hasClass('valid'));
};

NewAccount.prepareAccountCreation = function prepareAccountCreation() {
    $('#submit').click(function (event) {
       /* var form_data = $("#newAccount input[type=text], input[type=password]").serializeArray();
        var error_free = true;
        for (var input in form_data) {
            var element = $("#newAccount_" + form_data[input]['name']);
            var valid = element.hasClass("valid");
            var error_element = $("span", element.parent());
            if (!valid) {
                error_element.removeClass("error").addClass("error_show");
                error_free = false;
            }
            else {
                error_element.removeClass("error_show").addClass("error");
            }
        }
        if (!error_free) {
            event.preventDefault();
        } */
       if(!NewAccount.validate()) {
           event.preventDefault();
       }
        else {
            var firstName = $('#firstName').val();
            var lastName = $('#lastName').val();
            var ssn = $('#ssn').val();
            var email = $('#email').val();
            var password = $('#password').val();
            var role = $('#role').val();

            NewAccount.sendCreateAccountAjax(firstName, lastName, ssn, email, password, role);
        }
    });
};

NewAccount.sendCreateAccountAjax = function sendCreateAccountAjax(firstName, lastName, ssn, email, password, role) {
    var jsonParams = JSON.stringify({
        'firstName': firstName,
        'lastName': lastName,
        'ssn': ssn,
        'email': email,
        'password': password,
        'role': role
    });

    $.ajax({
        url: '/addNewProfessorAccount',
        type: 'POST',
        data: jsonParams,
        contentType: 'application/json',
        success: function (data) {
            var message = $('#message');
            if (data.code === 0) {
                message.html(data.message);
                message.css('color', 'green');
                message.fadeIn(1);
                message.fadeOut(5000);
            }
            else {
                message.html(data.message);
                message.css('color', 'red');
                message.fadeIn(1);
                message.fadeOut(5000);
            }
        },
        error: function (data) {
            console.log(data);
        }
    });
};
