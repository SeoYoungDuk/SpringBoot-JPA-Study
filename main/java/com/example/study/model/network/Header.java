package com.example.study.model.network;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@JsonInclude() 예외 주기
public class Header<T> {

//    @JsonProperty("transaction_time")//너무 하나하나씩 하기 힘들다 그래서 resource application properties
    //api 통신시간
    private LocalDateTime transactionTime;     // ISO, YYYY-MM-DD HH:mm:ss

    //api 응답코드
    private String resultCode;

    //api 부가설명
    private String description;

    private T data;

    //OK
    public static <T>Header<T> OK(){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("Ok")
                .build();
    }

    //DATA OK
    public static <T>Header<T> OK(T data){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("Ok")
                .data(data)
                .build();
    }

    //ERROR
    public static <T>Header<T> ERROR(String description){
        return (Header<T>) Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("ERROR")
                .description(description)
                .build();
    }
}
