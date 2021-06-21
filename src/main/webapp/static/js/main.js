async function updateCart(data) {
    fetch(`/updateCart${data}`)
        .then((response) => response.json())
        .catch(error  => console.log(error));
}

window.onload = () => {
    let addToCartButtons = document.querySelectorAll(".btn-success");
    for (let button of addToCartButtons) {

        button.addEventListener('click', () => {
            let productName = button.dataset.name;
            let defaultP = button.dataset.defaultP;
            let defaultC = button.dataset.defaultC;
            let productC = button.dataset.productC;
            let supplier = button.dataset.supplier;

            console.log(productName)
            let productCount = 1;
            let fetchParam = `?name=${productName}`;
            updateCart(fetchParam);
        });
    }
}
