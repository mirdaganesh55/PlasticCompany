document.addEventListener("DOMContentLoaded", function() {
    var menuHeader = document.getElementById("menuHeader");
    var menuLinks = document.getElementById("menuLinks");

    menuHeader.addEventListener("click", function() {
        if (menuLinks.style.display === "none") {
            menuLinks.style.display = "block";
        } else {
            menuLinks.style.display = "none";
        }
    });
});
