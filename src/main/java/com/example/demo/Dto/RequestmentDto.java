package com.example.demo.Dto;

import java.util.Objects;

public class RequestmentDto {
    private String requestment;

    public RequestmentDto() {
    }

    public RequestmentDto(String requestment) {
        this.requestment = requestment;
    }

    public String getRequestment() {
        return this.requestment;
    }

    public void setRequestment(String requestment) {
        this.requestment = requestment;
    }

    public RequestmentDto requestment(String requestment) {
        setRequestment(requestment);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RequestmentDto)) {
            return false;
        }
        RequestmentDto requestmentDto = (RequestmentDto) o;
        return Objects.equals(requestment, requestmentDto.requestment);
    }

    @Override
    public String toString() {
        return "{" +
                " requestment='" + getRequestment() + "'" +
                "}";
    }

}
