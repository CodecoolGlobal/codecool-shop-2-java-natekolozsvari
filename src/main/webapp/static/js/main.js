async function updateCart(data, callback) {
    fetch(`/updateCart${data}`)
        .then((response) => response.json())
        .then((items) => callback(items))
        .catch(error => console.log(error));
}

window.onload = () => {
    updateCart("?name=show", showCartItems)
    let cartSize = document.querySelector(".cart-size");
    cartSize.textContent = cartSize.dataset.value;
    let addToCartButtons = document.querySelectorAll(".btn-success");
    for (let button of addToCartButtons) {

        button.addEventListener('click', () => {
            let productName = button.dataset.name;
            let add = "add";
            let fetchParam = `?name=${productName}&mod=${add}`;
            updateCart(fetchParam, showCartItems);
            cartSize.textContent = (parseInt(cartSize.textContent) + 1).toString()
        });
    }

    initButtons();
    initSideBar();
    // initCartEventListener();
    initSignUpModal();
    initLogInModal();
    cartHoverListener();
    initLogOut();
}

function initButtons() {
    let filterBtn = document.getElementById('filter-btn');
    filterBtn.addEventListener('click', function () {
        if (document.getElementById('mySidenav').style.width === '300px') {
            closeNav();
        } else {
            openNav();
        }
    });
    let closeBtn = document.getElementById('close-btn');
    closeBtn.addEventListener('click', closeNav);

}

function initSideBar() {
    let categoryCheckBoxes = document.querySelectorAll('.category-check');
    categoryCheckBoxes.forEach(function (checkbox) {
        checkbox.addEventListener('change', function () {
            let category = checkbox.parentElement.querySelector('.category-check-label').textContent;
            category = category.substring(0, category.indexOf('(') - 1);
            let categoryContainer = document.querySelector(`.${category}`);
            if (this.checked) {
                categoryContainer.style.display = 'block';
            } else {
                categoryContainer.style.display = 'none';
            }
        })
    })
    let categoryClear = document.getElementById('category-clear');
    categoryClear.addEventListener('click', function () {
        categoryCheckBoxes.forEach(function (checkbox) {
            if (checkbox.checked) {
                checkbox.click();
            }
        })
    })
    let categorySelect = document.getElementById('category-select');
    categorySelect.addEventListener('click', function () {
        categoryCheckBoxes.forEach(function (checkbox) {
            if (!checkbox.checked) {
                checkbox.click();
            }
        })
    })
    let supplierCheckBoxes = document.querySelectorAll('.supplier-check');
    supplierCheckBoxes.forEach(function (checkbox) {
        checkbox.addEventListener('change', function () {
            let supplier = checkbox.parentElement.querySelector('.supplier-check-label').textContent;
            supplier = supplier.substring(0, supplier.indexOf('(') - 1);
            let cards = document.querySelectorAll('.supplier');
            cards.forEach(function (card) {
                if (card.textContent === supplier) {
                    if (checkbox.checked) {
                        card.parentElement.style.display = 'block';
                    } else {
                        card.parentElement.style.display = 'none';
                    }
                }
            })
        })
    })
    let supplierClear = document.getElementById('supplier-clear');
    supplierClear.addEventListener('click', function () {
        supplierCheckBoxes.forEach(function (checkbox) {
            if (checkbox.checked) {
                checkbox.click();
            }
        })
    })
    let supplierSelect = document.getElementById('supplier-select');
    supplierSelect.addEventListener('click', function () {
        supplierCheckBoxes.forEach(function (checkbox) {
            if (!checkbox.checked) {
                checkbox.click();
            }
        })
    })
}


function openNav() {
    document.getElementById("mySidenav").style.width = "300px";
    document.getElementById("main").style.marginLeft = "300px";
    document.querySelector("header").style.marginLeft = "300px";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginLeft = "0";
    document.querySelector("header").style.marginLeft = "0";


}

function initCartEventListener() {
    let cartButton = document.querySelector('.cart-button');
    cartButton.addEventListener('click', function () {
        window.location.href = "/cart";
    })
}

function cartHoverListener() {
// Get the modal
    var modal = document.getElementById("myModal");

// Get the button that opens the modal
    var btn = document.querySelector(".shopcart");

// Get the <span> element that closes the modal
    var span = modal.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal
    btn.onclick = function () {
        let modals = document.getElementsByClassName("modal");

        Array.from(modals).forEach(function(m) {
            if (m !== modal && m.style.display === "block"){
                m.style.display = "none";
            }
        })
        if (modal.style.display === "block") {
            modal.style.display = "none";
        } else {
            modal.style.display = "block";
        }
    }

// When the user clicks on <span> (x), close the modal
    span.onclick = function () {
        modal.style.display = "none";
    }

// When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }
}

