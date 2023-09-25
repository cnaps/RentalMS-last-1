package com.msa.rentalcard.application.outputport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.msa.rentalcard.domain.model.event.ItemRented;
import com.msa.rentalcard.domain.model.event.ItemReturned;
import com.msa.rentalcard.domain.model.event.OverdueCleared;
import com.msa.rentalcard.domain.model.event.PointUseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public interface EventOutputPort {
    public void occurRentalEvent(ItemRented itemRented) throws JsonProcessingException;
    public void occurRetunEvent(ItemReturned itemReturned) throws  JsonProcessingException;
    public void occurOverdueClearEvent(OverdueCleared overdueCleared) throws  JsonProcessingException;
    public void occurPointUseCommand(PointUseCommand pointUseCommand) throws JsonProcessingException;
}
