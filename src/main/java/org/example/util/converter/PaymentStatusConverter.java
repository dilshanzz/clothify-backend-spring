package org.example.util.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.example.util.enums.PaymentStatus;

@Converter(autoApply = true)
public class PaymentStatusConverter implements AttributeConverter<PaymentStatus,Integer> {


    @Override
    public Integer convertToDatabaseColumn(PaymentStatus paymentStatus) {
        return switch (paymentStatus){
            case COMPLETED -> 1;
            case INCOMPLETE ->2;
        };
    }

    @Override
    public PaymentStatus convertToEntityAttribute(Integer integer) {
        return switch (integer){
            case  1 ->  PaymentStatus.COMPLETED;
            case  2 ->  PaymentStatus.INCOMPLETE;
            default -> null;
        };
    }
}