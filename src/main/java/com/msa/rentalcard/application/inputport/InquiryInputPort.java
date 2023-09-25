package com.msa.rentalcard.application.inputport;

import com.msa.rentalcard.application.outputport.RentalCardOuputPort;
import com.msa.rentalcard.application.usecase.InquiryUsecase;
import com.msa.rentalcard.framework.web.dto.RentItemOutputDTO;
import com.msa.rentalcard.framework.web.dto.RentalCardOutputDTO;
import com.msa.rentalcard.framework.web.dto.RetrunItemOupputDTO;
import com.msa.rentalcard.framework.web.dto.UserInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class InquiryInputPort implements InquiryUsecase {

    private final RentalCardOuputPort rentalCardOuputPort;

    @Override
    public Optional<RentalCardOutputDTO> getRentalCard(UserInputDTO userInputDTO) {
        return rentalCardOuputPort.loadRentalCard(userInputDTO.UserId)
                .map(loadCard -> RentalCardOutputDTO.mapToDTO(loadCard));
    }

    @Override
    public Optional<List<RentItemOutputDTO>> getAllRentItem(UserInputDTO userInputDTO) {
        return rentalCardOuputPort.loadRentalCard(userInputDTO.UserId)
                .map(loadCard -> loadCard.getRentItemList()
                        .stream()
                        .map(RentItemOutputDTO::mapToDTO)
                        .collect(Collectors.toList()));
    }

    @Override
    public Optional<List<RetrunItemOupputDTO>> getAllReturnItem(UserInputDTO userInputDTO) {
        return rentalCardOuputPort.loadRentalCard(userInputDTO.UserId)
                .map(loadCard -> loadCard.getReturnItemList()
                        .stream()
                        .map(RetrunItemOupputDTO::mapToDTO)
                        .collect(Collectors.toList()));

    }
}
