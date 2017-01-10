package pl.edu.utp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.edu.utp.model.dictionary.LanguageCertificateDict;
import pl.edu.utp.model.dictionary.LanguageDict;

import javax.persistence.*;
import java.util.List;

/**
 * Wymagania językowe - wymagane certyfikaty
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString
public class LanguageQualification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Język
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private LanguageDict languageDict;

    /**
     * Certyfikaty wymagane dla danego języka
     */
    @OneToMany
    @JoinTable
    private List<LanguageCertificateDict> languageCertificateDicts;
}
