package playground.command;

import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventhandling.EventBus;
import playground.EventUtils;
import playground.event.InvoiceSentEvent;

@NoArgsConstructor
public class InvoiceCommandHandler {

    private EventBus eventBus;

    public InvoiceCommandHandler(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @CommandHandler
    public void handle(SendInvoiceCommand command) {
        System.out.println("sending invoice: " + command.getInvoiceId());
        eventBus.publish(EventUtils.asEventMessage(new InvoiceSentEvent(command.getInvoiceId())));
    }

}
