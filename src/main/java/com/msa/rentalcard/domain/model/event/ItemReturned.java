package com.msa.rentalcard.domain.model.event;

import com.msa.rentalcard.domain.model.vo.IDName;
import com.msa.rentalcard.domain.model.vo.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.net.IDN;


@Getter
public class ItemReturned extends ItemRented{

    public ItemReturned(IDName idName, Item item, long point) {
        super(idName, item, point);
    }
}
