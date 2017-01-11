package pl.edu.utp.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.utp.model.Person;

import java.util.List;

/**
 * Created by Bartosz on 2016-12-16.
 */
public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findAll();
    List<Person> findBySurnameStartsWithIgnoreCase(String surname);

}