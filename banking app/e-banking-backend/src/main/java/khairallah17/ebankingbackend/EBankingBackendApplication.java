package khairallah17.ebankingbackend;

import khairallah17.ebankingbackend.entities.*;
import khairallah17.ebankingbackend.enums.AccountStatus;
import khairallah17.ebankingbackend.enums.OperationType;
import khairallah17.ebankingbackend.repositories.AccountOperationsRepository;
import khairallah17.ebankingbackend.repositories.BankAccountRepository;
import khairallah17.ebankingbackend.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EBankingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EBankingBackendApplication.class, args);
	}

	//@Bean
	CommandLineRunner start(CustomerRepository customerRepository,
							BankAccountRepository bankAccountRepository,
							AccountOperationsRepository accountOperationsRepository) {
		return args -> {
			Stream.of("Hassan", "yassin", "mohammed").forEach(name -> {
				Customer customer = new Customer();
				customer.setEmail(name+"@gmail.com");
				customer.setName(name);
				customerRepository.save(customer);
			});
			customerRepository.findAll().forEach(cust -> {
				CurrentAccount currentAccount = new CurrentAccount();

				currentAccount.setBalance(Math.random() * 9000);
				currentAccount.setId(UUID.randomUUID().toString());
				currentAccount.setCreationDate(new Date());
				currentAccount.setStatus(AccountStatus.CREATED);
				currentAccount.setCustomer(cust);
				currentAccount.setOverDraft(9000);
				bankAccountRepository.save(currentAccount);

				SavingAccount savingAccount = new SavingAccount();
				savingAccount.setId(UUID.randomUUID().toString());
				savingAccount.setBalance(Math.random() * 9000);
				savingAccount.setCreationDate(new Date());
				savingAccount.setStatus(AccountStatus.CREATED);
				savingAccount.setCustomer(cust);
				savingAccount.setInterestRate(5.5);
				bankAccountRepository.save(savingAccount);

			});

			bankAccountRepository.findAll().forEach(acc -> {
				for (int i = 0 ; i < 5 ; i++) {
					 AccountOperations accountOperations = new AccountOperations();
					 accountOperations.setOperationDate(new Date());
					 accountOperations.setAmount(Math.random() * 12000);
					 accountOperations.setType(Math.random() > 0.5 ? OperationType.DEBIT : OperationType.CREDIT);
					 accountOperations.setBankAccount(acc);
					 accountOperationsRepository.save(accountOperations);
				}
			});
		};
	}

	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository,
										BankAccountRepository bankAccountRepository,
										AccountOperationsRepository accountOperationsRepository) {
		return args -> {
			BankAccount bankAccount = bankAccountRepository.findById("251ebc58-b4b9-419d-8742-cfcc23c85c0b").orElse(null);
			if (bankAccount != null) {
				System.out.println("*************************************");
				System.out.println(bankAccount.getId());
				System.out.println(bankAccount.getBalance());
				System.out.println(bankAccount.getStatus());
				System.out.println(bankAccount.getCreationDate());
				System.out.println(bankAccount.getCustomer().getName());
				System.out.println(bankAccount.getClass().getSimpleName());

				if (bankAccount instanceof CurrentAccount) {
					System.out.println("Over Draft ==> " + (((CurrentAccount) bankAccount).getOverDraft()));
				} else if (bankAccount instanceof SavingAccount) {
					System.out.println("intereset rate ==> " + (((SavingAccount) bankAccount).getInterestRate()));
				}

				bankAccount.getAccountOperations().forEach(op -> {
					System.out.println(op.getType() + "\t" + op.getOperationDate() + "\t" + op.getAmount());
				});

			}
		};
	}

}
