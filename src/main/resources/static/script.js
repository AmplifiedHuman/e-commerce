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
    let mobileTotal = document.querySelector("#cart-total-amount-mobile");
    if (updateCartPrice) {
        const cartPrice = document.querySelector("#cart-total-price");
        cartPrice.textContent = responseText;
    }
    if (total) {
        total.textContent = responseText;
        mobileTotal.textContent = responseText;
        return total.textContent;
    }
    return null;
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

const addToCart = (productID, isBuyNow) => {
    const quantityField = document.querySelector("#product-quantity");
    const quantity = parseInt(quantityField.textContent);
    const baseURL = window.location.origin;
    editCart(productID, quantity, true, false, null);
    if (isBuyNow) {
        window.location = baseURL + "/cart/";
    }
}

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
    let response = await fetch(baseURL + "/admin/order", {
        method: 'POST',
        body: data,
    });
    response = await response.json();
    if (response.success) {
        const orderStatusField = document.querySelector("#order-status");
        orderStatusField.textContent = newOrderStatus;
    }
}

function viewMessage(id) {
    $.ajax({
        url: "/user/contact/" + id,
        success: function (result) {
            $("#contact-view").html(result);
        }
    });
}

function viewMessageAdmin(id) {
    $.ajax({
        url: "/admin/contact/" + id,
        success: function (result) {
            $("#contact-view").html(result);
            if (document.getElementById('messageType').textContent === "ANSWERED") {
                $("#reply-box").hide();
            }
        }
    });
}

function viewNewMessage(id) {
    $.ajax({
        url: "/user/contactForm/" + id,
        success: function (result) {
            $("#contact-view").html(result);
        }
    });
}

const sendMessage = async (id) => {
    const baseURL = window.location.origin;
    const subjectSelect = document.getElementById('subject');
    let subject;
    let messageType;
    if (document.getElementById('messageType') === null) {
        messageType = "NEW";
        subject = subjectSelect[subjectSelect.selectedIndex].value;
    } else {
        messageType = "ANSWERED";
        subject = subjectSelect.textContent;
    }
    const messageContent = document.getElementById('messageContent').value;
    let data = new URLSearchParams();
    data.append('id', id);
    data.append('subject', subject);
    data.append('messageContent', messageContent);
    data.append('messageType', messageType);
    if (messageType === "ANSWERED") {
        const messageId = document.getElementById('messageId').textContent;
        data.append('messageId', messageId);
        await fetch(baseURL + "/admin/contact/add", {
            method: 'POST',
            body: data,
        });
    } else {
        await fetch(baseURL + "/user/contact/add", {
            method: 'POST',
            body: data,
        });
    }
    location.reload();
}

const removeProduct = async (id) => {
    const baseURL = window.location.origin;
    let data = new URLSearchParams();
    data.append('id', id);
    let response = await fetch(baseURL + "/admin/delete", {
        method: 'POST',
        body: data,
    });
    response = await response.json();
    if (response.success) {
        document.querySelector("#product-id-" + id).remove();
    }
}

function exportUserDataPdf() {
    const doc = new jsPDF();
    doc.fromHTML($('#user-data').html(), 15, 15, {
        'width': 170,
    });
    doc.save('your-data.pdf');
}

let timerId;
const searchInput = document.querySelector("#search-input");

const updateSearchResults = async () => {
    const baseURL = window.location.origin;
    const searchBox = document.querySelector('.search-result-box');
    const searchString = searchInput.value;
    searchBox.innerHTML = "";
    if (searchString !== null && searchString.trim() !== '') {
        const response = await fetch(baseURL + "/instant-search?name=" + encodeURI(searchString));
        const json = await response.json();
        for (let i = 0; i < json.length; i++) {
            let link = document.createElement("a");
            link.href = baseURL + "/product/" + json[i].id;
            link.classList.add("search-result");
            link.textContent = json[i].name;
            link.tabIndex = 0;
            searchBox.appendChild(link);
        }
    }
}

const debounceFunction = (func, delay) => {
    clearTimeout(timerId);
    timerId = setTimeout(func, delay);
}

searchInput.addEventListener('keydown', (event) => {
    if (event.keyCode !== 9) {
        debounceFunction(updateSearchResults, 200);
    }
});

// loading
const loadAddUpdateButton = () => {
    const addForm = document.querySelector("#add-form");
    const editForm = document.querySelector("#edit-form");
    if (addForm !== null && addForm.checkValidity()) {
        const addButton = document.querySelector("#add-button");
        addButton.addEventListener("click", () => {
            addButton.classList.add("is-loading");
        });
    }
    if (editForm !== null && editForm.checkValidity()) {
        const updateButton = document.querySelector("#update-button");
        updateButton.addEventListener("click", () => {
            updateButton.classList.add("is-loading");
        });
    }
}

updateTotal(false).catch(e => console.log(e));
loadAddUpdateButton();
search();