package pl.edu.utp.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.edu.utp.model.views.Views;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.ListView.class)
    private Long id;

    /**
     * Imię
     */
    @Column(nullable = true)
    @JsonView(Views.ListView.class)
    private String name;

    /**
     * Nazwisko
     */
    @Column(nullable = true)
    @JsonView(Views.ListView.class)
    private String surname;

    /**
     * Wiek
     */
    @Column(nullable = true)
    @JsonView(Views.ListView.class)
    private Integer age;

    /**
     * Data urodzenia
     */
    @Column(nullable = true)
    @JsonView(Views.ListView.class)
    private Date dateOfBirth;

    /**
     * Adres
     */
    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JsonView(Views.Details.class)
    private Address address;

}


//        CascadeType

//        ALL – wszystkie operacje danej relacji będą kaskadowane
//        DETACH – tylko odłączenie encji od kontekstu persystencji
//        MERGE – aktualizacja encji
//        PERSIST – zapisanie encji
//        REFRESH – odświeżenie stanu encji (pobranie stanu z bazy danych i zastąpienie obecnego)
//        REMOVE – usuwanie encji

