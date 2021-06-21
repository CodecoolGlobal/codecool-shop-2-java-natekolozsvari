initButtons()

function initButtons() {
    let filterBtn = document.getElementById('filter-btn');
    filterBtn.addEventListener('click', openNav);
    let closeBtn = document.getElementById('close-btn');
    closeBtn.addEventListener('click', closeNav);
}

function initSideBar() {
    let categories = document.querySelectorAll('.category-filter');
}

function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
}