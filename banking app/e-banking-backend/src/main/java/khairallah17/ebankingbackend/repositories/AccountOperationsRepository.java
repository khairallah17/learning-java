package khairallah17.ebankingbackend.repositories;

import khairallah17.ebankingbackend.entities.AccountOperations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOperationsRepository extends JpaRepository<AccountOperations, Long> {
}
