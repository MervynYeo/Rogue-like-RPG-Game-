package com.fit3077.covidtesting.app.testsite.search;

import com.fit3077.covidtesting.model.testsite.TestSite;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchByFacility implements Search{
    public ArrayList<TestSite> execute(){
        ArrayList<TestSite> testSites=new ArrayList<TestSite>();
        List<TestSite> allTestSite=getTestSites();
        ArrayList<FacilityType> facilityTypes=getAvailableFacilityType();

        Scanner scanner=new Scanner(System.in);
        Integer input=scanner.nextInt();
        FacilityType wantedFacilityType=facilityTypes.get(input);
        for(TestSite testSite:allTestSite){
            String facilityTypeEnum=testSite.getAdditionalInfo().getFacilityType();
            if(facilityTypeEnum.equalsIgnoreCase(wantedFacilityType.toString())){
                testSites.add(testSite);
            }
        }
        return testSites;
    }
    private ArrayList<FacilityType> getAvailableFacilityType(){
        ArrayList<FacilityType> facilityTypes=new ArrayList<FacilityType>();
        for (FacilityType facilityType:FacilityType.values()){
            System.out.println("Please press "+facilityTypes.size()+" for "+facilityType);
            facilityTypes.add(facilityType);
        }
        return facilityTypes;
    }
}
