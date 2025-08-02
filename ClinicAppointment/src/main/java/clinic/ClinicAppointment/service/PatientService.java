package clinic.ClinicAppointment.service;


import clinic.ClinicAppointment.exception.ResourceNotFoundException;
import clinic.ClinicAppointment.models.Patient;
import clinic.ClinicAppointment.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + id));
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
}
