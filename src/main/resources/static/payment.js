// stripe public API key
const stripe = Stripe('pk_test_51ISVypEwEmI24watjCjPGjtBvPxmLRUn2731OZoGaLaAxmjhsHz1oT1UhiZQ6GvGPrixYO5vmtYctEt8sohIsYVf0051TMfjay');

const stripePaymentMethodHandler = async (result) => {
    try {
        const res = await fetch('/user/pay', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                payment_method_id: result.paymentMethod.id,
            }),
        })
        let paymentButton = document.querySelector("#payment-button");
        paymentButton.classList.add("is-loading");
        const paymentResponse = await res.json();
        await handleServerResponse(paymentResponse);
    } catch (e) {
        resetPaymentButton();
        setPaymentWarningMessage("Please enter 4242 4242 4242 4242 as the test card.");
    }
}

const handleServerResponse = async (response) => {
    if (response.requires_action) {
        const {error: errorAction, paymentIntent} =
            await stripe.handleCardAction(response.payment_intent_client_secret);
        const serverResponse = await fetch('/user/pay', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({payment_intent_id: paymentIntent.id})
        });
        await handleServerResponse(await serverResponse.json());
    } else if (response.error) {
        resetPaymentButton();
        setPaymentWarningMessage(response.error);
    } else if (response.invalid) {
        resetPaymentButton();
        setPaymentWarningMessage(response.invalid);
        setTimeout(function () {
            window.location.reload();
        }, 3000);
    } else {
        window.location.href = window.location.origin + '/user/orders/';
    }
}

const setupPaymentForm = () => {
    const elements = stripe.elements();
    const style = {
        base: {
            color: "#32325d",
            fontFamily: '"Helvetica Neue", Helvetica, sans-serif',
            fontSmoothing: "antialiased",
            fontSize: "16px",
            "::placeholder": {
                color: "#aab7c4"
            }
        },
        invalid: {
            color: "#fa755a",
            iconColor: "#fa755a"
        },
    };

    const cardElement = elements.create('card', {style});
    cardElement.mount('#card-element');

    const form = document.getElementById('payment-form');
    form.addEventListener('submit', async (event) => {
        event.preventDefault();
        try {
            const result = await stripe.createPaymentMethod({
                type: 'card',
                card: cardElement,
                billing_details: {
                    address: {line1: document.querySelector("#user-address").value},
                    name: document.querySelector("#user-full-name").value,
                    email: document.querySelector("#user-email").value
                },
            });
            await stripePaymentMethodHandler(result);
        } catch (e) {
            console.log(e);
            setPaymentWarningMessage("Please make sure all fields are field out correctly.");
        }
    });
}

const resetPaymentButton = () => {
    let paymentButton = document.querySelector("#payment-button");
    paymentButton.classList.remove("is-loading");
}

const setPaymentWarningMessage = (message) => {
    const warningContainer = document.getElementById('payment-warning-container');
    const warningContent = document.getElementById('payment-warning-content');
    warningContainer.classList.remove("is-hidden");
    warningContent.innerText = message;
}

setupPaymentForm();