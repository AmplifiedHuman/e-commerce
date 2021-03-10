package ie.ucd.ibot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ConfirmPaymentRequest {
    @JsonProperty("payment_method_id")
    private String paymentMethodId;

    @JsonProperty("payment_intent_id")
    private String paymentIntentId;
}
