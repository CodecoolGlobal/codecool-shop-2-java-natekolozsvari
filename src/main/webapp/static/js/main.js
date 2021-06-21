initButtons();
initSideBar();

function initButtons() {
    let filterBtn = document.getElementById('filter-btn');
    filterBtn.addEventListener('click', openNav);
    let closeBtn = document.getElementById('close-btn');
    closeBtn.addEventListener('click', closeNav);
}

function initSideBar() {
    let categoryCheckBoxes = document.querySelectorAll('.category-check');
    categoryCheckBoxes.forEach(function(checkbox){
        checkbox.addEventListener('change', function() {
            let category = checkbox.parentElement.querySelector('.category-check-label').textContent;
            category = category.substring(0, category.indexOf('(')-1);
            let categoryContainer = document.querySelector(`.${category}`);
            if (this.checked) {
                categoryContainer.style.display = 'block';
            } else {
                categoryContainer.style.display = 'none';
            }
        })
    })
    let categoryClear = document.getElementById('category-clear');
    categoryClear.addEventListener('click', function() {
        categoryCheckBoxes.forEach(function(checkbox){
            if(checkbox.checked) {
                checkbox.click();
            }
        })
    })
    let categorySelect = document.getElementById('category-select');
    categorySelect.addEventListener('click', function() {
        categoryCheckBoxes.forEach(function(checkbox){
            if(!checkbox.checked) {
                checkbox.click();
            }
        })
    })
    let supplierCheckBoxes = document.querySelectorAll('.supplier-check');
    supplierCheckBoxes.forEach(function(checkbox){
        console.log(checkbox);
        checkbox.addEventListener('change', function() {
            let supplier = checkbox.parentElement.querySelector('.supplier-check-label').textContent;
            console.log(supplier);
            supplier = supplier.substring(0, supplier.indexOf('(')-1);
            console.log(supplier);
            let cards = document.querySelectorAll('.supplier');
            cards.forEach(function(card) {
                if (card.textContent === supplier) {
                    console.log(card.parentElement);
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
    supplierClear.addEventListener('click', function() {
        supplierCheckBoxes.forEach(function(checkbox){
            if(checkbox.checked) {
                checkbox.click();
            }
        })
    })
    let supplierSelect = document.getElementById('supplier-select');
    supplierSelect.addEventListener('click', function() {
        supplierCheckBoxes.forEach(function(checkbox){
            if(!checkbox.checked) {
                checkbox.click();
            }
        })
    })
}



function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginLeft = "0";

}