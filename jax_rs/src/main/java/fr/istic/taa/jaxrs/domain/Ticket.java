package fr.istic.taa.jaxrs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@XmlRootElement(name = "Ticket")

public class Ticket implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlAttribute
    private Long id;
    @XmlAttribute

    private String name;
    @XmlAttribute

    private String description;

    @XmlAttribute
    @ManyToOne
    private Status status;

    @XmlElement
    @ManyToOne
    @JsonIgnoreProperties("tickets") // Ignorer la sérialisation de la liste des tickets de l'utilisateur pour éviter la récursion infinie
    private User user;

    @XmlElement
    @ManyToMany(mappedBy = "ticketList")
    private List<Tag> tagList = new ArrayList<>();

    public Ticket(String name, String description, Status status, User user) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.user = user;
    }

    public Ticket() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String email) {
        this.description = email;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(Tag tag) {
        this.tagList.add(tag);
    }
}