package dtos.requests;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BuyAirTimeRequest {

    private String sourceAccountNumber;
    private BigDecimal amount;
    private String phoneNumber;
    private String networkProvider;
}
