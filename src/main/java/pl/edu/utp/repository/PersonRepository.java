package pl.edu.utp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.utp.model.Person;

import java.util.List;

/**
 * Created by Bartosz on 2016-12-16.
 */
public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findAll();

}