function showCartItems(items) {
    let modalC = document.querySelector(".modal-content");
    let totalPrice = 0;
    let checkoutTag = `<span style="float: left;font-size: 16px;
    font-weight: bold; cursor:pointer;text-decoration: none;" class="checkout">Checkout</span>`;
    modalC.innerHTML = '';
    for (var key in items) {
        let listOfString = key.split(",");

        let name = listOfString[1].substr(7);
        let priceStr = listOfString[2].substr(15);
        let price = parseFloat(parseFloat(priceStr).toFixed(2));

        let amount = parseInt(items[key]);

        totalPrice += parseFloat(parseFloat((amount * price)).toFixed(2));

        let itemTag = `<div class="cartItem">
<p id="itemName" style="float:left; font-size:17px; width:100%;">${name}</p>
<br>
<div><p id="itemPrice" style="float:left;font-size:13px;">Price: <span class="item-price">${(price * amount).toFixed(2)}</span> USD</p>

<pre id="plusBtn" data-itemname="${name}" style="float:right;font-weight: bold;font-size: 20px;cursor:pointer;"> +</pre>
<p style="float:right; font-size: 14px" class="amountOfItem" data-itemname="${name}">${amount}</p>
<pre style="float:right;font-weight: bold;font-size: 20px;cursor:pointer;" id="minusBtn" data-itemname="${name}">- </pre></div>

</div>
`

        modalC.insertAdjacentHTML("beforeend", itemTag)

    }

    modalC.insertAdjacentHTML("beforeend", `<p>__________________________________________</p>`);
    modalC.insertAdjacentHTML("beforeend", `<div class="totalPrice"><p style="float: right">Total: <span id="total-price">${totalPrice.toFixed(2)}</span> USD</p></div>`);
    modalC.insertAdjacentHTML("beforeend", `<br><span class="empty_cart close">Empty Cart</span><br><span class="save_cart close">Save Cart</span><br><div class="cartFooter"><span class="close close_button">Close</span> ${checkoutTag} </div>`)


    setTimeout(() => {
        let span = document.querySelector(".close_button");
        let modal = document.getElementById("myModal");
        let checkout = document.querySelector(".checkout");
        let minusBtns = document.querySelectorAll("#minusBtn");
        console.log(minusBtns)
        let plusBtns = document.querySelectorAll("#plusBtn");
        let inputFields = document.querySelectorAll(".amountOfItem");
        let emptyCart = document.querySelector(".empty_cart");
        let saveCart = document.querySelector(".save_cart")


        span.onclick = function () {
            modal.style.display = "none";
        }

        checkout.onclick = function () {
            window.location.href = "/checkout";
        }

        minusBtns.forEach(button =>{
            button.onclick = function(){
                console.log(button.dataset.itemname)
                inputFields.forEach(inputF =>{
                    if(inputF.dataset.itemname === button.dataset.itemname && parseInt(inputF.textContent) > 0){
                        let cartSize = document.querySelector(".cart-size");
                        let productName = button.dataset.itemname;
                        console.log(productName)
                        let del = "del";
                        let fetchParam = `?name=${productName}&mod=${del}`;
                        updateCart(fetchParam, showCartItems);

                        cartSize.textContent = (parseInt(cartSize.textContent) - 1).toString()
                        inputF.textContent = parseInt(inputF.textContent)-1;
                        setTimeout(()=>{

                            if(cartSize.textContent ==="0"){
                                emptyCartTags()
                                setTimeout(()=>{
                                    let span = document.querySelector(".close");
                                    span.onclick = function () {
                                        modal.style.display = "none";
                                    }
                                },500)
                            }
                        },100)
                        if(inputF.textContent === '0'){
                            document.getElementById('total-price').textContent = (parseFloat(document.getElementById('total-price').textContent) - parseFloat(inputF.parentElement.querySelector('.item-price').textContent)).toFixed(2).toString();
                            inputF.parentElement.parentElement.remove();
                        }
                }
                })
            }
        })
        plusBtns.forEach(button => {
            button.onclick = function() {
                inputFields.forEach(inputF => {
                    if(inputF.dataset.itemname === button.dataset.itemname) {
                        let cartSize = document.querySelector(".cart-size");
                        let productName = button.dataset.itemname;
                        let mod = "add";
                        let fetchParam = `?name=${productName}&mod=${mod}`;
                        updateCart(fetchParam, showCartItems);
                        cartSize.textContent = (parseInt(cartSize.textContent) + 1).toString();
                        inputF.value += 1;
                    }
                })
            }
        })

        emptyCart.onclick = function () {
            updateCart("?name=clear", showCartItems);
            let cartSize = document.querySelector(".cart-size");
            cartSize.textContent = "0"
            setTimeout(()=>{
                emptyCartTags()
                setTimeout(()=>{
                    let span = document.querySelector(".close");
                    span.onclick = function () {
                        modal.style.display = "none";
                    }
                },500)


            },50)
        }

        saveCart.onclick = function () {
            fetch(`save-cart`, {
                method: 'GET',
                credentials: "include",
                cache: "no-cache",
                headers: new Headers ({"content-type": "application/json"})
            })
        }
    }, 1000)
}

