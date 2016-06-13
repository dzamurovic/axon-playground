package playground.commandhandler;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import playground.api.command.CreditAccountCommand;
import playground.domain.Account;

@Component
public class CreditAccountHandler {

    @Autowired
    private Repository repository;

    @CommandHandler
    public void handle(CreditAccountCommand creditAccountCommandCommand) {
        Account account = (Account) repository.load(creditAccountCommandCommand.getAccount());

        System.out.println("CreditAccountHandler: debit the account.");
        System.out.println("Account no. " + creditAccountCommandCommand.getAccount());
        System.out.println("Amount: " + creditAccountCommandCommand.getAmount());
        System.out.println("Current balance: " + account.getBalance());

        account.credit(creditAccountCommandCommand.getAmount());
    }

}
