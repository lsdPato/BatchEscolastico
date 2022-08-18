package ec.com.espe.batchescolastico;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
public class BatchEscolasticoApplication {
    @Autowired
    JobLauncher jobLauncher;
    Job job1;

    public static void main(String[] args) {
        SpringApplication.run(BatchEscolasticoApplication.class, args);
    }


    @Scheduled(cron = "0 */1 * * * ?")
    public void perform() throws Exception {

        JobParameters params =
                new JobParametersBuilder()
                        .addString("processTextFile", String.valueOf(System.currentTimeMillis()))
                        .toJobParameters();
        jobLauncher.run(job1, params);
    }

}
