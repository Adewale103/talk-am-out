package com.twinkles.talkamout.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CreateWalletResponse {
    private boolean message;
    private long walletId;
}
