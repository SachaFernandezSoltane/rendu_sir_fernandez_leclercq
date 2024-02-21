package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Ticket;

public class TicketDAO extends AbstractJpaDao<Long, Ticket> {

    public TicketDAO() {
        setClazz(Ticket.class);
    }
}
