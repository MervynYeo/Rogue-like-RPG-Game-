package com.fit3077.covidtesting.app.testsite;

import com.fit3077.covidtesting.app.common.Action;
import com.fit3077.covidtesting.app.common.DependencyContainer;
import com.fit3077.covidtesting.model.testsite.TestSite;
import com.fit3077.covidtesting.controller.testsite.TestSiteController;
import com.fit3077.covidtesting.app.testsite.search.SearchType;
import com.fit3077.covidtesting.app.testsite.search.Search;
import com.fit3077.covidtesting.app.testsite.search.SearchByFacility;
import com.fit3077.covidtesting.app.testsite.search.SearchBySuburb;
import com.fit3077.covidtesting.model.user.User;

import java.util.ArrayList;

public class ViewTestSiteAction extends Action {

    private TestSiteController testSiteController;

    public ViewTestSiteAction(DependencyContainer dependencyContainer) {
        this.testSiteController = dependencyContainer.getTestSiteController();
    }

    public void execute(User user)throws Exception{
        this.testSiteController.viewTestSite();
    }

    private ArrayList<TestSite> search(SearchType searchType){
        Search search=null;
        if (searchType==SearchType.SUBURB){
            search=new SearchBySuburb();
        } else if (searchType==SearchType.FACILITYTYPE) {
            search=new SearchByFacility();
        }
        return search.execute();

    }
    public String displayChar(){
        return "v";
    }
    public String toString(){
        return "View test site";
    }
}
