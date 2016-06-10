package playground.event;

import lombok.Data;

@Data
public class InvoiceSentEvent extends QueueableEvent {

    private final String invoiceId;

}
