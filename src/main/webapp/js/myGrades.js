var MyGrades = MyGrades || {};

$(document).ready(function () {
    MyGrades.getMyCoursesWithGrades();
});

MyGrades.getMyCoursesWithGrades = function getMyCoursesWithGrades() {
    var warning = $("#warning");
    $.ajax({
        url: '/getMyCoursesWithGrades',
        type: 'GET',
        dataType: 'json',
        traditional: true,
        success: function (data) {
            if (data.length === 0) {
                $(warning).find('h2').html("You aren't enrolled to any course yet!");
                warning.show();
            } else {
                Utils.sortCourseAscending(data);
                MyGrades.renderCourses(data);
            }
        },
        error: function (data) {
            console.log(data);
        }
    });
};

MyGrades.renderCourses = function renderCourses(data) {
    var tbodyCourses = $('#tbodyCourses');
    var paginationCourses = $('#paginationCourses');
    var tr;
    var result;
    var validated;
    for (var i = 0; i < data.length; i++) {
        tr = $('<tr/>');
        result = data[i].result;
        validated = data[i].validated;
        tr.append("<td>" + data[i].courseName + "</td>");
        tr.append("<td>" + data[i].category + "</td>");
        tr.append("<td>" + data[i].professor + "</td>");
        if(result && validated) {
            tr.append("<td class='validated'>" + result + "</td>");
        } else if(result && !validated) {
            tr.append("<td class='notValidated' title='Not validated by Admin'>" + result + "</td>");
        } else {
            tr.append("<td>" + noGrade + "</td>");
        }
        tbodyCourses.append(tr);
    }
    $('#studentCourses').show();
    Utils.pagination(tbodyCourses, paginationCourses);
};
