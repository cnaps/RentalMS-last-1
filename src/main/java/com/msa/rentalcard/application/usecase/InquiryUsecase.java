package com.msa.rentalcard.application.usecase;

import com.msa.rentalcard.framework.web.dto.RentItemOutputDTO;
import com.msa.rentalcard.framework.web.dto.RentalCardOutputDTO;
import com.msa.rentalcard.framework.web.dto.RetrunItemOupputDTO;
import com.msa.rentalcard.framework.web.dto.UserInputDTO;

import java.util.List;
import java.util.Optional;

public interface InquiryUsecase {
    public Optional<RentalCardOutputDTO> getRentalCard(UserInputDTO userInputDTO);
    public Optional<List<RentItemOutputDTO>> getAllRentItem(UserInputDTO userInputDTO);
    public Optional<List<RetrunItemOupputDTO>> getAllReturnItem(UserInputDTO userInputDTO);

}
