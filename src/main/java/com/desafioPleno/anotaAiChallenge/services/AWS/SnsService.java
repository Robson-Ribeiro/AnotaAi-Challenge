package com.desafioPleno.anotaAiChallenge.services.AWS;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;

@Service
public class SnsService {
    private final AmazonSNS snsClient;
    private final Topic catalogTopic;

    public SnsService(AmazonSNS snsClient, @Qualifier("catalogEventsTopic") Topic catalogTopic) {
        this.snsClient = snsClient;
        this.catalogTopic = catalogTopic;
    }

    public void publish(String message) {
        this.snsClient.publish(catalogTopic.getTopicArn(), message);
    }
}
