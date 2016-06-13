package playground.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import playground.api.event.AccountCreatedEvent;
import playground.api.event.AccountCreditedEvent;
import playground.api.event.AccountDebitedEvent;

@Data
@NoArgsConstructor
public class Account extends AbstractAnnotatedAggregateRoot {

    @AggregateIdentifier
    private String id;
    @Setter(value = AccessLevel.NONE)
    private double balance;

    public Account(String accountId, double balance) {
        apply(new AccountCreatedEvent(accountId, balance));
    }

    @EventHandler
    public void handle(AccountCreatedEvent event) {
        this.id = event.getAccountId();
        this.balance = event.getBalance();
    }

    @EventHandler
    public void handle(AccountDebitedEvent event) {
        this.balance -= event.getAmountDebited();
    }

    @EventHandler
    public void handle(AccountCreditedEvent event) {
        this.balance += event.getAmountCredited();
    }

    public void debit(Double debitAmount) {
        System.out.println("Account#" + this.getId());

        if (Double.compare(debitAmount, 0.0d) > 0 &&
                this.balance - debitAmount > -1) {
            System.out.println("Fire AccountDebitedEvent.");
            apply(new AccountDebitedEvent(this.getId(), debitAmount, this.balance));
        } else {
            throw new IllegalArgumentException("Cannot debit with the amount");
        }
    }

    public void credit(Double creditAmount) {
        System.out.println("Account#" + this.getId());

        if (Double.compare(creditAmount, 0.0d) > 0 &&
                Double.compare(creditAmount, 1000000) < 0) {
            System.out.println("Fire AccountCreditedEvent.");
            apply(new AccountCreditedEvent(this.getId(), creditAmount, this.balance));
        } else {
            throw new IllegalArgumentException("Cannot credit with the amount");
        }
    }

}
