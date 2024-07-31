package bank;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Timer;

import bank.config.ApplicationProperties;
import bank.domain.Account;
import bank.domain.AccountEntry;
import bank.domain.Customer;
import bank.scheduler.BankScheduler;
import bank.service.AccountService;
import bank.service.AppPropertiesService;
import bank.service.IAccountService;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.sql.DataSource;


@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class Application implements CommandLineRunner, RequestHandler<AwsProxyRequest, AwsProxyResponse> {


	@Autowired
	IAccountService accountService;
	@Autowired
	private AppPropertiesService appPropertiesService;

	private  static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
	static {
		try {
			handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(Application.class);
		} catch (Exception e) {
			throw new RuntimeException("Could not initialize Spring Boot application", e);
		}
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class,args);
	}

	@Override
	public AwsProxyResponse handleRequest(AwsProxyRequest awsProxyRequest, Context context) {

		return handler.proxy(awsProxyRequest, context);
	}

	@Override
	public void run(String[] args) throws Exception{

		// create 2 accounts;
		accountService.createAccount(1263862, "Frank Brown");
		accountService.createAccount(4253892, "John Doe");
		//use account 1;
		accountService.deposit(1263862, 240);
		accountService.deposit(1263862, 529);
		accountService.withdrawEuros(1263862, 230);
		//use account 2;
		accountService.deposit(4253892, 12450);
		accountService.depositEuros(4253892, 200);
		accountService.transferFunds(4253892, 1263862, 100, "payment of invoice 10232");
		// show balances

//		Collection<Account> accountlist = accountService.getAllAccounts();
//		Customer customer = null;
//		for (Account account : accountlist) {
//			customer = account.getCustomer();
//			System.out.println("Statement for Account: " + account.getAccountnumber());
//			System.out.println("Account Holder: " + customer.getName());
//			System.out.println("-Date-------------------------"
//							+ "-Description------------------"
//							+ "-Amount-------------");
//			for (AccountEntry entry : account.getEntryList()) {
//				System.out.printf("%30s%30s%20.2f\n", entry.getDate()
//						.toString(), entry.getDescription(), entry.getAmount());
//			}
//			System.out.println("----------------------------------------"
//					+ "----------------------------------------");
//			System.out.printf("%30s%30s%20.2f\n\n", "", "Current Balance:",
//					account.getBalance());
//		}

//		Timer timer = new Timer();
////		timer.schedule(new BankScheduler(accountService.getAllAccounts()), 0, 10000);
//		appPropertiesService.printProperties();


	}


}


