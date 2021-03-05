// Menu bar
document.addEventListener('DOMContentLoaded', () => {
    const $navbarBurgers = Array.prototype.slice.call(document.querySelectorAll('.navbar-burger'), 0);
    if ($navbarBurgers.length > 0) {
        $navbarBurgers.forEach(el => {
            el.addEventListener('click', () => {
                const target = el.dataset.target;
                const $target = document.getElementById(target);
                el.classList.toggle('is-active');
                $target.classList.toggle('is-active');
            });
        });
    }
});

// Search
const search = () => {
    const searchBox = document.querySelector("#search-input");
    searchBox.addEventListener('keydown', e => {
        const searchString = document.querySelector("#search-input").value;
        const baseURL = window.location.origin;
        if (e.key === 'Enter') {
            window.location = baseURL + "/search?name=" + encodeURI(searchString);
        }
    });
}
search();