package playground;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import playground.command.InvoiceCommandDispatcher;

import java.util.Random;
import java.util.UUID;

public class InvoiceAppRunner implements Runnable {

    private InvoiceCommandDispatcher commandDiscpather;

    public InvoiceAppRunner(InvoiceCommandDispatcher commandDispatcher) {
        this.commandDiscpather = commandDispatcher;
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        InvoiceCommandDispatcher commandDispatcher = context.getBean(InvoiceCommandDispatcher.class);
        InvoiceAppRunner runner = new InvoiceAppRunner(commandDispatcher);
        runner.run();
    }

    @Override
    public void run() {
        final int NUMBER_OF_EVENTS = 1;
        int counter = 0;

        while (counter < NUMBER_OF_EVENTS) {
            final String invoiceId = UUID.randomUUID().toString();
            final String customerId = UUID.randomUUID().toString();
            double amount = new Random().nextDouble();
            commandDiscpather.dispatchCreateInvoiceCommand(invoiceId, customerId, amount);

            counter++;
        }
    }

}
