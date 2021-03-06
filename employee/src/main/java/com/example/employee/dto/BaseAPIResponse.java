package com.example.employee.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseAPIResponse {

    /*
     * A unique ID generated by the service layer for tracking/debugging a request processing.
     */
    private String requestId;

    /*
     * An identifier of the source system (WEB, MOBILE, etc) who is making the API call.
     */
    private String sourceId;

    /*
     * Overall status of the request processing. SUCCESS of FAILURE.
     */
    private String status;

    /*
     * An error code in case of a failure.
     */
    private String errorCode;

    /*
     * A user friendly error message in case of a failure.
     */
    private String errorMessage;

    /*
     * An indicator of the total number of matches found for the request.
     */
    private Integer numFound;

    private Long id;

}
