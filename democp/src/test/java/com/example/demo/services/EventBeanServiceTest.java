package com.example.demo.services;

import com.example.demo.entities.Event;
import com.example.demo.entities.EventType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;


class EventBeanServiceTest {

    @Mock
    private EntityManagerFactory emf;
    @Mock
    private EntityManager em;

    @InjectMocks
    private EventService eventService;

    @Mock
    private EntityTransaction et;

    private Event event;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        event = new Event("title 1" , "discription Event" , new Date() , 100 , EventType.CONFERENCE);
    }

    @Test
    void save_Event_Success() {
        // Arrange
        when(emf.createEntityManager()).thenReturn(em);
        when(em.getTransaction()).thenReturn(et);

        // Act
        Event savedEvent = eventService.saveEvent(event);

        // Assert
        Assertions.assertEquals(event, savedEvent);
        verify(this.et , times(1)).begin();
        verify(this.et , times(1)).commit();
        verify(this.em , times(1)).persist(this.event);
    }

    @Test
    public void remove_Event_Success(){
        when(emf.createEntityManager()).thenReturn(em);
        when(em.getTransaction()).thenReturn(et);

        Mockito.doAnswer(Answers.CALLS_REAL_METHODS).when(em).remove(event);
        boolean result = eventService.deleteEvent(event.getId());

        // Assert
        Assertions.assertEquals(result, true);
        verify(this.et , times(1)).begin();
        verify(this.et , times(1)).commit();
    }

//    public void get_Events(){
//        when(emf.createEntityManager()).thenReturn(em);
//        when(em.getTransaction()).thenReturn(et);
//
//        when(em.)
//        List<Event> events = this.eventService.getAll();
//
//        Assertions.assertEquals(events.get(0) , this.event);
//    }



}