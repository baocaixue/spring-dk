package com.isaac.ch18;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class JobExecutionStatsListener extends JobExecutionListenerSupport {
    private JdbcTemplate jdbcTemplate;

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("---> Singers were saved to the database...");
            jdbcTemplate.query("SELECT first_name,last_name,song FROM SINGER", (rs, row) -> new Singer(rs.getString(1), rs.getString(2), rs.getString(3))).forEach(singer -> log.info(singer.toString()));
        }
    }
}
