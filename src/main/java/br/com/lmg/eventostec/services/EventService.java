package br.com.lmg.eventostec.services;

import br.com.lmg.eventostec.domain.Event;
import br.com.lmg.eventostec.dtos.EventRequest;
import br.com.lmg.eventostec.dtos.EventResponse;
import br.com.lmg.eventostec.repositories.EventRepository;
import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class EventService {

    @Value("${aws.bucket.name}")
    private String awsBucketName;

    private final EventRepository eventRepository;
    private final AmazonS3 s3Client;

    public EventService(EventRepository eventRepository, AmazonS3 s3Client) {
        this.eventRepository = eventRepository;
        this.s3Client = s3Client;
    }

    @Transactional
    public Event createEvent(EventRequest eventRequest) {
        String imgUrl = null;

        if (Objects.nonNull(eventRequest.image())) {
            imgUrl = this.uploadImage(eventRequest.image());
        }

        var event = Event.builder()
                .title(eventRequest.title())
                .description(eventRequest.description())
                .eventUrl(eventRequest.eventUrl())
                .date(eventRequest.date())
                .imageUrl(imgUrl)
                .remote(eventRequest.remote())
                .build();

        return eventRepository.save(event);
    }

    private String uploadImage(MultipartFile multipartFile) {
        String fileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        try {
            File file = this.converMultiPartToFile(multipartFile);
            s3Client.putObject(awsBucketName, fileName, file);
            file.delete();

            return s3Client.getUrl(awsBucketName, fileName).toString();
        } catch (IOException e) {
            return "";
        }

    }

    private File converMultiPartToFile(MultipartFile imageFile) throws IOException {
        File convFile = new File(Objects.requireNonNull(imageFile.getOriginalFilename()));
        FileOutputStream fileOutputStream = new FileOutputStream(convFile);
        fileOutputStream.write(imageFile.getBytes());
        fileOutputStream.close();
        return convFile;
    }

    public List<EventResponse> getUpCommingEvents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Event> events = eventRepository.findUpCommingEvents(new Date(), pageable).getContent();

        return events.stream().map(event ->
                EventResponse.builder()
                        .id(event.getId())
                        .title(event.getTitle())
                        .description(event.getDescription())
                        .city("")
                        .state("")
                        .imgUrl(event.getImageUrl())
                        .remote(event.getRemote())
                        .build()).toList();

    }
}
