package com.bcreagh.mpspark.mp.domain.requestdetails;

public class RequestDetails {
    private HttpMethods httpMethod = HttpMethods.POST;
    private InputTypes inputType = InputTypes.JSON;
    private String inputTemplate = "";

    public HttpMethods getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethods httpMethod) {
        this.httpMethod = httpMethod;
    }

    public InputTypes getInputType() {
        return inputType;
    }

    public void setInputType(InputTypes inputType) {
        this.inputType = inputType;
    }

    public String getInputTemplate() {
        return inputTemplate;
    }

    public void setInputTemplate(String inputTemplate) {
        this.inputTemplate = inputTemplate;
    }
}
