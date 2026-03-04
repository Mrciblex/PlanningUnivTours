package com.univtime.informatique.helpers;

import java.time.LocalDateTime;

public record MomentBanalise(
        String nom,
        LocalDateTime debut,
        LocalDateTime fin
) {
}
