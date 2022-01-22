package com.example.sumservice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class ResponseModel {

    @JsonProperty("code")
    private static int code;
    @JsonProperty("description")
    private static String description;
    @JsonProperty("sum")
    private Integer sum;
    private Object response;


    public void fillResponse(Response response) {
        this.code = response.getCode();
        this.description = response.getDescription();
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ResponseModel() {
        fillResponse(Response.OK);
    }

    public ResponseModel(Response response) {
        fillResponse(response);

    }

    public ResponseModel(int sum) {
        fillResponse(Response.OK);
        this.sum = sum;
    }

    public enum Response {

        OK(0, "OK"),
        ERROR(100, "Произошла ошибка");

        private final String description;
        private final int code;

        Response(final int code, final String description) {
            this.code = code;
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public int getCode() {
            return code;
        }
    }
}
