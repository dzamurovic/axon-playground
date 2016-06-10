package playground.command;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import playground.Invoice;
import playground.event.InvoiceCreatedEvent;

public class InvoiceCommandDispatcher {

    private CommandGateway commandGateway;
    private EventBus eventBus;
    private EventSourcingRepository<Invoice> invoiceEventSourcingRepository;

    public InvoiceCommandDispatcher(CommandGateway commandGateway, EventBus eventBus, EventSourcingRepository invoiceRepository) {
        this.commandGateway = commandGateway;
        this.eventBus = eventBus;
        this.invoiceEventSourcingRepository = invoiceRepository;
    }

    public void dispatchCreateInvoiceCommand(String invoiceId, String customerId, double amount) {
        System.out.println("create invoice");
        commandGateway.send(new CreateInvoiceCommand(invoiceId, customerId, amount));
    }

    protected void dispatchSendInvoiceCommand(String invoiceId) {
        System.out.println("\nsend invoice: " + invoiceId);
        commandGateway.send(new SendInvoiceCommand(invoiceId));
    }

    @EventHandler
    public void onInvoiceCreatedEvent(InvoiceCreatedEvent event) {
        System.out.println("invoice created: " + event.getInvoiceId());
        invoiceEventSourcingRepository.load(event.getInvoiceId());
        dispatchSendInvoiceCommand(event.getInvoiceId());
    }
    
}
