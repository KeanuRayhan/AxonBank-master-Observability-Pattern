package org.axonframework.samples.bank.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.sentry.Sentry;

@RestController
public class TestController {

    @GetMapping("/test-error")
    public ResponseEntity<String> testError() throws Exception {
        // Ini akan dilempar dan ditangani otomatis oleh SentryExceptionResolver
        throw new Exception("This is a test error triggered for Sentry!");
    }

    @GetMapping("/test-sentry-manual")
    public ResponseEntity<String> testSentryManual() {
        // Manual capture, jika ingin mengirim log custom
        try {
            throw new Exception("Manual capture to Sentry from /test-sentry-manual");
        } catch (Exception e) {
            Sentry.captureException(e);
            return ResponseEntity.ok("Error manual berhasil dikirim ke Sentry: " + e.getMessage());
        }
    }
}
