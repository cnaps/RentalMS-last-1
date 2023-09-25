package com.msa.rentalcard.domain.model.event;

import com.msa.rentalcard.domain.model.vo.IDName;
import com.msa.rentalcard.domain.model.vo.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PointUseCommand {
    private IDName idName;
    private long point;
}
