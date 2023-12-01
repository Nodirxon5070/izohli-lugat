package com.company.Izohli.lug.at.dto.responseDto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WordTypeDto {
    private Integer wordTypeId;

    private Integer typeId;
    private Integer wordId;
    private int[] wordIds;

    private TypeDto type;

    private WordDto word;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
