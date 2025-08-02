package clinic.ClinicAppointment.controllers;


import clinic.ClinicAppointment.exception.ResourceNotFoundException;
import clinic.ClinicAppointment.models.Appointment;
import clinic.ClinicAppointment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public Appointment bookAppointment(@RequestParam Long doctorId,
                                       @RequestParam Long patientId,
                                       @RequestParam String slot) {
    LocalDateTime slotTime = LocalDateTime.parse(slot, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return appointmentService.bookAppointment(doctorId, patientId, slotTime);
    }

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }
    
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctorId(@PathVariable Long doctorId) {
    List<Appointment> doctorAppointments = appointmentService.getAppointmentsByDoctorId(doctorId);
    if (doctorAppointments.isEmpty()) {
        throw new ResourceNotFoundException("No appointments found for doctor with ID: " + doctorId);
    }
    return ResponseEntity.ok(doctorAppointments);
}

}
