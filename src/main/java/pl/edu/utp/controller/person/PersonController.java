package pl.edu.utp.controller.person;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.utp.model.Person;
import pl.edu.utp.model.views.Views;
import pl.edu.utp.repository.PersonRepository;

import java.util.List;

import static pl.edu.utp.utils.RestConfiguration.TRANSPORT_DATA_TYPE;

/**
 * Created by Bartosz Szydzik on 17.12.2016.
 */
@RestController
@RequestMapping("/api/person")
public class PersonController {

    PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping(path = "/{id}")
    public Person find(@PathVariable("id") Long id) {
        return personRepository.findOne(id);
    }

    @PostMapping(produces = TRANSPORT_DATA_TYPE, consumes = TRANSPORT_DATA_TYPE)
    public Person save(@RequestBody Person person) {
        System.out.println("Debug: POST: "+person.toString());
        return personRepository.save(person);
    }

//    @PutMapping
//    public void create(@RequestBody Person person) {
//        System.out.println("Debug: PUT: "+person.toString());
//        personRepository.save(person);
//    }

    @DeleteMapping(value = "/{id}")
    public void remove(@PathVariable("id") Long id) {
        personRepository.delete(id);
    }

}
