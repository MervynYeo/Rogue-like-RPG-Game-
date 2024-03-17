package com.fit3077.covidtesting.model.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fit3077.covidtesting.app.common.FIT3077Api;
import com.fit3077.covidtesting.app.common.JsonUtils;
import com.fit3077.covidtesting.model.Model;

import java.util.List;

public class TestModel extends Model {
    private static final String URL_PATH = "/covid-test";

    public TestModel() {
        super.setUrlPath(URL_PATH);
    }

    public Test registerTest(TestRequest testRequest) throws Exception {
        try {
            System.out.println(JsonUtils.toJsonString(testRequest));
            String response = FIT3077Api.post(this.urlPath, JsonUtils.toJsonString(testRequest));
            Test test = JsonUtils.toObject(response, Test.class);
            return test;
        } catch (Exception e) {
            String errorMessage = "Error in TestSystem.registerTest: " + e.getMessage();
            System.out.println(errorMessage);
            throw new Exception(errorMessage);
        }
    }

    public Test getTest(String id) {
        try {
            String response = FIT3077Api.get(this.urlPath + "/" + id);
            Test test = JsonUtils.toObject(response, Test.class);
            return test;
        } catch (Exception e) {
            String errorMessage = "Error in TestSystem.getTest: " + e.getMessage();
            System.out.println(errorMessage);
            return null;
        }
    }

    public List<Test> getTests() {
        try {
            String response = FIT3077Api.get(this.urlPath);
            List<Test> tests = JsonUtils.toObjectList(response, new TypeReference<List<Test>>() { });
            return tests;
        } catch (Exception e) {
            String errorMessage = "Error in TestSystem.getTests: " + e.getMessage();
            System.out.println(errorMessage);
            return null;
        }
    }

    public Test updateTest(String id, String testUpdate) throws Exception {
        try {
            String response = FIT3077Api.patch(this.urlPath + "/" + id, testUpdate);
            Test test = JsonUtils.toObject(response, Test.class);
            return test;
        } catch (Exception e) {
            String errorMessage = "Error in TestSystem.updateTest: " + e.getMessage();
            System.out.println(errorMessage);
            throw new Exception(errorMessage);
        }
    }
}
