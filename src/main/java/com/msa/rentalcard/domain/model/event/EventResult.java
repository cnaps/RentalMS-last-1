package com.msa.rentalcard.domain.model.event;

import com.msa.rentalcard.domain.model.vo.IDName;
import com.msa.rentalcard.domain.model.vo.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class EventResult {
    private EventType eventType;
    private boolean isSuccessed;
    private IDName idName;
    private Item item;
    private long point;
}
