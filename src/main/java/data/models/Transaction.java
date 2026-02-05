package data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@Document("transactions")
public class Transaction {

    @Id
    private String id;
    private String customerId;
    private String sourceAccoountNumber;
    private String destinationAccoountNumber;
    private BigDecimal amount;
    private TransactionType transactionType;

}
