package ec.com.espe.batchescolastico.config;

import ec.com.espe.batchescolastico.procesos.LeerInsertarTask;
import ec.com.espe.batchescolastico.procesos.ProcesarDataTask;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@EnableBatchProcessing
public class JobConfig {

    private JobBuilderFactory jobs;


    private StepBuilderFactory steps;



    @Bean
    protected Step readAndInsertTask() {
        return steps.get("readAndInsertTask").tasklet(new LeerInsertarTask()).build();
    }
    @Bean
    protected Step processDataTask() {
        return steps.get("processDataTask").tasklet(new ProcesarDataTask()).build();
    }

    @Bean
    public Job processTextFileJob() {
        return jobs.get("processTextFileJob")
                .start(readAndInsertTask())
                .next(processDataTask())
                .build();
    }

}
