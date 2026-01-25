package com.xyz.JournalApp1.journal.kafka.producer;

import com.xyz.JournalApp1.journal.kafka.KafkaTopics;
import com.xyz.JournalApp1.journal.kafka.events.JournalCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class JournalEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public JournalEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishJournalCreated(JournalCreatedEvent event) {
        kafkaTemplate.send(KafkaTopics.JOURNAL_CREATED, event.getJournalId(), event);
    }
}
