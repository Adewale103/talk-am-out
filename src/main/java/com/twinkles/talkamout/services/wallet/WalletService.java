package com.twinkles.talkamout.services.wallet;

import com.twinkles.talkamout.dto.request.CreateWalletRequest;
import com.twinkles.talkamout.dto.response.CreateWalletResponse;

public interface WalletService {
    CreateWalletResponse createWallet(CreateWalletRequest createWalletRequest);
}
