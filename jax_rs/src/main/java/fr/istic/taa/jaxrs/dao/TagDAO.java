package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Tag;

public class TagDAO extends AbstractJpaDao<Long, Tag> {

    public TagDAO() {
        setClazz(Tag.class);
    }
}
