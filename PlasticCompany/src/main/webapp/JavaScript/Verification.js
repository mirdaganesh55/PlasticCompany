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
                });
            });