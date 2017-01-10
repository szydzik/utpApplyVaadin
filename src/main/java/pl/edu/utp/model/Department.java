package pl.edu.utp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Wydział
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Nazwa wydziału
     */
    private String name;

//    /**
//     * Uczelnia do której należy wydział
//     */
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private University university;

    /**
     * Adres wydziału
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;

}
