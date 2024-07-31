package bank;


import bank.domain.Account;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class AccountTest{
    @Test
    public void testDeposit(){
        Account account = new Account();
        account.deposit(100.0);

//        NB: closeTo is used to compare floating point numbers and the last value is the delta(e.g 0.01)
        assertThat(
                account.getBalance(),
                closeTo(100.0,0.01)
        );
    }


}