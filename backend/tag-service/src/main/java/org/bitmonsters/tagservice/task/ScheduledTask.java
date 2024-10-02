package org.bitmonsters.tagservice.task;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.tagservice.service.ReconciliationJobs;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduledTask {

    private final ReconciliationJobs jobs;

    @Scheduled(cron = "0 0 * * * *") // execute at every hour
    public void executePostCountReconciliation() {
        jobs.calculatePostCount();
    }
}
