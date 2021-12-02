let loginForm = document.getElementById("loginForm")
let registerForm = document.getElementById("registerForm")
let indicator = document.getElementById("indicator")

function register() {
    registerForm.style.transform = "translateX(0px)"
    loginForm.style.transform = "translateX(0px)"
    indicator.style.transform = "translateX(100px)"
}

function log() {
    registerForm.style.transform = "translateX(300px)"
    loginForm.style.transform = "translateX(300px)"
    indicator.style.transform = "translateX(0px)"
}