package playground.command;

import lombok.Value;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

@Value
public class SendInvoiceCommand {

    @TargetAggregateIdentifier
    private final String invoiceId;

}
