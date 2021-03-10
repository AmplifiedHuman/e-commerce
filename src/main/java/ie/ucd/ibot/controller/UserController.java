package ie.ucd.ibot.controller;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import ie.ucd.ibot.entity.ConfirmPaymentRequest;
import ie.ucd.ibot.entity.User;
import ie.ucd.ibot.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private static final String stripeKey =
            "sk_test_51ISVypEwEmI24watTpBGrkUpeN43iz4aRE0MwRFeOumIt1iBpvOgC85qs7CaAKFRj5FYPBPRqrv38hZdP7Ph96Bw00HIj7zB6T";
    private final PaymentService paymentService;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeKey;
    }

    @ResponseBody
    @PostMapping("/pay")
    public Map<String, Object> processPayment(@RequestBody ConfirmPaymentRequest confirmPaymentRequest,
                                              @AuthenticationPrincipal User sessionUser, HttpServletResponse response) {
        PaymentIntent intent = null;
        try {
            Map<String, Object> responseData = paymentService.handleUserPayment(confirmPaymentRequest, sessionUser.getId());
            handleResponse(response, responseData);
            return responseData;
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error, please contact support.");
            return errorResponse;
        }
    }

    private void handleResponse(HttpServletResponse httpServletResponse,
                                Map<String, Object> responseMap) {
        httpServletResponse.setContentType("application/json");
        if (responseMap.containsKey("error") || responseMap.containsKey("invalid")) {
            httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        } else {
            httpServletResponse.setStatus(HttpStatus.OK.value());
        }
    }
}
