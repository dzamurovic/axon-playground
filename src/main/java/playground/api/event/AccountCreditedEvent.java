package playground.api.event;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class AccountCreditedEvent {

    private String accountNo;
    private Double amountCredited;
    private Double balance;

}
