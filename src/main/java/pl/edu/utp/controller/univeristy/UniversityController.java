package pl.edu.utp.controller.univeristy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.utp.model.University;
import pl.edu.utp.repository.UniversityRepository;

import static pl.edu.utp.config.RestConfiguration.TRANSPORT_DATA_TYPE;

/**
 * Created by Bartosz Szydzik on 06.01.2017.
 */
@RestController
@RequestMapping("/api/university")
public class UniversityController {

    UniversityRepository universityRepository;

    @Autowired
    public UniversityController(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @GetMapping(path = "/{id}")
    public University find(@PathVariable("id") Long id) {
        return universityRepository.findOne(id);
    }

    @PostMapping(produces = TRANSPORT_DATA_TYPE, consumes = TRANSPORT_DATA_TYPE)
    public University save(@RequestBody University university) {
        System.out.println("Debug: POST: "+ university.toString());
        return universityRepository.save(university);
    }

//    @PutMapping
//    public void create(@RequestBody Person person) {
//        System.out.println("Debug: PUT: "+person.toString());
//        personRepository.save(person);
//    }

    @DeleteMapping(value = "/{id}")
    public void remove(@PathVariable("id") Long id) {
        universityRepository.delete(id);
    }
}
