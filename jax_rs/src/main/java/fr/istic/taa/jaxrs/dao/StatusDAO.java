package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Status;

public class StatusDAO extends AbstractJpaDao<Long, Status> {

    public StatusDAO() {
        setClazz(Status.class);
    }
}
