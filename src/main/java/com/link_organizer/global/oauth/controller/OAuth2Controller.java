package com.link_organizer.global.oauth.controller;

import com.link_organizer.common.response.ApiResponse;
import com.link_organizer.domain.accounts.dto.AccountLoginResponseDto;
import com.link_organizer.domain.accounts.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class OAuth2Controller {

    private final AccountService accountService;

    @PostMapping("/google")
    public ApiResponse<AccountLoginResponseDto> googleLogin(@RequestParam String idToken) {
        AccountLoginResponseDto response = accountService.googleLogin(idToken);
        return ApiResponse.success(response);
    }


}
