
package bank.controller;

import bank.domain.Account;
import bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/bank")
@CrossOrigin(origins = "*") // Enable CORS for all origins
public class BankController {

//    record
    public record AccountRecord(long accountNumber,String customerName){}
    public record AccountList(Collection<Account> accountCollection){}

    @Autowired
    private AccountService accountService;

    @PostMapping("/accounts")
    public ResponseEntity<?> createAccount(@RequestBody AccountRecord accountRecord) {
        Account account = accountService.createAccount(accountRecord.accountNumber, accountRecord.customerName);

        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @PostMapping("/accounts/{accountNumber}/deposit")
    public ResponseEntity<String> deposit(@PathVariable long accountNumber, @RequestParam double amount) {
        accountService.deposit(accountNumber, amount);
        return new ResponseEntity<>("Deposit successful!", HttpStatus.OK);
    }

    @PostMapping("/accounts/{accountNumber}/withdraw")
    public ResponseEntity<String> withdraw(@PathVariable long accountNumber, @RequestParam double amount) {
        try {
            accountService.withdraw(accountNumber, amount);
            return new ResponseEntity<>("Withdrawal successful!", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/accounts/transfer")
    public ResponseEntity<String> transfer(@RequestParam long fromAccountNumber, @RequestParam long toAccountNumber, @RequestParam double amount) {
        try {
            accountService.transferFunds(fromAccountNumber, toAccountNumber, amount, "Transfer");
            return new ResponseEntity<>("Transfer successful!", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/accounts/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable long accountNumber) {
        Account account = accountService.getAccount(accountNumber);
        if (account != null) {
            return new ResponseEntity<>(account, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/accounts")
    public ResponseEntity<Collection<Account>> getAllAccounts() {
        Collection<Account> accounts = accountService.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
}
