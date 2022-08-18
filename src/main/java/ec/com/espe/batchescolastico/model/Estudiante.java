package ec.com.espe.batchescolastico.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class Estudiante {

    private String id;

    private String internalId;

    private String cedula;

    private String apellido;

    private String nombres;

    private Integer nivel;

}
