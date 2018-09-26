$(document).ready(function () {
    var currentMultiplication;

    function getMultiplication() {
        $.ajax({
            url: "/multiplications/random",
        }).then(
            function (data) {
                currentMultiplication = data;
                $("#multiplication-1").text(data.a);
                $("#multiplication-2").text(data.b);
            }
        )
    }

    function sendMultiplicationResult() {
        return $.ajax({
            url: "/results",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({
                multiplication: currentMultiplication,
                user: {alias: $("#user-nickname").val()},
                attemptResult: $("#result-input").val()
            }),
            success: function (result) {
                $("#result-input").val("");
                var msg = result.multiplicationAttempt.multiplication.a + "x" + result.multiplicationAttempt.multiplication.b + "=" + result.multiplicationAttempt.attemptResult;
                if(result.correct){
                    $("#attempt-result-message").text(msg + " => Success!");
                }else {
                    $("#attempt-result-message").text(msg + " => Failure!")
                }
            }
        })
    }

    function getLastResults() {
        $.ajax({
            url: "/results/",
            data: {
                "alias" : $("#user-nickname").val()
            }
        }).then(
            function (data) {
                var resultsUI = "";
                for (var i = 0; i < data.length; i++) {
                    var attemptResult = data[i];
                    var attemptResultUI =
                        "<tr>" +
                        "<td>{{multiplicationAttempt.multiplication.a}}</td>" +
                        "<td>{{multiplicationAttempt.multiplication.b}}</td>" +
                        "<td>{{multiplicationAttempt.attemptResult}}</td>" +
                        "<td>{{correct}}</td>" +
                        "</tr>";
                    resultsUI += Mustache.to_html(attemptResultUI, attemptResult);
                }
                $("#attempt-results").html(resultsUI);
            }
        )
    }

    getMultiplication();
    $("#check-button").on("click", function () {
        sendMultiplicationResult().then(function () {
            getLastResults();
        });
        getMultiplication();
    })
})