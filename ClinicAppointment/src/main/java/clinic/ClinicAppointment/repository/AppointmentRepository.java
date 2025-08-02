package clinic.ClinicAppointment.repository;

import clinic.ClinicAppointment.models.Appointment;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
@Repository
public class AppointmentRepository {
    
    private final Map<Long, Appointment> appointments = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public Appointment save(Appointment appointment) {
        if (appointment.getId() == null) {
            appointment.setId(idGenerator.incrementAndGet());
        }
        appointments.put(appointment.getId(), appointment);
        return appointment;
    }

    public List<Appointment> findAll() {
        return appointments.values().stream()
                .collect(Collectors.toList());
    }

    public List<Appointment> findByDoctorId(Long doctorId) {
        return appointments.values().stream()
                .filter(appointment -> doctorId.equals(appointment.getDoctorId()))
                .collect(Collectors.toList());
    }

    public boolean isSlotBooked(Long doctorId, LocalDateTime slot) {
        return appointments.values().stream()
                .anyMatch(appointment -> 
                    doctorId.equals(appointment.getDoctorId()) && 
                    slot.equals(appointment.getSlot()));
    }
}


