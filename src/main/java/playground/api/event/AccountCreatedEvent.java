package playground.api.event;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class AccountCreatedEvent {

    private String accountId;
    private Double balance;

}
