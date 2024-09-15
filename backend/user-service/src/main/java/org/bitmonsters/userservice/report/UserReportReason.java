package org.bitmonsters.userservice.report;

import lombok.Getter;

@Getter
public enum UserReportReason {
    FAKE_PROFILE("FAKE_PROFILE"),
    FRAUD_PROFILE("FRAUD PROFILE"),
    VIOLENCE("VIOLENCE"),
    INAPPROPRIATE("INAPPROPRIATE");

    private final String reason;

    UserReportReason(String reason) {
        this.reason = reason;
    }
}
