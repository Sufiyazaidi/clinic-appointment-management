package clinic.ClinicAppointment.models;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class Appointment {
     private Long id;
    @NotBlank(message = "Doctor Id is required")
    private Long doctorId;
    @NotBlank(message = "Patient Id is required")
    private Long patientId;
    private LocalDateTime slot;
    
}
