package playground;

import org.axonframework.repository.AbstractRepository;

import java.util.HashMap;
import java.util.Map;

public class Repository extends AbstractRepository<Invoice> {

    private Map<String, Invoice> invoiceMap;

    public Repository() {
        super(Invoice.class);
        invoiceMap = new HashMap<String, Invoice>();
    }

    @Override
    protected void doSave(Invoice aggregate) {
        invoiceMap.put(aggregate.getInvoiceId(), aggregate);
    }

    @Override
    protected Invoice doLoad(Object aggregateIdentifier, Long expectedVersion) {
        return invoiceMap.get(aggregateIdentifier);
    }

    @Override
    protected void doDelete(Invoice aggregate) {
        Invoice value = invoiceMap.get(aggregate.getInvoiceId());
        if (value != null)
            invoiceMap.remove(value);
    }

}
