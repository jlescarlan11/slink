package com.app.slink.dtos;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClickEventDTO {
    private LocalDate clickDate;
    private Long count;
}
