package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "Feature")
public class Bug extends Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Vous pouvez ajouter des attributs spécifiques aux fonctionnalités ici

    public Bug(String name, String description, Status status, User user) {
        super(name, description, status, user);
    }

    public Bug() {
        super();
    }

    // Vous pouvez ajouter des méthodes spécifiques aux fonctionnalités ici, si nécessaire.
}
