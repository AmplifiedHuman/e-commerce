package ie.ucd.ibot.service;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import ie.ucd.ibot.entity.ConfirmPaymentRequest;
import ie.ucd.ibot.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final UserService userService;

    private final CartService cartService;

    private final CustomerOrderService orderService;

    @Transactional
    public Map<String, Object> handleUserPayment(ConfirmPaymentRequest confirmPaymentRequest, Long userID)
            throws StripeException {
        PaymentIntent intent = null;
        User user = userService.getUserById(userID);
        if (confirmPaymentRequest.getPaymentMethodId() != null) {
            if (cartService.isCartCurrentlyValid(user.getCart())) {
                intent = createPaymentIntent(user.getCart().getTotal(), user, confirmPaymentRequest.getPaymentMethodId());
            } else {
                cartService.removeInvalidItems(user.getCart());
            }
        } else if (confirmPaymentRequest.getPaymentIntentId() != null) {
            intent = PaymentIntent.retrieve(confirmPaymentRequest.getPaymentIntentId());
            intent = intent.confirm();
        }
        return generateResponse(intent, user);
    }

    private Map<String, Object> generateResponse(PaymentIntent intent, User user) {
        Map<String, Object> responseData = new HashMap<>();
        if (intent == null) {
            // invalid items in cart
            responseData.put("invalid", "Invalid items has been removed from the cart, the page will refresh.");
        } else if (intent.getStatus().equals("requires_action")
                && intent.getNextAction().getType().equals("use_stripe_sdk")) {
            responseData.put("requires_action", true);
            responseData.put("payment_intent_client_secret", intent.getClientSecret());
        } else if (intent.getStatus().equals("succeeded")) {
            responseData.put("success", true);
            orderService.createCustomerOrder(user, intent.getId(), intent.getShipping().getAddress().getLine1());
        } else {
            responseData.put("error", "Invalid status");
        }
        return responseData;
    }

    private PaymentIntent createPaymentIntent(double amount, User user, String paymentMethodId) throws StripeException {
        Long finalAmount = Math.round(amount * 100);
        PaymentIntentCreateParams.Shipping shipping = PaymentIntentCreateParams.Shipping.
                builder()
                .setName(user.getFullName())
                .setAddress(PaymentIntentCreateParams.Shipping.Address.builder().setLine1(user.getAddress()).build())
                .build();
        PaymentIntentCreateParams createParams = PaymentIntentCreateParams.builder()
                .setAmount(finalAmount)
                .setCurrency("eur")
                .setReceiptEmail(user.getEmail())
                .setDescription("Payment from InternationalBot")
                .setShipping(shipping)
                .setConfirm(true)
                .setPaymentMethod(paymentMethodId)
                .setConfirmationMethod(PaymentIntentCreateParams.ConfirmationMethod.MANUAL)
                .build();
        return PaymentIntent.create(createParams);
    }
}
