package org.axonframework.samples.bank.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.sentry.Sentry;

@RestController
public class TestController {

    @GetMapping("/test-error")
    public ResponseEntity<String> testError() {
        try {
            throw new Exception("This is a test.");
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.status(500)
                .body("Error telah dikirim ke Sentry: " + e.getMessage());
        }
    }
    
    @GetMapping("/test-sentry")
    public ResponseEntity<String> testSentry() {
        try {
            throw new Exception("Ini adalah error tes untuk Sentry dari TestController!");
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok("Error berhasil dikirim ke Sentry: " + e.getMessage());
        }
    }
}