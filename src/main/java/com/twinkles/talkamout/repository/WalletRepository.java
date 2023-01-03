package com.twinkles.talkamout.repository;

import com.twinkles.talkamout.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findWalletByWalletAddress(String walletAddress);
}
