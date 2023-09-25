package com.msa.rentalcard.application.inputport;

import com.msa.rentalcard.application.outputport.EventOutputPort;
import com.msa.rentalcard.domain.model.event.ItemRented;
import com.msa.rentalcard.domain.model.event.ItemReturned;
import com.msa.rentalcard.framework.web.dto.RentalCardOutputDTO;
import com.msa.rentalcard.application.usecase.ReturnItemUsercase;
import com.msa.rentalcard.domain.model.RentalCard;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msa.rentalcard.application.outputport.RentalCardOuputPort;
import com.msa.rentalcard.domain.model.vo.Item;
import com.msa.rentalcard.framework.web.dto.UserItemInputDTO;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class ReturnItemInputPort implements ReturnItemUsercase {

    private final RentalCardOuputPort rentalCardOuputPort;
    private final EventOutputPort eventOutputPort;

    @Override
    public RentalCardOutputDTO returnItem(UserItemInputDTO returnDto) throws Exception {
        // OutputPort를 사용해서 rental를 검색한 후 
        // 없으면 에러
        // 있으면 도서 반납
        // OutputPort에 저장 
        Optional<RentalCard> rental = rentalCardOuputPort.loadRentalCard(returnDto.getUserId());
        
        if (rental == null) new IllegalArgumentException("해당 카드가 존재하지 않습니다.");

        Item returnItem = new Item(returnDto.getItemId(), returnDto.getItemTitle());
        rental = Optional.ofNullable(rental.get().returnItem(returnItem, LocalDate.now()));

        //rental = Optional.ofNullable(rentalCardOuputPort.save(rental.get()));

        eventOutputPort.occurRetunEvent(RentalCard.createItemReturnEvent(rental.get().getMember(),returnItem,10L));

        return RentalCardOutputDTO.mapToDTO(rental.get());
      }


}
