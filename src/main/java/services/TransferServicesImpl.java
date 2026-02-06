package services;

import data.models.Account;
import data.models.AccountType;
import data.models.Transaction;
import data.models.TransactionType;
import data.repositories.CustomerRepository;
import data.repositories.TransactionRepository;
import dtos.requests.BuyAirTimeRequest;
import dtos.requests.TransferRequest;
import dtos.responses.BuyAirTimeResponse;
import dtos.responses.TransferResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class TransferServicesImpl implements TransferServices {

    TransactionRepository transactionRepository;
    public TransferServicesImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
}
    CustomerRepository customerRepository;
    public TransferServicesImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public TransferResponse transfer(TransferRequest transferRequest) {

        Account account = new Account();
        account.setAccountNumber(transferRequest.getSourceAccountNumber());
        account.setAccountType(AccountType.BUSINESS);

        Transaction transaction = new Transaction();
        transaction.setSourceAccoountNumber(account.getAccountNumber());
        transaction.setDestinationAccoountNumber(transferRequest.getDestinationAccountNumber());
        transaction.setAmount(transferRequest.getAmount());
        transaction.setTransactionType(TransactionType.TRANSFER);

        LocalDateTime startDate = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        LocalDateTime endDate = LocalDateTime.now();

        int noOfTransaction = transactionRepository.countByAccountNumberAndTransactionDate(account.getAccountNumber(), startDate, endDate);
        int transactionFee = 20;
        double discountFee;
        if(account.getAccountType().equals(AccountType.BUSINESS) && noOfTransaction == 3 && transaction.getAmount().compareTo(BigDecimal.valueOf(150000.00)) > 0) {
            discountFee = 0.27 * transactionFee;
            transaction.setAmount(transaction.getAmount().add(BigDecimal.valueOf(discountFee)));
        }
        else if (account.getAccountType().equals(AccountType.RETAIL) && noOfTransaction == 3 && transaction.getAmount().compareTo(BigDecimal.valueOf(50000.00)) > 0) {
            discountFee = 0.18 * transactionFee;
            transaction.setAmount(transaction.getAmount().add(BigDecimal.valueOf(discountFee)));
        }

        boolean customerOlderThanFourYears = account.getDate().isBefore(LocalDate.now().minusYears(4));
        if(customerOlderThanFourYears && noOfTransaction < 3){
            discountFee = 0.10 * transactionFee;
            transaction.setAmount(transaction.getAmount().add(BigDecimal.valueOf(discountFee)));
        }

        account.setBalance(account.getBalance().subtract(transaction.getAmount()));
        transactionRepository.save(transaction);

        TransferResponse transferResponse = new TransferResponse();

        transferResponse.setAmount(transaction.getAmount());
        transferResponse.setAccountNumber(account.getAccountNumber());
        transferResponse.setMessage("Transfer successful");

        return transferResponse;
    }

    @Override
    public BuyAirTimeResponse buyAirTime(BuyAirTimeRequest buyAirTimeRequest) {

        Account account = new Account();

        account.setAccountNumber(buyAirTimeRequest.getSourceAccountNumber());

        Transaction transaction = new Transaction();
        transaction.setSourceAccoountNumber(account.getAccountNumber());
        transaction.setTransactionType(TransactionType.AIRTIME);
        transaction.setAmount(buyAirTimeRequest.getAmount());
        transaction.setAmount(buyAirTimeRequest.getAmount());
        transaction.setDestinationAccoountNumber(buyAirTimeRequest.getPhoneNumber());
        transaction.setNetworkProvider(buyAirTimeRequest.getNetworkProvider());

        account.setBalance(account.getBalance().subtract(transaction.getAmount()));
        transactionRepository.save(transaction);

        BuyAirTimeResponse buyAirTimeResponse = new BuyAirTimeResponse();
        buyAirTimeResponse.setNetworkProvider(buyAirTimeRequest.getNetworkProvider());
        buyAirTimeResponse.setPhoneNumber(buyAirTimeRequest.getPhoneNumber());
        buyAirTimeResponse.setMessage("Successful");

        return buyAirTimeResponse;
    }
}
