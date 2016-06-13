package playground.rest;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import playground.api.command.CreditAccountCommand;
import playground.api.command.DebitAccountCommand;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class RestController {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private DataSource dataSource;

    @RequestMapping()
    @ResponseBody
    public String index() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Double>> queryResult = jdbcTemplate.query("SELECT * from account_view ORDER BY account_no", (rs, rowNum) -> {
            return new HashMap<String, Double>() {{
                put(rs.getString("ACCOUNT_NO"), rs.getDouble("BALANCE"));
            }};
        });

        String result = "";
        Iterator<Map<String, Double>> iterator = queryResult.iterator();
        while (iterator.hasNext()) {
            Map<String, Double> resultMap = iterator.next();
            String accountId = resultMap.keySet().iterator().next();
            result += "<br>" + accountId + ": " + resultMap.get(accountId);
        }

        return result;
    }

    @RequestMapping("/debit")
    @ResponseBody
    public void doDebit(@RequestParam("account") String accountNumber,
                        @RequestParam("amount") double amount) {
        DebitAccountCommand debitAccountCommandCommand =
                new DebitAccountCommand(accountNumber, amount);

        commandGateway.send(debitAccountCommandCommand);
    }

    @RequestMapping("/credit")
    @ResponseBody
    public void doCredit(@RequestParam("account") String accountNumber,
                         @RequestParam("amount") double amount) {
        CreditAccountCommand creditAccountCommandCommand =
                new CreditAccountCommand(accountNumber, amount);
        commandGateway.send(creditAccountCommandCommand);
    }

}
