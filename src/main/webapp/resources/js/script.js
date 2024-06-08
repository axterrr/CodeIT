document.getElementById('filter-button').onclick = function() {
    openMenu();
}

document.getElementById('forSideMenu').addEventListener('click', function(event) {
        closeMenu();
});

function openMenu() {
    const menu = document.getElementById('sideMenu');
    const forMenu = document.getElementById('forSideMenu');
    forMenu.style.display = 'block';
    setTimeout(() => {
        menu.style.width = '300px';
        menu.style.borderRight = '1px solid #2F0D2C';
        forMenu.style.transition = 'opacity 0.5s';
        forMenu.style.opacity = '80%';
    }, 10);
}

function closeMenu() {
    const menu = document.getElementById('sideMenu');
    const forMenu = document.getElementById('forSideMenu');
    /*setTimeout(() => {
        forMenu.style.display = 'none';
    }, 100);*/
    forMenu.style.transition = 'opacity 0.5s';
    forMenu.style.opacity = '0';
    menu.style.width = '0';
    menu.style.border = 'none';
    forMenu.style.display = 'none';
}

/*function openMenu() {
    const menu = document.getElementById('sideMenu');
    menu.style.width = '300px';
    menu.style.borderRight = '1px solid #2F0D2C';
    const forMenu = document.getElementById('forSideMenu');
    forMenu.style.transition = 'opacity 1s';
    forMenu.style.opacity = '80%';
    setTimeout(() => {
        forMenu.style.display = 'block';
    }, 1000);
}

function closeMenu() {
    const forMenu = document.getElementById('forSideMenu');
    forMenu.style.transition = 'opacity 1s';
    forMenu.style.opacity = '0';
    setTimeout(() => {
        forMenu.style.display = 'none';
    }, 1000);
    const menu = document.getElementById('sideMenu');
    menu.style.width = '0';
    menu.style.border = 'none';
}*/
/*document.getElementById('button.filter-button').onclick = function() {
  openMenu();
}

function openMenu() {
  document.getElementById('sideMenu').style.width = '300px';
}

function closeMenu() {
  document.getElementById('sideMenu').style.width = '0';
}*/
