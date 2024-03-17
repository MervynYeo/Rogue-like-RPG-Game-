package com.fit3077.covidtesting.model.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingRequest {
    private String customerId;
    private String testingSiteId;
    private String startTime;
    private String notes;
    private BookingAdditionalInfo additionalInfo;
}
