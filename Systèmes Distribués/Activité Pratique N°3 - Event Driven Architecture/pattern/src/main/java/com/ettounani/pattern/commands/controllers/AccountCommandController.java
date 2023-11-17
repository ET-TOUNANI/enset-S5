package com.ettounani.pattern.commands.controllers;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ettounani.pattern.common.commands.CreateAccountCommand;
import com.ettounani.pattern.common.dtos.CreateAccountRequestDTO;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/commands/account")
@AllArgsConstructor
public class AccountCommandController {
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping("/create")
    public CompletableFuture<String> createAccount(@RequestBody CreateAccountRequestDTO request) {
        CompletableFuture<String> response = commandGateway.send(
                new CreateAccountCommand(UUID.randomUUID().toString(), request.getInitialBalance(),
                        request.getCurrency()));
        return response;
    }

}
