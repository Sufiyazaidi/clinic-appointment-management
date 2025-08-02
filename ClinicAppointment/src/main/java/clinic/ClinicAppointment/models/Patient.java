package clinic.ClinicAppointment.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Patient {
    private Long id;
    @NotBlank(message = "Patient name is required")
    private String name;
    private int age;
    private String gender;
    
}
