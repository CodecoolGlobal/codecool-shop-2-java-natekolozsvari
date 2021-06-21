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
            category = category.substring(0, category.indexOf(' '));
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
}

function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginLeft = "0";

}