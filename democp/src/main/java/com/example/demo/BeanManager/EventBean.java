package com.example.demo.BeanManager;

import com.example.demo.entities.Event;
import com.example.demo.services.EventService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class EventBean implements Serializable {
    @EJB
    private EventService eventService;
    private Event event;

    private Event editEvent;

    @PostConstruct
    public void init(){
        event = new Event();
    }
    public EventBean() {
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String save() {
        if (eventService.saveEvent(event) != null){
            return "admin";
        }
        return "admin?error";
    }

    public List<Event> allEvents(){
        return eventService.getAll();
    }

    public String delete(Long id){
        eventService.deleteEvent(id);
        return "admin";
    }
}
