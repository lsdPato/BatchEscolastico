package ec.com.espe.batchescolastico.procesos;

import ec.com.espe.batchescolastico.model.Estudiante;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class ProcesarDataTask implements Tasklet, StepExecutionListener {
    private List<Estudiante> estudiantes;
    private RestTemplate restTemplate;
    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("Se inicio el servicio");
        ExecutionContext executionContext = stepExecution.getJobExecution().getExecutionContext();
        this.estudiantes = (List<Estudiante>) executionContext.get("Estudiantes");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        for(Estudiante estudiante: estudiantes){
            ResponseEntity<Estudiante> response =
                    restTemplate.postForEntity(
                            "localhost:8080/estudiantes/asignar", estudiante,
                            Estudiante.class);
            Estudiante datos = response.getBody();

            return ExitStatus.COMPLETED;
        }
        return null;

    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        return null;
    }
}
