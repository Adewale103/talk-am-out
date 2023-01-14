package com.twinkles.talkamout.services.wallet;

import com.twinkles.talkamout.dto.request.CreateWalletRequest;
import com.twinkles.talkamout.dto.response.CreateWalletResponse;
import com.twinkles.talkamout.exceptions.TalkAmOutException;
import com.twinkles.talkamout.model.Wallet;
import com.twinkles.talkamout.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService{
    private final WalletRepository walletRepository;

    @Override
    public CreateWalletResponse createWallet(CreateWalletRequest createWalletRequest) {
        if (createWalletRequest.getBalance().compareTo(BigDecimal.ZERO) >= 0) {
            Wallet wallet = Wallet.builder()
                    .balance(createWalletRequest.getBalance())
                    .build();
            Wallet savedWallet = walletRepository.save(wallet);

            return new CreateWalletResponse(true, savedWallet.getId());
        }
        throw new TalkAmOutException("You do not have sufficient balance to create wallet", 400);
    }

}
