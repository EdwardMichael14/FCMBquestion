package dtos.responses;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferResponse {

    private String message;
    private BigDecimal amount;
    private String accountNumber;
}
