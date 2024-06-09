
document.getElementById('filter-button').addEventListener('click', function(event) {
    openMenu();
});

document.getElementById('forSideMenu').addEventListener('click', function(event) {
    closeMenu();
});

let buttons = document.getElementsByClassName('module-button');
for (let i = 0; i < buttons.length; i++) {
    buttons[i].onclick = function() {
        this.parentElement.remove();
    };
}

function openMenu() {
    const menu = document.getElementById('sideMenu');
    const forMenu = document.getElementById('forSideMenu');
    menu.style.width = '300px';
    menu.style.borderRight = '1px solid #2F0D2C';
    forMenu.style.width = '100%';
    forMenu.style.transition = 'opacity 0.5s';
    forMenu.style.opacity = '80%';
}

function closeMenu() {
    const menu = document.getElementById('sideMenu');
    const forMenu = document.getElementById('forSideMenu');
    forMenu.style.transition = 'opacity 0.5s';
    forMenu.style.opacity = '0';
    menu.style.width = '0';
    menu.style.border = 'none';
    forMenu.style.width = '0';
}

function addModule(id, name, moduleType) {
    if(document.getElementById(id)) return;

    let module = document.createElement('div');
    let type;
    let selected;
    switch (moduleType) {
        case "filter-client-group": type="client"; selected="Clients"; break;
        case "filter-project-group": type="project"; selected="Projects"; break;
        case "filter-manager-group": type="manager"; selected="Managers"; break;
        case "filter-developer-group": type="developer"; selected="Developers"; break;
        case "filter-tester-group": type="tester"; selected="Testers"; break;
    }
    module.className = type + '-module';
    module.id = id;

    let nameLabel = document.createElement('p');
    nameLabel.className = 'module-label';
    nameLabel.textContent = name;
    module.appendChild(nameLabel);

    let idValue = document.createElement('input');
    idValue.type = 'hidden';
    idValue.name = 'selected' + selected;
    idValue.value = id;
    module.appendChild(idValue);

    let deleteButton = document.createElement('button');
    deleteButton.classList.add('button');
    deleteButton.classList.add('module-button');
    deleteButton.type = 'button';
    deleteButton.innerHTML = '&times;';
    deleteButton.onclick = function () {
        module.remove();
    };
    module.appendChild(deleteButton);

    document.getElementById(type + 'sFilterContainer').appendChild(module);
}

let multipleSelectors = document.getElementsByClassName('filter-multiple-select');
for (let i = 0; i < multipleSelectors.length; i++) {
    multipleSelectors[i].addEventListener('change', function () {
        if (this.options[this.selectedIndex].value === "") return;
        let id = this.options[this.selectedIndex].value;
        let name = this.options[this.selectedIndex].textContent;
        let type = this.id;
        addModule(id, name, type);
    });
}
