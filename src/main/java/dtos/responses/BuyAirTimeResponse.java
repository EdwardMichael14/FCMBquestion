package dtos.responses;

import lombok.Data;

@Data
public class BuyAirTimeResponse {

    private String phoneNumber;
    private String networkProvider;
    private int amount;

}
