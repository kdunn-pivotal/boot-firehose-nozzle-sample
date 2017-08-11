package io.pivotal.cf.nozzle;

import java.util.UUID;

import org.cloudfoundry.doppler.Envelope;
import org.cloudfoundry.doppler.FirehoseRequest;
import org.cloudfoundry.doppler.DopplerClient;

import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import reactor.core.publisher.Flux;

public class NozzleMessageSource extends MessageProducerSupport {

    private final DopplerClient dopplerClient;

    private final String applicationName;

    protected NozzleMessageSource(
            String applicationName,
            DopplerClient dopplerClient,
            MessageChannel out) {
        super();
        this.applicationName = applicationName;
        this.dopplerClient = dopplerClient;
        setOutputChannel(out);
    }

    @Override
    protected void doStart() {

        Flux<Envelope> nozzleEvents = this.dopplerClient.firehose(
                FirehoseRequest
                        .builder()
                        .subscriptionId(UUID.randomUUID().toString()).build());

        nozzleEvents.subscribe(event -> sendMessage(MessageBuilder.withPayload(event.toString()).build()));
    }

}
