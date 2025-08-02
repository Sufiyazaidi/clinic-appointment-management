package clinic.ClinicAppointment.service;

import clinic.ClinicAppointment.exception.ResourceNotFoundException;
import clinic.ClinicAppointment.models.Doctor;
import clinic.ClinicAppointment.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + id));
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
    
    
    public boolean isSlotAvailable(Long doctorId, LocalDateTime slot) {
        return doctorRepository.findById(doctorId)
                .map(doctor -> Optional.ofNullable(doctor.getAvailableSlots())
                        .map(slots -> slots.contains(slot))
                        .orElse(false))
                .orElse(false);
    }
    
    public void bookSlot(Long doctorId, LocalDateTime slot) {
        doctorRepository.findById(doctorId)
                .ifPresent(doctor -> {
                    Optional.ofNullable(doctor.getAvailableSlots())
                            .ifPresent(slots -> slots.remove(slot));
                    doctorRepository.save(doctor);
                });
    }
    
    public Doctor getDoctorByIdWithDateFilter(Long id, String date) {
        Doctor doctor = getDoctorById(id);
        
        if (date != null) {
            LocalDate targetDate = LocalDate.parse(date); // format: yyyy-MM-dd
            List<LocalDateTime> filteredSlots = doctor.getAvailableSlots().stream()
                .filter(slot -> slot.toLocalDate().equals(targetDate))
                .collect(Collectors.toList());
            doctor.setAvailableSlots(filteredSlots);
        }
        
        return doctor;
    }
}
