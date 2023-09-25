package com.msa.rentalcard.framework.jpaadapter;

import com.msa.rentalcard.application.outputport.RentalCardOuputPort;
import com.msa.rentalcard.domain.model.RentalCard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RentalCardJpaAdapter implements RentalCardOuputPort {

    private final RentalCardRepository rentalCardRepository;
    @Override
    public Optional<RentalCard> loadRentalCard(String userId) {
        return Optional.of(rentalCardRepository.findByMemberId(userId).get());
    }

    @Override
    public RentalCard save(RentalCard rentalCard) {
        return rentalCardRepository.save(rentalCard);
    }
}
