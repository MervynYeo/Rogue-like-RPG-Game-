package com.fit3077.covidtesting.app.testsite.search;

import com.fit3077.covidtesting.model.testsite.TestSite;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchBySuburb implements Search{

    public ArrayList<TestSite> execute(){
        ArrayList<TestSite> testSites=new ArrayList<TestSite>();
        List<TestSite>allTestSite=getTestSites();
        System.out.println("Please enter suburb:");
        Scanner scanner=new Scanner(System.in);
        String suburb=scanner.next();
        for(TestSite testSite:allTestSite){
            String currentSuburb=testSite.getAddress().getSuburb();
            if(currentSuburb.equalsIgnoreCase(suburb)){
                testSites.add(testSite);
            }
        }
        return testSites;
    }
}
