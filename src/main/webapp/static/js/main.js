async function updateCart(data) {
    fetch(`/updateCart${data}`)
}

window.onload = () => {
    let addToCartButtons = document.querySelectorAll(".btn-success");
    for (let button of addToCartButtons) {

        button.addEventListener('click', () => {
            let productName = button.dataset.name;
            let fetchParam = `?name=${productName}`;
            updateCart(fetchParam);
        });
    }
}
