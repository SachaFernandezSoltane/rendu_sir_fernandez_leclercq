package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.User;

public class UserDAO extends AbstractJpaDao<Long, User> {

    public UserDAO() {
        setClazz(User.class);
    }
}
