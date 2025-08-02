package clinic.ClinicAppointment.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    private Long id;
    @NotBlank(message="Doctor name is required")
    private String name;
    private String specialization;
    private List<LocalDateTime> availableSlots;
}





    

