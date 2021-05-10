package com.belong.common;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

/**
 * <h1>APIResponse</h1> This class represents the general response structure of
 * exam service api.
 * 
 * @author jyotikattikar
 */

public class APIResponse<T> {

    private Integer status;
    private Error error;
    private T data;
    private String warning;

    public APIResponse() {
        this.status = ErrorCode.OK.value();
        this.error = new Error();
    }

    @Override
    public boolean equals(Object other) {

        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (getClass() != other.getClass()) {
            return false;
        }

        APIResponse otherApiResponse = (APIResponse) other;

        return Objects.equals(status, otherApiResponse.status) && Objects.equals(error, otherApiResponse.error)
                && Objects.equals(data, otherApiResponse.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, error, data);
    }

    @Override
    public String toString() {
        return "<APIResponse STATUS: " + getStatus() + ", ERROR: " + getError() + ", DATA: " + getData() + ">";
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public void appendWarning(String warning) {

        if (!StringUtils.isEmpty(warning)) {

            if (StringUtils.isEmpty(this.warning)) {
                this.warning = warning;
            } else {
                this.warning += "," + warning;
            }
        }
    }

}
