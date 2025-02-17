package com.payment.strip_payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class StripeResponse {
    private String status;
    private String message;
    private String sessionId;
    private String sessionUrl;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionUrl() {
        return sessionUrl;
    }

    public void setSessionnUrl(String sessionUrl) {
        this.sessionUrl = sessionUrl;
    }

    // Private constructor
    private StripeResponse(Builder builder) {
        this.status = builder.status;
        this.message = builder.message;
        this.sessionId = builder.sessionId;
        this.sessionUrl = builder.sessionUrl;
    }

    // Static method to get a Builder instance
    public static Builder builder() {
        return new Builder();
    }

    // Builder class
    public static class Builder {
        private String status;
        private String message;
        private String sessionId;
        private String sessionUrl;

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder sessionId(String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public Builder sessionUrl(String sessionUrl) {
            this.sessionUrl = sessionUrl;
            return this;
        }

        public StripeResponse build() {
            return new StripeResponse(this);
        }
    }

}
