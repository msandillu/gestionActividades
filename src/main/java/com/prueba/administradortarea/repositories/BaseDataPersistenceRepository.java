package com.prueba.administradortarea.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.util.Objects;

public class BaseDataPersistenceRepository implements DataPersistenceRepository {

    private SessionFactory sessionFactory;

    public BaseDataPersistenceRepository(SessionFactory sessionFactory) {
        Objects.requireNonNull(sessionFactory, "sessionFactory is required");
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Object object) {
        Long start = startTime();
        Session session = getSessionFactory().getCurrentSession();
        session.save(object);
    }

    @Override
    public void remove(Object object) {
        Long start = startTime();
        Session session = getSessionFactory().getCurrentSession();
        session.remove(object);
    }

   /* @Override
    public void removeById(Long id, Class clazz) {
        Long start = startTime();
        SqlSpecification sqlSpecification = new RemoveByIdSpecification(id, clazz);
        Session session = getSessionFactory().getCurrentSession();
        Query query = session.createQuery(sqlSpecification.toSqlQuery());
        query.executeUpdate();
        metrics.recordTime(MetricNames.DURATION_DB, duration(start), String.format(MetricTags.FLOW, clazz.getSimpleName()));
    }

    @Override
    public void update(Object object) {
        Long start = startTime();
        Session session = getSessionFactory().getCurrentSession();
        session.update(object);
        metrics.recordTime(MetricNames.DURATION_DB, duration(start), String.format(MetricTags.FLOW, object.getClass().getSimpleName()));
    }*/

    SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private Long startTime() {
        return System.currentTimeMillis();
    }

    private Long duration(Long start) {
        return System.currentTimeMillis() - start;
    }

}
