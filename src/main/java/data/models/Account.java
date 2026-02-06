package data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Document("accounts")
public class Account {

    @Id
    private String id;
    private Customer customer;
    private String accountNumber;
    private AccountType accountType;
    private BigDecimal balance;
    private LocalDate date =  LocalDate.now();
}
