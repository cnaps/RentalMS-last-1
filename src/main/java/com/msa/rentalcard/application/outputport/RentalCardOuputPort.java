package com.msa.rentalcard.application.outputport;

import com.msa.rentalcard.domain.model.RentalCard;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentalCardOuputPort {
    Optional<RentalCard> loadRentalCard(String userId);
    RentalCard save(RentalCard rentalCard);
}
