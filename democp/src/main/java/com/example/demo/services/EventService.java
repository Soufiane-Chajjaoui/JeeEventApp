package com.example.demo.services;

import com.example.demo.entities.Event;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Stateless
@Local
public class EventService {

    EntityManagerFactory emf;
    EntityManager em;

    public EventService() {
        emf = Persistence.createEntityManagerFactory("EventPU");
        em = emf.createEntityManager();
    }

    public Event saveEvent(Event event) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(event);
            et.commit();
            return event;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Event> findEventsByDate(LocalDateTime startDate, LocalDateTime endDate) {
        Query query = em.createQuery("SELECT e FROM Event e WHERE e.dateTime BETWEEN :startDate AND :endDate");
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }

    public List<Event> findEventsByName(String name) {
        Query query = em.createQuery("SELECT e FROM Event e WHERE e.title LIKE :name");
        query.setParameter("name", "%" + name + "%"); // Search by name containing the provided string
        return query.getResultList();
    }

    public Event updateEvent(Event event) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.merge(event);
            et.commit();
            return event;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean deleteEvent(Long eventId) {
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Event event = em.find(Event.class, eventId);
            if (event != null) {
                em.remove(event);
            }
            et.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Event> getAll() {

        try {
            Query query = em.createQuery("SELECT e FROM Event e");
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

}
