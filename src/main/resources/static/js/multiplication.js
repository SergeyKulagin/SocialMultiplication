$(document).ready(function () {
    function getMultiplication() {
        $.ajax({
            url: "/multiplications/random",
        }).then(
            function (data) {
                $("#multiplication-1").text(data.a);
                $("#multiplication-2").text(data.b);
            }
        )
    }

    getMultiplication();
})