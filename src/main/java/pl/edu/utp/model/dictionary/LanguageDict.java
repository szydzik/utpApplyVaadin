package pl.edu.utp.model.dictionary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString
public class LanguageDict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * https://pl.wiktionary.org/wiki/Wikis%C5%82ownik:Kody_j%C4%99zyk%C3%B3w
     * Kod języka: PL, EN, ES, DE
     */
    private String code;

    /**
     * Język: English, Polski, Espanol, Deutsch
     */
    private String language;
}
