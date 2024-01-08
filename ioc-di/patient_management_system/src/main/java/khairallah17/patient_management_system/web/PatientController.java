package khairallah17.patient_management_system.web;

import khairallah17.patient_management_system.entities.Patient;
import khairallah17.patient_management_system.repositories.PatientsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {

    private PatientsRepository patientsRepository;
    @GetMapping(path="/")
    public String index() {
        return "index";
    }

    @GetMapping(path = "/patients")
    @ResponseBody
    public List<Patient> listPatients(){
        return patientsRepository.findAll();
    }

}
