package com.example.spring_data_parttwo.Services;

import com.example.spring_data_parttwo.Models.Account;
import com.example.spring_data_parttwo.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal amount, int id) {

        Optional<Account> optionalAccount = accountRepository.findById(id);

        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();

            if (account.getBalance().compareTo(amount) >= 0) {
                account.setBalance(account.getBalance().subtract(amount));
                accountRepository.save(account);
                System.out.println("Withdrawal successful. New balance: " + account.getBalance());
            } else {
                System.out.println("Insufficient balance for withdrawal.");
            }
        } else {
            System.out.println("Account not found.");
        }

    }

    @Override
    public void transferMoney(BigDecimal amount, int idFrom, int idTo) {

        Optional<Account> optionalSourceAccount = accountRepository.findById(idFrom);
        Optional<Account> optionalTargetAccount = accountRepository.findById(idTo);

        if (optionalSourceAccount.isPresent() && optionalTargetAccount.isPresent()) {
            Account sourceAccount = optionalSourceAccount.get();
            Account targetAccount = optionalTargetAccount.get();

            if (sourceAccount.getBalance().compareTo(amount) >= 0) {
                sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
                targetAccount.setBalance(targetAccount.getBalance().add(amount));
                accountRepository.saveAll(Arrays.asList(sourceAccount, targetAccount));
                System.out.println("Transfer successful. Source balance: " + sourceAccount.getBalance() +
                        ", Target balance: " + targetAccount.getBalance());
            } else {
                System.out.println("Insufficient balance for transfer.");
            }
        } else {
            System.out.println("One or both accounts not found.");
        }
    }



}



