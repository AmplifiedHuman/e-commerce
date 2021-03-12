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

// alert button
document.addEventListener('DOMContentLoaded', () => {
    (document.querySelectorAll('.notification .delete') || []).forEach(($delete) => {
        const $notification = $delete.parentNode;
        $delete.addEventListener('click', () => {
            $notification.classList.add("is-hidden");
        });
    });
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

const deleteFromCart = async (productID, button) => {
    const baseURL = window.location.origin;
    let data = new URLSearchParams();
    data.append('productID', productID);
    const response = await fetch(baseURL + "/cart/delete", {
        method: 'POST',
        body: data,
    });
    const responseJSON = await response.json();
    if (responseJSON.success) {
        const newTotal = await updateTotal(true);
        button.closest("tr").remove();
        if (newTotal === '$0.00') {
            document.querySelector("#payment-form").remove();
        }
    }
}

const updateTotal = async (updateCartPrice) => {
    const baseURL = window.location.origin;
    const response = await fetch(baseURL + "/cart/total");
    const responseText = await response.text();
    let total = document.querySelector("#cart-total-amount");
    total.textContent = responseText;
    if (updateCartPrice) {
        const cartPrice = document.querySelector("#cart-total-price");
        cartPrice.textContent = responseText;
    }
    return total.textContent;
}

// product page
const plusButton = () => {
    const quantityField = document.querySelector("#product-quantity");
    const quantity = parseInt(quantityField.textContent);
    quantityField.textContent = quantity + 1 + '';
}

const minusButton = () => {
    const quantityField = document.querySelector("#product-quantity");
    const quantity = parseInt(quantityField.textContent);
    if (quantity > 1) {
        quantityField.textContent = quantity - 1 + '';
    }
}

const addToCart = (productID) => {
    const quantityField = document.querySelector("#product-quantity");
    const quantity = parseInt(quantityField.textContent);
    editCart(productID, quantity, true, false, null);
}

updateTotal(false).catch(e => console.log(e));
search();

function backButton() {
    window.history.back();
}

const updateOrder = async (id) => {
    const baseURL = window.location.origin;
    const statusSelect = document.getElementById('new-order-status');
    const newOrderStatus = statusSelect[statusSelect.selectedIndex].value;
    let data = new URLSearchParams();
    data.append('id', id);
    data.append('newOrderStatus', newOrderStatus);
    await fetch(baseURL + "/admin/order", {
        method: 'POST',
        body: data,
    });
    const orderStatusField = document.querySelector("#order-status");
    orderStatusField.textContent = newOrderStatus;
}

const removeProduct = async (id) => {
    try {
        const baseURL = window.location.origin;
        let data = new URLSearchParams();
        data.append('id', id);
        await fetch(baseURL + "/admin/delete", {
            method: 'POST',
            body: data,
        });
        document.querySelector("#product-id-" + id).remove();
    } catch (e) {
        console.log(e);
    }
}