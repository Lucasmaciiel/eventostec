package br.com.lmg.eventostec.repositories;

import br.com.lmg.eventostec.domain.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

    @Query("SELECT e FROM Event e WHERE e.date >= :currentDate")
    Page<Event> findUpCommingEvents(@Param("currentDate") Date currentDate, Pageable pageable);
}
