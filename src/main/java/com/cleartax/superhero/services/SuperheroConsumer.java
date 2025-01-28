package com.cleartax.superhero.services;

import com.cleartax.superhero.configs.SqsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.DeleteMessageResponse;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;

@Service
public class SuperheroConsumer {

  @Autowired
  private SqsConfig sqsConfig;

  @Autowired
  private SqsClient sqsClient;

  public String consumeSuperhero() {

    ReceiveMessageResponse receivedMessage = sqsClient.receiveMessage(ReceiveMessageRequest.builder()
        .queueUrl(sqsConfig.getQueueUrl())
        .build());

    DeleteMessageResponse deletedMessage = sqsClient.deleteMessage(DeleteMessageRequest.builder()
        .queueUrl(sqsConfig.getQueueUrl())
        .receiptHandle(receivedMessage.messages().get(0).receiptHandle())
        .build());

    System.out.println("deleted message response "+ deletedMessage.toString());
    return receivedMessage.messages().get(0).body();
  }


}
