package br.com.lmg.eventostec.controllers;

import br.com.lmg.eventostec.domain.Coupon;
import br.com.lmg.eventostec.dtos.CouponRequest;
import br.com.lmg.eventostec.services.CouponService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/coupons")
public class CouponController {

    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping("/events/{eventId}")
    public ResponseEntity<Coupon> addCouponsToEvent(@PathVariable UUID eventId, @RequestBody CouponRequest couponRequest) {
        Coupon coupon = couponService.addCouponToEvent(eventId, couponRequest);
        return ResponseEntity.ok(coupon);
    }
}
