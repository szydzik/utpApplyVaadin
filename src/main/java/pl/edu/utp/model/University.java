package pl.edu.utp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonView;
import pl.edu.utp.model.views.Views;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.ListView.class)
    private Long id;
    @JsonView(Views.ListView.class)
    private String name;

    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JsonView(Views.Details.class)
    private Address address;

//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "university")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable
    @JsonView(Views.Details.class)
    private List<Department> departments;

}
