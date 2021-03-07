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

// Cart
const editCart = (productID, quantity, isAdd, updateRow, button) => {
    const baseURL = window.location.origin;
    let data = new URLSearchParams();
    data.append('productID', productID);
    data.append('quantity', quantity);
    let url;
    if (isAdd) {
        url = baseURL + "/cart/add";
    } else {
        url = baseURL + "/cart/remove";
    }
    fetch(url, {
        method: 'POST',
        body: data,
    }).then(r => r.json()).then(result => {
        updateTotal(updateRow);
        if (updateRow) {
            if (isAdd) {
                button.previousElementSibling.textContent = result.count;
            } else {
                button.nextElementSibling.textContent = result.count;
            }
            button.parentNode.parentNode.nextElementSibling.textContent = result.price;
        }
    })
}

const deleteFromCart = (productID, button) => {
    const baseURL = window.location.origin;
    let data = new URLSearchParams();
    data.append('productID', productID);
    fetch(baseURL + "/cart/delete", {
        method: 'POST',
        body: data,
    }).then(r => r.json())
        .then(result => {
            console.log(result);
            if (result.success) {
                updateTotal(true);
                button.closest("tr").remove();
            }
        });
}

const updateTotal = (updateCartPrice) => {
    const baseURL = window.location.origin;
    fetch(baseURL + "/cart/total").then(r => r.text()).then(result => {
        const total = document.querySelector("#cart-total-amount");
        total.textContent = result;
        if (updateCartPrice) {
            const cartPrice = document.querySelector("#cart-total-price");
            cartPrice.textContent = result;
        }
    });
}

updateTotal(false);
search();