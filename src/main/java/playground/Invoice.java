package playground;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import playground.command.CreateInvoiceCommand;
import playground.event.InvoiceCreatedEvent;

import java.util.UUID;

@Data
@NoArgsConstructor
public class Invoice extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private String invoiceId;
    private String customerId;
    private boolean sent;
    private double amount;

    @CommandHandler
    public Invoice(CreateInvoiceCommand command) {
        invoiceId = UUID.randomUUID().toString();
        System.out.println("creating invoice: " + invoiceId);

        customerId = command.getCustomerId();
        amount = command.getAmount();
        sent = false;
        apply(new InvoiceCreatedEvent(invoiceId, customerId, amount));
    }

}
