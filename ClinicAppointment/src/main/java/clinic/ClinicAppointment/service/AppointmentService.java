package clinic.ClinicAppointment.service;

import clinic.ClinicAppointment.exception.ResourceNotFoundException;
import clinic.ClinicAppointment.exception.SlotAlreadyBookedException;
import clinic.ClinicAppointment.exception.InvalidSlotException;
import clinic.ClinicAppointment.models.Appointment;
import clinic.ClinicAppointment.models.Doctor;
import clinic.ClinicAppointment.models.Patient;
import clinic.ClinicAppointment.repository.AppointmentRepository;
import clinic.ClinicAppointment.repository.DoctorRepository;
import clinic.ClinicAppointment.repository.PatientRepository;
import clinic.ClinicAppointment.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientRepository patientRepository;

    
    public synchronized Appointment bookAppointment(Long doctorId, Long patientId, LocalDateTime slot) {
        if (slot.isBefore(LocalDateTime.now())) {
            throw new InvalidSlotException("Cannot book appointments in the past");
        }

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + doctorId));

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + patientId));

        if (!doctorService.isSlotAvailable(doctorId, slot)) {
            throw new InvalidSlotException("Requested slot is not available for doctor: " + doctor.getName());
        }

        // Thread-safe check if slot is already booked
        if (appointmentRepository.isSlotBooked(doctorId, slot)) {
            throw new SlotAlreadyBookedException("This slot is already booked for the doctor");
        }

        // Create and save appointment
        Appointment appointment = new Appointment();
        appointment.setDoctorId(doctorId);
        appointment.setPatientId(patientId);
        appointment.setSlot(slot);
        doctorService.bookSlot(doctorId, slot);

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getAppointmentsByDoctorId(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }
}
