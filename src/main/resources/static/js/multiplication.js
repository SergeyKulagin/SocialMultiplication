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
        $.ajax({
            url: "/results",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({
                multiplication: currentMultiplication,
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

    getMultiplication();
    $("#check-button").on("click", function () {
        sendMultiplicationResult();
        getMultiplication();
    })
})