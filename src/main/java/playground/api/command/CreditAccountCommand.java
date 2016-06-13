package playground.api.command;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class CreditAccountCommand {

    private String account;
    private Double amount;

}
