package com.msa.rentalcard.application.usecase;

import com.msa.rentalcard.domain.model.RentalCard;
import com.msa.rentalcard.domain.model.vo.IDName;
import com.msa.rentalcard.domain.model.vo.Item;

public interface CompensationUsecase {

    public RentalCard cancleRentItem(IDName idName,Item item);
    public RentalCard cancleReturnItem(IDName idName,Item item, long point) throws Exception;
    public long cancleMakeAvailableRental(IDName idName,long point);
}
