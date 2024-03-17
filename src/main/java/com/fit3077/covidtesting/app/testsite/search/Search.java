package com.fit3077.covidtesting.app.testsite.search;

import com.fit3077.covidtesting.model.testsite.TestSite;
import com.fit3077.covidtesting.model.testsite.TestSiteModel;

import java.util.ArrayList;
import java.util.List;

public interface Search {

    ArrayList<TestSite> execute();
    default List<TestSite> getTestSites(){
        TestSiteModel testSiteModel =new TestSiteModel();
        return testSiteModel.getTestSites();
    };
}
