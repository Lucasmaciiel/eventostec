package br.com.lmg.eventostec.controllers;

import br.com.lmg.eventostec.domain.Event;
import br.com.lmg.eventostec.dtos.EventRequest;
import br.com.lmg.eventostec.dtos.EventResponse;
import br.com.lmg.eventostec.services.EventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping(consumes = "multipart/form-data")
    public Event create(@RequestParam("title") String title,
                        @RequestParam(value = "description", required = false) String description,
                        @RequestParam("date") LocalDateTime date,
                        @RequestParam("city") String city,
                        @RequestParam("uf") String state,
                        @RequestParam("remote") Boolean remote,
                        @RequestParam("eventUrl") String eventUrl,
                        @RequestParam(value = "image", required = false) MultipartFile image) {

        EventRequest eventRequest = new EventRequest(title, description, date, city, state, remote, eventUrl, image);
        return eventService.createEvent(eventRequest);
    }

    @GetMapping
    public List<EventResponse> getAllEvents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
       return this.eventService.getUpCommingEvents(page, size);
    }
}