function emptyCartTags(){
    let modalC = document.querySelector(".modal-content");
    modalC.innerHTML = '';
    modalC.insertAdjacentHTML("beforeend", `<p id="emptyCart">Your cart is empty!</p>
        <span class="close">Close</span>`)
}

function initSignUpModal() {
// Get the modal
    var modal = document.getElementById("signUpModal");

// Get the button that opens the modal
    var btn = document.getElementById("signup-btn");

// Get the <span> element that closes the modal
    var span = modal.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal
    btn.onclick = function () {
        let modals = document.getElementsByClassName("modal");

        Array.from(modals).forEach(function(m) {
            if (m !== modal && m.style.display === "block"){
                m.style.display = "none";
            }
        })
        if (modal.style.display === "block") {
            modal.style.display = "none";
        } else {
            modal.style.display = "block";
        }
    }

    let alreadyReg = document.getElementsByClassName("already-reg")[0];
    alreadyReg.onclick = function () {
        modal.style.display = "none";
        let logInModal = document.getElementById("logInModal");
        logInModal.style.display = "block";
    }

// When the user clicks on <span> (x), close the modal
    span.onclick = function () {
        modal.style.display = "none";
    }

// When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }

    let form = document.getElementById('signUpForm');
    form.addEventListener('submit', function(event) {
        event.preventDefault();

        validateSignUp(event);

    })

}

async function validateSignUp(event) {
    let form = document.getElementById('signUpForm');

    let name = document.getElementById("name").value;
    let email = document.getElementById("email").value;
    let fetchParam = `?name=${name}&email=${email}`
    let obj = await fetch(`/validate${fetchParam}`)
        .then(response => response.json())
        .catch(error => console.log(error));
    let valid = !(obj['name'] === true || obj['email'] === true);
    if(valid) {
        form.submit();
    } else {
        if(obj['name'] === true){
            document.getElementById('signup-name-invalid').style.display = 'inline-block';
        } else {
            document.getElementById('signup-name-invalid').style.display = 'none';
        }
        if(obj['email'] === true){
            document.getElementById('signup-email-invalid').style.display = 'inline-block';
        } else {
            document.getElementById('signup-email-invalid').style.display = 'none';
        }
    }
}

function initLogInModal() {
// Get the modal
    var modal = document.getElementById("logInModal");

// Get the button that opens the modal
    var btn = document.getElementById("login-btn");

// Get the <span> element that closes the modal
    var span = modal.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal
    btn.onclick = function () {
        let modals = document.getElementsByClassName("modal");

        Array.from(modals).forEach(function(m) {
            if (m !== modal && m.style.display === "block"){
                m.style.display = "none";
            }
        })
        if (modal.style.display === "block") {
            modal.style.display = "none";
        } else {
            modal.style.display = "block";
        }
    }

    let noAcc = document.getElementsByClassName("no-acc")[0];
    noAcc.onclick = function () {
        modal.style.display = "none";
        let signUpModal = document.getElementById("signUpModal");
        signUpModal.style.display = "block";
    }

// When the user clicks on <span> (x), close the modal
    span.onclick = function () {
        modal.style.display = "none";
    }

// When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }
}

function initLogOut() {
    let logOutButton = document.getElementById("logout-btn")
    if (sessionStorage.getItem("loggedIn") !== "true") {
        logOutButton.style.display = "none";
    }
}
