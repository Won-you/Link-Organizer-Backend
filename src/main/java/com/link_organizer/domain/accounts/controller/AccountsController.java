package com.link_organizer.domain.accounts.controller;

import com.link_organizer.common.response.ApiResponse;
import com.link_organizer.domain.accounts.dto.JwtResponse;
import com.link_organizer.domain.accounts.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountsController {

   private final AccountService accountService;

   @PostMapping("/refreshToken")
   public ApiResponse<JwtResponse> setRefreshToken(@RequestParam String refreshToken){
      JwtResponse response= accountService.reissue(refreshToken);
        return ApiResponse.success(response);
   }

//   @PostMapping("/info")
//   public ApiResponse<>


}