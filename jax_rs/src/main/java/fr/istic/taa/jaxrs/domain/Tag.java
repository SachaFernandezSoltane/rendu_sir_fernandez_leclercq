package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@XmlRootElement(name = "Tag")
public class Tag implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlAttribute
    private Long id;

    @XmlAttribute
    private String name;

    @XmlElement
    @ManyToMany
    @JoinTable(name = "tag_list")
    private List<Ticket> ticketList = new ArrayList<>();

    public Tag(String name) {
        this.name = name;
    }

    public Tag() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(Ticket ticket) {
        this.ticketList.add(ticket);
    }
}
