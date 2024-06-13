function moveToNext(input) {
    if (input.value.length === input.maxLength) {
        var nextInput = input.nextElementSibling;
        if (nextInput != null) {
            nextInput.focus();
        }
    }
}

document.addEventListener("DOMContentLoaded", function() {
    var otpInputs = document.querySelectorAll(".otp-field input[type='text']");
    otpInputs.forEach(function(input) {
        input.addEventListener("input", function() {
            moveToNext(this);
        });

        // Add event listener for the backspace key
        input.addEventListener("keydown", function(event) {
            if (event.key === "Backspace" && input.value.length === 0) {
                var prevInput = input.previousElementSibling;
                if (prevInput != null) {
                    prevInput.focus();
                }
            }
        });
    });
});
