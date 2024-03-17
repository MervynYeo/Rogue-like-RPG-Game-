package com.fit3077.covidtesting.controller.testsite;

import com.fit3077.covidtesting.model.testsite.TestSite;
import com.fit3077.covidtesting.model.testsite.TestSiteModel;
import com.fit3077.covidtesting.view.testsite.TestSiteView;
import com.fit3077.covidtesting.app.testsite.search.Search;
import com.fit3077.covidtesting.app.testsite.search.SearchByFacility;
import com.fit3077.covidtesting.app.testsite.search.SearchBySuburb;
import com.fit3077.covidtesting.app.testsite.search.SearchType;

import java.util.List;

public class TestSiteController {

    private TestSiteView testSiteView;

    private TestSiteModel testSiteModel;

    public TestSiteController(TestSiteView testSiteView, TestSiteModel testSiteModel) {
        this.testSiteView = testSiteView;
        this.testSiteModel = testSiteModel;
    }

    public void viewTestSite() {
        SearchType searchType = this.testSiteView.getSearchType();
        List<TestSite> testSites = search(searchType);
        this.testSiteView.displayTestSites(testSites);
    }

    private List<TestSite> search(SearchType searchType){
        Search search=null;
        if (searchType==SearchType.SUBURB){
            search=new SearchBySuburb();
        } else if (searchType==SearchType.FACILITYTYPE) {
            search=new SearchByFacility();
        }
        return search.execute();

    }

}
