package com.fit3077.covidtesting.model.test;

import lombok.Data;

@Data
public class TestRequest {
    private TestType type;
    private String patientId;
    private String administererId;
    private String bookingId;
    private ResultType result;
    private String status;
    private String notes;
    private TestAdditionalInfo additionalInfo;
}
