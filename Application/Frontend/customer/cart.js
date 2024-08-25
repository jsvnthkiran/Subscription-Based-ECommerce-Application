function updateCartCount() {
    const cart = JSON.parse(localStorage.getItem('cart')) || [];
    const cartCount = cart.length;
    document.querySelectorAll('.cart-count').forEach(function(element) {
        element.textContent = cartCount;
    });
}

// Call updateCartCount() on page load to initialize the count
window.onload = function() {
    updateCartCount();
};
