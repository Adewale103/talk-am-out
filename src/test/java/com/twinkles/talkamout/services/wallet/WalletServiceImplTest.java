package com.twinkles.talkamout.services.wallet;

import com.twinkles.talkamout.repository.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class WalletServiceImplTest {
    private WalletService walletService;
    @Mock
    private WalletRepository walletRepository;

    @BeforeEach
    void setUp() {
        walletService = new WalletServiceImpl(walletRepository);
    }
}