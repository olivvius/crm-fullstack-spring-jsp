package fr.m2i.spring.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fr.m2i.spring.Serializer.PrestationSerializer;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.validator.constraints.Length;

@JsonSerialize(using = PrestationSerializer.class)
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "prestations")
public class Prestation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Length(min = 3, max = 255, message = "la longueur de la description est au moins 3!")
    @Column
    private String description;

    @Column
    @Positive(message = "doit etre strict. superieur a 0!")
    private int duree;

    @Column
    @PositiveOrZero(message = "doit etre superieur a 0!")
    private double prix;

    @Column
    @org.hibernate.annotations.Generated(value = GenerationTime.ALWAYS)
    @Formula("prix*duree")
    private double prixtotal;

    @Column
    private String etat;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    public Prestation(String description) {
        this.description = description;
    }

    public Prestation(String description, String etat) {
        this.description = description;
        this.etat = etat;
    }

    public Prestation(String description, int duree, int prix, String etat) {
        this.description = description;
        this.duree = duree;
        this.prix = prix;
        this.etat = etat;
    }

}
