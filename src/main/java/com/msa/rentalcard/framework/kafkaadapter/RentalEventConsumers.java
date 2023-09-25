package com.msa.rentalcard.framework.kafkaadapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msa.rentalcard.application.usecase.CompensationUsecase;
import com.msa.rentalcard.domain.model.event.EventResult;
import com.msa.rentalcard.domain.model.event.EventType;
import com.msa.rentalcard.domain.model.vo.IDName;
import com.msa.rentalcard.domain.model.vo.Item;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class RentalEventConsumers {
    private final Logger log = LoggerFactory.getLogger(RentalEventConsumers.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CompensationUsecase compensationUsecase;
    @KafkaListener(topics="${consumer.topic1.name}",groupId = "${consumer.groupid.name}")
    public void consumeRental(ConsumerRecord<String, String> record) throws IOException {

       try{
            System.out.printf("rental_rent:" + record.value());

            EventResult eventResult = objectMapper.readValue(record.value(), EventResult.class);
            IDName idName = eventResult.getIdName();
            Item item = eventResult.getItem();
            long point = eventResult.getPoint();

            if (eventResult.getEventType() == EventType.RENT && !eventResult.isSuccessed())
            {
               //보상 처리 - 대여취소
               compensationUsecase.cancleRentItem(idName,item);
               //포인트 보상처리
            } else if  (eventResult.getEventType() == EventType.RETURN && !eventResult.isSuccessed())
            {
               //보상 처리 - 반납취소
               compensationUsecase.cancleReturnItem(idName,item,point);
               //포인트 보상처리
            } else if (eventResult.getEventType() == EventType.OVERDUE && !eventResult.isSuccessed())
            {
               //보상 처리 - 정치해제취소
               compensationUsecase.cancleMakeAvailableRental(idName,point);
               //
            } else
            {

            }

        } catch (IllegalArgumentException e) {

        } catch (Exception e) {

        }
    }

}
