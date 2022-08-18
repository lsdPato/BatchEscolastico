package ec.com.espe.batchescolastico.procesos;

import ec.com.espe.batchescolastico.model.Estudiante;
import ec.com.espe.batchescolastico.servicio.Files;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LeerInsertarTask implements Tasklet, StepExecutionListener {
    private List<Estudiante> datos;
    private  RestTemplate restTemplate;


    @Override
    public void beforeStep(StepExecution arg0) {
        System.out.println("Proceso de lectura iniciado");
        datos = Files.leerArchivo("c:/volumes/estudiantes.txt");
    }




    @Override
    public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {

        ResponseEntity<Estudiante[]> response =
                restTemplate.postForEntity(
                        "localhost:8080/estudiantes/crear", datos,
                        Estudiante[].class);
        Estudiante[] obectArray = response.getBody();
        datos = Arrays.asList(obectArray);
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return ExitStatus.COMPLETED;
    }
}
