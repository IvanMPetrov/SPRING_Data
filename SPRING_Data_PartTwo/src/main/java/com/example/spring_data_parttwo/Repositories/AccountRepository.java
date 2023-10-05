package com.example.spring_data_parttwo.Repositories;

import com.example.spring_data_parttwo.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findTopByOrderByBalanceDesc();
}
