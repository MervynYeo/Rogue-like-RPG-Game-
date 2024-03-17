package com.fit3077.covidtesting.model.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TestAdditionalInfo {
    private Boolean isNeedRATKit;
    private Boolean isRATKitReceived;
}
