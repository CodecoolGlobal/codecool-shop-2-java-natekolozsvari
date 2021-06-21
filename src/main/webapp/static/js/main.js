function init() {
    addCartEventListener();
}

function addCartEventListener() {
    let cartButton = document.querySelector('.cart-button');
    cartButton.addEventListener('click', function () {
        window.location.href = "/cart";
    })
}

init();