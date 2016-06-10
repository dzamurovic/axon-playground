package playground.command;

import lombok.Value;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

@Value
public class CreateInvoiceCommand {

    @TargetAggregateIdentifier
    private String invoiceId;
    private String customerId;
    private double amount;

    public CreateInvoiceCommand(String invoiceId, String customerId, double amount) {
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.amount = amount;
    }

}
