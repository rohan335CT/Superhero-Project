package com.cleartax.superhero.services;

import com.cleartax.superhero.configs.SqsConfig;
import com.cleartax.superhero.dto.Superhero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.util.List;

@Service
public class SuperheroConsumer {

  @Autowired
  private SqsConfig sqsConfig;

  @Autowired
  private SqsClient sqsClient;

  @Autowired
  private SuperheroService superheroService;

  @Scheduled(fixedRate = 5000)
  public void consumeSuperhero() {

    System.out.println("running");

    ReceiveMessageResponse receivedMessage = sqsClient.receiveMessage(ReceiveMessageRequest.builder()
        .queueUrl(sqsConfig.getQueueUrl())
        .build());

    List<Message> li = receivedMessage.messages();

    for(Message m: li){
      String str = m.body();
      String[] parts = str.split(",");
      String id = parts[0];
      String name = parts[1];


      Superhero superhero = superheroService.getSuperheroById(id);
      superhero.setName(name);
      superheroService.updateSuperhero(id, superhero);

//      DeleteMessageResponse deletedMessage = sqsClient.deleteMessage(DeleteMessageRequest.builder()
//              .queueUrl(sqsConfig.getQueueUrl())
//              .receiptHandle(receivedMessage.messages().get(0).receiptHandle())
//              .build());

      DeleteMessageResponse deletedMessage = sqsClient.deleteMessage(DeleteMessageRequest.builder()
              .queueUrl(sqsConfig.getQueueUrl())
              .receiptHandle(m.receiptHandle())
              .build());

      System.out.println("deleted message response "+ deletedMessage.toString());
    }




    //return receivedMessage.messages().get(0).body();
  }


}
