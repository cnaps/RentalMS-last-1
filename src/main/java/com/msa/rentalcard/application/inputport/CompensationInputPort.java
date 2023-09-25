package com.msa.rentalcard.application.inputport;

import com.msa.rentalcard.application.outputport.EventOutputPort;
import com.msa.rentalcard.application.outputport.RentalCardOuputPort;
import com.msa.rentalcard.application.usecase.CompensationUsecase;
import com.msa.rentalcard.domain.model.RentalCard;
import com.msa.rentalcard.domain.model.event.PointUseCommand;
import com.msa.rentalcard.domain.model.vo.IDName;
import com.msa.rentalcard.domain.model.vo.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
@Transactional
public class CompensationInputPort implements CompensationUsecase {
    private final RentalCardOuputPort rentalCardOuputPort;
    private final EventOutputPort eventOutputPort;

    @Override
    public RentalCard cancleRentItem(IDName idName, Item item) {
        return rentalCardOuputPort.loadRentalCard(idName.getId())
                .map(rentalCard -> {
                    try {
                        rentalCard.cancleRentItem(item);
                        eventOutputPort.occurPointUseCommand(new PointUseCommand(idName,10L));
                        return rentalCard;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .get();
    }

    @Override
    public RentalCard cancleReturnItem(IDName idName,Item item, long point)  {
        Optional<RentalCard> rentalCard = rentalCardOuputPort.loadRentalCard(idName.getId());
        try {
            rentalCard.get().cancleReturnItem(item,point);
            eventOutputPort.occurPointUseCommand(new PointUseCommand(idName,point));
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return rentalCard.get();
    }

    @Override
    public long cancleMakeAvailableRental(IDName idName,long point) {
        return rentalCardOuputPort.loadRentalCard(idName.getId())
                .map(rentalCard -> {
                    try {
                        return rentalCard.cancleMakeAvailableRental(point);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).get();
    }
}
