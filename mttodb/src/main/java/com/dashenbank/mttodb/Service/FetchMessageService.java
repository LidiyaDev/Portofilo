package com.dashenbank.mttodb.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.dashenbank.mttodb.DTO.MessageResponse;

@Service
public class FetchMessageService {
     WebClient webclient;



     public FetchMessageService(WebClient.Builder webClientBuilder) {
        this.webclient = webClientBuilder.baseUrl("http://192.168.12.##:####").build();
    }

    public List<MessageResponse> getMessageInfo() {

        String GET_EMPLOYEE_BY_ID = "http://192.168.12.##:###/getall/";

        System.out.println(GET_EMPLOYEE_BY_ID);

        List<MessageResponse> listAccount = webclient.get().uri(GET_EMPLOYEE_BY_ID)
        .retrieve()
        .bodyToFlux(MessageResponse.class)
                .collectList()
                .block();

                return listAccount;



    }
    public MessageResponse getMessageInfo(String referenceNumber) {

        webclient = WebClient.builder()

                .build();

                MessageResponse model = webclient.get()

                .uri("http://192.168.12.##:####/getByRefference/" + referenceNumber)

                .retrieve()

                .bodyToMono(MessageResponse.class).block();

        return model;



    }
}
