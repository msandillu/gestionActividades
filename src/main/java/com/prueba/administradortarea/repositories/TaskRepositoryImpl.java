package com.prueba.administradortarea.repositories;

import com.prueba.administradortarea.models.domain.Task;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TaskRepositoryImpl implements TaskRepository {

    private SessionFactory sessionFactory;

    public TaskRepositoryImpl(SessionFactory sessionFactory) {
        Objects.requireNonNull(sessionFactory, "sessionFactory is required");
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Task add(Task task) {
        Session session = null;
        Transaction tx = null;
        try {
            session = getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.persist(task);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (tx != null && tx.isActive()){
                tx.commit();
            }
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        Hibernate.initialize(task);
        return task;
    }

    @Override
    public Boolean remove(Task task) {
        Session session = null;
        Transaction tx = null;
        try {
            session = getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(task);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (tx != null && tx.isActive()){
                tx.commit();
            }
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return false;
    }

    @Override
    public Task findById(Integer taskId) {
        Session session = null;
        Task task = null;
        try {
            session = getSessionFactory().openSession();
            task =  (Task) session.load(Task.class, taskId);
            Hibernate.initialize(task);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return task;
    }

    @Override
    public List<Task> findAll() {
        Session session = null;
        List<Task> taskList = new ArrayList<>();
        try {
            session = getSessionFactory().openSession();
            taskList.addAll(session.createCriteria(Task.class).list());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return taskList;
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
        return this.sessionFactory;
    }

}
