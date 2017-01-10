package pl.edu.utp.controller.univeristy;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.utp.model.University;
import pl.edu.utp.model.views.Views;
import pl.edu.utp.repository.UniversityRepository;

import java.util.List;

import static pl.edu.utp.utils.RestConfiguration.TRANSPORT_DATA_TYPE;

/**
 * Created by Tomala on 2017-01-07.
 */
    @RestController
    @RequestMapping("/api/university")
    public class UniversityViewController {

    UniversityRepository universityRepository;

        @Autowired
        public UniversityViewController(UniversityRepository universityRepository) {
            this.universityRepository = universityRepository;
        }

        @GetMapping(value = "", produces = TRANSPORT_DATA_TYPE)
        @JsonView(Views.ListView.class)
        public List<University> findAll() {
            return universityRepository.findAll();
        }

    }
