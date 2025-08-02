package clinic.ClinicAppointment.repository;

import clinic.ClinicAppointment.models.Patient;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
@Repository
public class PatientRepository {
    private final Map<Long, Patient> patientMap = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();
    public Patient save(Patient patient) {
        patient.setId(idGenerator.incrementAndGet());
        patientMap.put(patient.getId(), patient);
        return patient;

    }
    public Optional<Patient> findById(Long id) {
        return Optional.ofNullable(patientMap.get(id));
    }

    public List<Patient> findAll() {
        return new ArrayList<>(patientMap.values());
    }
    
}
