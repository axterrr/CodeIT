document.getElementById('filter-button').onclick = function() {
    openMenu();
}

document.addEventListener('click', function(event) {
    const sideMenu = document.querySelector('.side-menu');
    const menuButton = document.querySelector('.filter-button');
    const clickedInsideMenu = sideMenu.contains(event.target);
    if (!clickedInsideMenu && !(event.target === menuButton)) {
        closeMenu();
    }
});

function openMenu() {
    const menu = document.getElementById('sideMenu');
    menu.style.width = '300px';
    menu.style.borderRight = '1px solid #2F0D2C';

}

function closeMenu() {
    const menu = document.getElementById('sideMenu');
    menu.style.width = '0';
    menu.style.border = 'none';
}
/*document.getElementById('button.filter-button').onclick = function() {
  openMenu();
}

function openMenu() {
  document.getElementById('sideMenu').style.width = '300px';
}

function closeMenu() {
  document.getElementById('sideMenu').style.width = '0';
}*/
