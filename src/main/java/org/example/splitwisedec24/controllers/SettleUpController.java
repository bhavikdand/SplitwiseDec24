package org.example.splitwisedec24.controllers;

import org.example.splitwisedec24.dtos.ResponseStatus;
import org.example.splitwisedec24.dtos.SettleUpRequestDto;
import org.example.splitwisedec24.dtos.SettleUpResponseDto;
import org.example.splitwisedec24.models.Transaction;
import org.example.splitwisedec24.services.SettleUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SettleUpController {

    private SettleUpService settleUpService;

    @Autowired
    public SettleUpController(SettleUpService settleUpService) {
        this.settleUpService = settleUpService;
    }

    public SettleUpResponseDto settleGroup(SettleUpRequestDto requestDto){
        SettleUpResponseDto responseDto = new SettleUpResponseDto();
        List<Transaction> transactions = settleUpService.settleGroup(requestDto.getGroupId());
        responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        responseDto.setTransactions(transactions);
        return responseDto;

    }

    //Implement settleUser takes in the user Id
    //In service: Figure out all the non group expenses of that user -> List<Expenses>
    // Then the condense Expenses logic and the settleUp Strategy will be exactly same.

}
