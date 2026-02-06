package data.repositories;

import data.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {

    int countByAccountNumberAndTransactionDate(String accountNumber, LocalDateTime startOfTransactionInMonth,  LocalDateTime endOfTransactionInMonth);
}
