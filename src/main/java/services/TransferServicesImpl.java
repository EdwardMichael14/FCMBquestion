package services;

import dtos.requests.BuyAirTimeRequest;
import dtos.requests.TransferRequest;
import dtos.responses.BuyAirTimeResponse;
import dtos.responses.TransferResponse;
import org.springframework.stereotype.Service;

@Service
public class TransferServicesImpl implements TransferServices {

    @Override
    public TransferResponse transfer(TransferRequest transferRequest) {


        return null;
    }

    @Override
    public BuyAirTimeResponse buyAirTime(BuyAirTimeRequest buyAirTimeRequest) {
        return null;
    }
}
