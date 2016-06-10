package playground.event;

import org.axonframework.eventhandling.annotation.EventHandler;

public class InvoiceEventHandler {

    @EventHandler
    public void onInvoiceSentEvent(InvoiceSentEvent event) {
        System.out.println("invoice sent");
    }

}
