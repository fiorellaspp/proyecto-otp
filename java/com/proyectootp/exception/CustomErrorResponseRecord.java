package com.proyectootp.exception;

import java.time.LocalDateTime;

public record CustomErrorResponseRecord(
    LocalDateTime datetime,
    String message,
    String path
) {
}
