package pl.edu.utp.model.dictionary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * https://pl.wikipedia.org/wiki/Certyfikat_j%C4%99zykowy
 *
 *  Certyfikat językowy – zaświadczenie o znajomości języka obcego na danym poziomie wydawane po odpowiednim egzaminie.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString
public class LanguageCertificateDict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Język ze słownika języków
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private LanguageDict languageDict;

    /**
     * Kod certyfikatu: Dla EN: FCE, CAE ....
     */
    private String code;

    /**
     * Nazwa certyfikatu: First Certificate in English,  Certificate in Advanced English
     */
    private String name;

    /**
     * Opis
     */
    private String description;

}
