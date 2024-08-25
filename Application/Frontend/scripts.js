document.querySelectorAll('nav a').forEach(anchor => {
    anchor.addEventListener('click', function (e) {
        e.preventDefault();
        document.querySelector(this.getAttribute('href')).scrollIntoView({
            behavior: 'smooth'
        });
    });
});

function toggleLoginPassword() {
    const passwordField = document.getElementById("login-password");
    const toggleBtn = document.querySelector(".toggle-login-password");
    if (passwordField.type === "password") {
        passwordField.type = "text";
        toggleBtn.classList.remove("fa-eye");
        toggleBtn.classList.add("fa-eye-slash");
    } else {
        passwordField.type = "password";
        toggleBtn.classList.remove("fa-eye-slash");
        toggleBtn.classList.add("fa-eye");
    }
}

function toggleSignupPassword() {
    const passwordField = document.getElementById("signup-password");
    const toggleBtn = document.querySelector(".toggle-password");
    if (passwordField.type === "password") {
        passwordField.type = "text";
        toggleBtn.classList.remove("fa-eye");
        toggleBtn.classList.add("fa-eye-slash");
    } else {
        passwordField.type = "password";
        toggleBtn.classList.remove("fa-eye-slash");
        toggleBtn.classList.add("fa-eye");
    }
}

function toggleConfirmPassword() {
    const confirmPasswordField = document.getElementById("confirm-password");
    const toggleBtn = document.querySelector(".toggle-confirm-password");
    if (confirmPasswordField.type === "password") {
        confirmPasswordField.type = "text";
        toggleBtn.classList.remove("fa-eye");
        toggleBtn.classList.add("fa-eye-slash");
    } else {
        confirmPasswordField.type = "password";
        toggleBtn.classList.remove("fa-eye-slash");
        toggleBtn.classList.add("fa-eye");
    }
}


function toggleSignup() {
    document.querySelector('.form-container').style.display = 'none';
    document.getElementById('signup-container').style.display = 'block';
}

function toggleLogin() {
    document.querySelector('.form-container').style.display = 'block';
    document.getElementById('signup-container').style.display = 'none';
}

function validateSignupForm() {
    const password = document.getElementById("signup-password").value;
    const confirmPassword = document.getElementById("confirm-password").value;
    const passwordErrorElement = document.getElementById("password-error");

    // Regular expressions to check the password requirements
    const hasUpperCase = /[A-Z]/.test(password);
    const hasNumber = /[0-9]/.test(password);
    const hasSymbol = /[!@#$%^&*(),.?":{}|<>]/.test(password);
    const isValidLength = password.length >= 8;

    if (password !== confirmPassword) {
        passwordErrorElement.textContent = "Passwords do not match!";
        passwordErrorElement.style.display = "block";
        return false;
    }

    if (!hasUpperCase || !hasNumber || !hasSymbol || !isValidLength) {
        passwordErrorElement.textContent = "Password must contain at least 1 capital letter, 1 symbol, 1 number, and be at least 8 characters long.";
        passwordErrorElement.style.display = "block";
        return false;
    }

    passwordErrorElement.style.display = "none"; // Hide the error message if all checks pass
    return true;
}


function toggleSidebar() {
    const sidebar = document.getElementById('sidebar');
    if (sidebar) {
        sidebar.classList.toggle('active');
        console.log("Class list after toggle:", sidebar.classList);
    } else {
        console.error("Sidebar element not found");
    }
}

async function handleLogin() {
    console.log("handleLogin function called");
    const emailInput = document.getElementById("email").value.trim();
    const passwordInput = document.getElementById("login-password").value.trim();
    const passwordErrorElement = document.getElementById("password-error");

    console.log("Email entered:", emailInput);

    const hasUpperCase = /[A-Z]/.test(passwordInput);
    const hasNumber = /[0-9]/.test(passwordInput);
    const hasSymbol = /[!@#$%^&*(),.?":{}|<>]/.test(passwordInput);
    const isValidLength = passwordInput.length >= 8;

    if (!hasUpperCase || !hasNumber || !hasSymbol || !isValidLength) {
        passwordErrorElement.textContent = "Password must contain at least 1 capital letter, 1 symbol, 1 number, and be at least 8 characters long.";
        passwordErrorElement.style.display = "block";
        return false;
    }

    passwordErrorElement.style.display = "none";

    try {
        const response = await fetch('customer.json');
        if (!response.ok) {
            throw new Error("Network response was not ok.");
        }
        const customers = await response.json();
        console.log("Fetched customers:", customers);

        const customer = customers.find(customer => customer.email === emailInput);
        console.log("Customer found:", customer);

        if (customer) {
            // Store customer data in local storage
            localStorage.setItem('loggedInCustomer', JSON.stringify(customer));

            const domain = emailInput.split('@')[1];
            if (domain === "BasketBliss.com") {
                window.location.href = 'admin/dashboard.html';
            } else {
                window.location.href = 'customer/dashboard.html'; 
            }
        } else {
            alert("Email not found. Please check your email or sign up.");
        }
    } catch (error) {
        console.error("Error fetching customer data:", error);
        alert("There was an error processing your request. Please try again later.");
    }

    return false;
}


