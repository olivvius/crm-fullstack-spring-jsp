package fr.m2i.spring.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fr.m2i.spring.Serializer.ClientSerializer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

@JsonSerialize(using = ClientSerializer.class)
@NoArgsConstructor
@Getter
@Setter
@ToString(includeFieldNames = false, of = "nom")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "clients", uniqueConstraints = @UniqueConstraint(columnNames = "nom"))
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    @NotEmpty
    private String nom;

    @Column
    private String statut;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL)
    private List<Prestation> prestation = new ArrayList<>();

    public Client(String nom, String statut, List<Prestation> prestation) {
        this.nom = nom;
        this.statut = statut;
        this.prestation = prestation;
    }

    public Client(String nom) {
        this.nom = nom;
    }

    public Client(String nom, String statut) {
        this.nom = nom;
        this.statut = statut;
    }

}
