package dtos.requests;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {

    private String sourceAccountNumber;
    private BigDecimal amount;
    private String destinationAccountNumber;
}
