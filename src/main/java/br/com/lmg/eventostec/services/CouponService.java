package br.com.lmg.eventostec.services;

import br.com.lmg.eventostec.domain.Coupon;
import br.com.lmg.eventostec.domain.Event;
import br.com.lmg.eventostec.dtos.CouponRequest;
import br.com.lmg.eventostec.repositories.CouponRepository;
import br.com.lmg.eventostec.repositories.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Service
public class CouponService {

    private final CouponRepository couponRepository;
    private final EventRepository eventRepository;

    public CouponService(CouponRepository couponRepository, EventRepository eventRepository) {
        this.couponRepository = couponRepository;
        this.eventRepository = eventRepository;
    }

    @Transactional
    public Coupon addCouponToEvent(UUID eventId, CouponRequest couponRequest) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found"));

        Coupon coupon = new Coupon();
        coupon.setCode(couponRequest.code());
        coupon.setDiscount(couponRequest.discount());
        coupon.setValid(Date.from(couponRequest.valid().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        coupon.setEvent(event);

        return couponRepository.save(coupon);
    }

}
