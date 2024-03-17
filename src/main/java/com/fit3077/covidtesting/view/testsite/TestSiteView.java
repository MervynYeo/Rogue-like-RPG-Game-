package com.fit3077.covidtesting.view.testsite;

import com.fit3077.covidtesting.model.testsite.TestSite;
import com.fit3077.covidtesting.model.testsite.TestSiteModel;
import com.fit3077.covidtesting.app.testsite.search.SearchType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestSiteView {
    private TestSiteModel testSiteModel;

    private Scanner scanner = new Scanner(System.in);

    public TestSiteView(TestSiteModel testSiteModel) {
        this.testSiteModel = testSiteModel;
    }

    public SearchType getSearchType() {
        ArrayList<SearchType> searchTypes=new ArrayList<>();
        for (SearchType st:SearchType.values()){
            searchTypes.add(st);
        }
        for(int i=0;i<searchTypes.size();i++){
            System.out.println("press "+i+" to search by "+searchTypes.get(i));
        }
        System.out.println("Please select a search type");
        int index=scanner.nextInt();
        return searchTypes.get(index);
    }

    public void displayTestSites(List<TestSite> testSites) {
        for (TestSite testSite:testSites){
            System.out.println(testSite);
        }
    }

//    public viewTest


}
