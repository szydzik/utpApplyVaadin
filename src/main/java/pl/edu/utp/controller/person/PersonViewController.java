package pl.edu.utp.controller.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.utp.model.Person;
import pl.edu.utp.repository.PersonRepository;

import java.util.List;

import static pl.edu.utp.config.RestConfiguration.TRANSPORT_DATA_TYPE;

/**
 * Created by Bartosz Szydzik on 06.01.2017.
 */
@RestController
@RequestMapping("/api/person")
public class PersonViewController {

    PersonRepository personRepository;

    @Autowired
    public PersonViewController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping(value = "", produces = TRANSPORT_DATA_TYPE)
    public List<Person> findAll() {
        return personRepository.findAll();
    }

}
