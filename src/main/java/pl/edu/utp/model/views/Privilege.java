package pl.edu.utp.model.views;

import pl.edu.utp.model.Role;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by xxbar on 10.01.2017.
 */
@Entity
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
}