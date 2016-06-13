package playground.commandhandler;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import playground.api.command.DebitAccountCommand;
import playground.domain.Account;

@Component
public class DebitAccountHandler {

    @Autowired
    private Repository repository;

    @CommandHandler
    public void handle(DebitAccountCommand debitAccountCommandCommand) {
        Account account = (Account) repository.load(debitAccountCommandCommand.getAccount());

        System.out.println("DebitAccountHandler: debit the account.");
        System.out.println("Account no. " + debitAccountCommandCommand.getAccount());
        System.out.println("Amount: " + debitAccountCommandCommand.getAmount());
        System.out.println("Current balance: " + account.getBalance());

        account.debit(debitAccountCommandCommand.getAmount());
    }

}
