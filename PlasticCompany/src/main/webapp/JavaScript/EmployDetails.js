 function checkPasswordStrength(password, messageElementId) {
        var strengthMessage = document.getElementById(messageElementId);
        if (password.length < 6) {
            strengthMessage.innerText = "Weak";
            strengthMessage.style.color = "red";
        } else if (password.length < 8) {
            strengthMessage.innerText = "Medium";
            strengthMessage.style.color = "orange";
        } else if(password.length < 10){
			strengthMessage.innerText = "Strong";
            strengthMessage.style.color = "green";
		}
        else {
            strengthMessage.innerText = "Strong";
            strengthMessage.style.color = "green";
            if (password.length >= 10) {
                strengthMessage.innerText = ""; // Empty the message when password is strong
            }
        }
    }

    function checkPasswordMatch(passwordId, confirmPasswordId, messageElementId) {
        var password = document.getElementById(passwordId).value;
        var confirmPassword = document.getElementById(confirmPasswordId).value;
        var matchMessage = document.getElementById(messageElementId);

        if (password !== confirmPassword) {
            matchMessage.innerText = "Passwords do not match";
        } else {
            matchMessage.innerText = ""; // Clear the message when passwords match
        }
    }