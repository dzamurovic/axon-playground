package playground;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;
import playground.command.CreateInvoiceCommand;
import playground.command.SendInvoiceCommand;
import playground.event.InvoiceCreatedEvent;
import playground.event.InvoiceSentEvent;

import java.util.Random;
import java.util.UUID;

public class InvoiceTest {

    private FixtureConfiguration fixture;

    @Before
    public void setUp() {
        fixture = Fixtures.newGivenWhenThenFixture(Invoice.class);
    }

    @Test
    public void testCreateInvoice() {
        String invoiceId = UUID.randomUUID().toString();
        String customerId = UUID.randomUUID().toString();
        double amount = new Random().nextDouble() * 1000;
        fixture.given()
                .when(new CreateInvoiceCommand(invoiceId, customerId, amount))
                .expectEvents(new InvoiceCreatedEvent(UUID.randomUUID().toString(), customerId, amount));
    }

    @Test
    public void testSendInvoice() {
        String invoiceId = UUID.randomUUID().toString();
        String customerId = UUID.randomUUID().toString();
        double amount = new Random().nextDouble() * 1000;
        fixture.given(new InvoiceCreatedEvent(invoiceId, customerId, amount))
                .when(new SendInvoiceCommand(invoiceId))
                .expectEvents(new InvoiceSentEvent(invoiceId));
    }

}
