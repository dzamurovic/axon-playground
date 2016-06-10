package playground.event;

import lombok.Data;

@Data
public class InvoiceCreatedEvent extends QueueableEvent {

    private final String invoiceId;
    private final String customerId;
    private final double amount;

}
