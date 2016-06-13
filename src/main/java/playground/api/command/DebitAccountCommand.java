package playground.api.command;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class DebitAccountCommand {

    private String account;
    private Double amount;

}
