package pl.edu.utp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.edu.utp.model.dictionary.LanguageDict;
import pl.edu.utp.model.enumeration.StudyFormEnum;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String location;        //lokalizacja: np. Poland, Bydgoszcz
    private String type;            //Typ studiów:
    private Integer ects;           //punkty ects
    private String duration;        //Czas trwania nauki
    private String awards;          //uzyskiwany tytuł
    private float tuititionFee;     //Czesne
    private float applicationFee;   //Opłata rekrutacyjna
    private String overview;        //przegląd

    /**
     * Forma studiów:
     *      FULL_TIME - dzienne
     *      PART_TIME - zaoczne, weekendowe
     */
    @Enumerated(EnumType.STRING)
    private StudyFormEnum studyFormEnum;

    /**
     * Wydział
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Department department;

    /**
     * Języki wykładowe
     */
    @OneToMany
    @JoinTable
    private List<LanguageDict> languageDicts;

    /**
     * Wymagania kwalifikacyjne, dokumenty itd.
     */
    @OneToMany
    @JoinTable
    private List<EntryQualification> entryQualifications;

    /**
     * Wymagania językowe
     */
    @OneToMany
    @JoinTable
    private List<LanguageQualification> languageQualifications;

}
