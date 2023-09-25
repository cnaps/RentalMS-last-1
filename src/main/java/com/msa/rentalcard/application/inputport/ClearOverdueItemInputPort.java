package com.msa.rentalcard.application.inputport;

import com.msa.rentalcard.application.outputport.EventOutputPort;
import com.msa.rentalcard.application.outputport.RentalCardOuputPort;
import com.msa.rentalcard.application.usecase.ClearOverdueItemUsecase;
import com.msa.rentalcard.domain.model.RentalCard;
import com.msa.rentalcard.domain.model.event.OverdueCleared;
import com.msa.rentalcard.framework.web.dto.ClearOverdueInfoDTO;
import com.msa.rentalcard.framework.web.dto.RentalResultOuputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class ClearOverdueItemInputPort implements ClearOverdueItemUsecase {
    final RentalCardOuputPort rentalCardOuputPort;
    final EventOutputPort eventOutputPort;


    @Override
    public RentalResultOuputDTO clearOverdue(ClearOverdueInfoDTO clearOverdueInfoDTO) throws Exception {
        Optional<RentalCard> loadRentalCard = rentalCardOuputPort.loadRentalCard(clearOverdueInfoDTO.UserId);

        loadRentalCard.get().makeAvailableRental(clearOverdueInfoDTO.getPoint());
        RentalCard rentalCard = rentalCardOuputPort.save(loadRentalCard.get());

        eventOutputPort.occurOverdueClearEvent(new OverdueCleared(rentalCard.getMember(),null,clearOverdueInfoDTO.getPoint()));

        return RentalResultOuputDTO.mapToDTO(rentalCard);
    }
}
