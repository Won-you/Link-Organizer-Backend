package com.link_organizer.common.exception;

import com.link_organizer.common.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 잘못된 요청(입력값 오류 등)이 발생했을 때 (HTTP 400 상태)
     * 예: 서비스에서 throw new IllegalArgumentException("에러 메시지")를 던졌을 때
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgumentException(IllegalArgumentException e) {
        return ApiResponse.badRequest("BAD_REQUEST", e.getMessage());
    }

    /**
     * 그 외 예상치 못한 모든 서버 내부 오류를 잡습니다. (HTTP 500 상태)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleAllException(Exception e) {
        // 실제 운영 환경에서는 e.printStackTrace() 대신 log.error()를 사용하는 것이 좋습니다.
        e.printStackTrace(); 
        return ApiResponse.serverError("SERVER_ERROR", "서버 내부 오류가 발생했습니다.");
    }

}
