package services;

import dtos.requests.BuyAirTimeRequest;
import dtos.requests.TransferRequest;
import dtos.responses.BuyAirTimeResponse;
import dtos.responses.TransferResponse;

public interface TransferServices {

    TransferResponse transfer(TransferRequest transferRequest);

    BuyAirTimeResponse buyAirTime(BuyAirTimeRequest buyAirTimeRequest);
}
