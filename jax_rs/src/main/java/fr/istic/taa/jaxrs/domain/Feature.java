package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "Feature")
public class Feature extends Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Vous pouvez ajouter des attributs spécifiques aux fonctionnalités ici

    public Feature(String name, String description, Status status, User user) {
        super(name, description, status, user);
    }

    public Feature() {
        super();
    }

    // Vous pouvez ajouter des méthodes spécifiques aux fonctionnalités ici, si nécessaire.
}
