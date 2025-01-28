package com.cleartax.superhero.controllers;

import com.cleartax.superhero.configs.SqsClientConfig;
import com.cleartax.superhero.configs.SqsConfig;
import com.cleartax.superhero.dto.Superhero;
import com.cleartax.superhero.dto.SuperheroRequestBody;
import com.cleartax.superhero.services.SuperheroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import java.util.List;

@RestController
public class SuperheroController {

    private SuperheroService superheroService;

    @Autowired
    private SqsConfig sqsConfig;

    @Autowired
    private SqsClient sqsClient;

    @Autowired
    public SuperheroController(SuperheroService superheroService, SqsClient sqsClient){
        this.superheroService = superheroService;
        this.sqsClient = sqsClient;
    }

    @GetMapping("/hello")
    public String hello() {
//        sqsClient.sendMessage(SendMessageRequest.builder()
//                .queueUrl(sqsConfig.getQueueUrl())
//                .messageBody("Rohan").build());
        ReceiveMessageResponse res = sqsClient.receiveMessage(ReceiveMessageRequest.builder().
                queueUrl(sqsConfig.getQueueUrl())
                .build());

        return sqsConfig.getQueueName() + " " + res.messages().get(0);
    }

    @GetMapping("/superhero")
    public Superhero getSuperhero(@RequestParam(value = "name", defaultValue = "Batman") String name,
                                  @RequestParam(value = "universe", defaultValue = "DC") String universe){
        return superheroService.getSuperhero(name, universe);
    }

    @PostMapping("/superhero")
    public Superhero persistSuperhero(@RequestBody SuperheroRequestBody superhero){
        return superheroService.persistSuperhero(superhero);
    }

    @PutMapping("/{id}")
    public Superhero updateSuperhero(@PathVariable String id, @RequestBody Superhero superhero) {
        return superheroService.updateSuperhero(id, superhero);
    }

    @DeleteMapping("/{id}")
    public void deleteSuperhero(@PathVariable String id){
        superheroService.deleteSuperhero(id);
    }

    @GetMapping("/{id}")
    public Superhero getSuperheroById(@PathVariable String id){
        return superheroService.getSuperheroById(id);
    }

    @GetMapping("/getAll")
    public List<Superhero> getAllSuperhero(){
        return superheroService.getAllSuperheroes();
    }

}
