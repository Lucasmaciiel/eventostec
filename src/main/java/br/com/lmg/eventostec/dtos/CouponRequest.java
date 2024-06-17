package br.com.lmg.eventostec.dtos;

import java.time.LocalDate;

public record CouponRequest(String code, Integer discount, LocalDate valid) {
}
