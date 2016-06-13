package playground.api.event;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class AccountDebitedEvent {

    private String accountNo;
    private Double amountDebited;
    private Double balance;

}
