package clinic.ClinicAppointment.repository;

import clinic.ClinicAppointment.models.Doctor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class DoctorRepository {
    
    private final Map<Long, Doctor> doctors = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public Doctor save(Doctor doctor) {
        if (doctor.getId() == null) {
            doctor.setId(idGenerator.incrementAndGet());
        }
        doctors.put(doctor.getId(), doctor);
        return doctor;
    }

    public Optional<Doctor> findById(Long id) {
        return Optional.ofNullable(doctors.get(id));
    }

    public List<Doctor> findAll() {
        return doctors.values().stream()
                .collect(Collectors.toList());
    }
}
