package playground.eventhandler;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import playground.api.event.AccountCreditedEvent;

import javax.sql.DataSource;

@Component
public class AccountCreditedEventHandler {

    @Autowired
    private DataSource dataSource;

    @EventHandler
    public void handle(AccountCreditedEvent event) {
        System.out.println("Handling AccountCreditedEvent.");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String updateQuery = "UPDATE account_view SET BALANCE = ? WHERE account_no = ?";
        double newBalance = event.getBalance() + event.getAmountCredited();
        jdbcTemplate.update(updateQuery, new Object[]{newBalance, event.getAccountNo()});

        System.out.println("Account no. " + event.getAccountNo());
        System.out.println("Current balance: " + newBalance);
    }

}
