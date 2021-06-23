async function updateCart(data,callback) {
    fetch(`/updateCart${data}`)
        .then((response) => response.json())
        .then((items) => callback(items))
        .catch(error  => console.log(error));
}

window.onload = () => {
    updateCart("", showCartItems)
    let cartSize = document.querySelector(".cart-size");
    cartSize.textContent = cartSize.dataset.value;
    let addToCartButtons = document.querySelectorAll(".btn-success");
    for (let button of addToCartButtons) {

        button.addEventListener('click', () => {
            let productName = button.dataset.name;
            let fetchParam = `?name=${productName}`;
            updateCart(fetchParam,showCartItems);
            cartSize.textContent = (parseInt(cartSize.textContent) + 1).toString()
        });
    }

    initButtons();
    initSideBar();
    // initCartEventListener();
    cartHoverListener();
}
    function initButtons() {
        let filterBtn = document.getElementById('filter-btn');
        filterBtn.addEventListener('click', function() {
            if (this.parentElement.parentElement.style.marginLeft === '300px'){
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

    function cartHoverListener(){
// Get the modal
        var modal = document.getElementById("myModal");

// Get the button that opens the modal
        var btn = document.querySelector(".shopcart");

// Get the <span> element that closes the modal
        var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal
        btn.onclick = function() {
            modal.style.display = "block";
        }

// When the user clicks on <span> (x), close the modal
        span.onclick = function() {
            modal.style.display = "none";
        }

// When the user clicks anywhere outside of the modal, close it
        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    }

    function showCartItems(items){
    let modalC = document.querySelector(".modal-content");
    modalC.innerHTML = '';
        for (var key in items) {
            let listOfString = key.split(",");

            let name = listOfString[1].substr(7);
            let price = parseFloat(listOfString[2].substr(15)).toFixed(2);
            console.log(name+" "+price);
            let amount = items[key];

            let itemTag = `<div class="cartItem">
<p id="itemName" style="float:left; font-size:17px; width:100%;">${name}</p>
<br>
<div><p id="itemPrice" style="float:left;font-size:13px;">Price: ${(price*amount).toFixed(2)} USD</p>

<pre id="plusBtn" style="float:right;font-weight: bold;"> +</pre>
<input style="float:right;" id="amountOfItem" value="${amount}" size="1">
<pre style="float:right;font-weight: bold;" id="minusBtn">- </pre></div>

</div>
`



            modalC.insertAdjacentHTML("beforeend", itemTag )

        }

        modalC.insertAdjacentHTML("beforeend", `<span class="close" style="color: black;
    float: right;
    font-size: 18px;
    font-weight: bold; cursor:pointer;">Close</span>`)
        setTimeout(()=>{
            let span = document.querySelector(".close");
            let modal = document.getElementById("myModal");
            span.onclick = function() {
                modal.style.display = "none";}
        },1000)





    }



