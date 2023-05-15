package co.com.bancolombia.events.handlers;

import co.com.bancolombia.model.placetohide.PlaceToHide;
import co.com.bancolombia.usecase.produceevent.ProduceEventUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cloudevents.CloudEvent;
import io.cloudevents.core.provider.EventFormatProvider;
import io.cloudevents.jackson.JsonFormat;
import lombok.AllArgsConstructor;
import org.reactivecommons.api.domain.DomainEvent;
import org.reactivecommons.async.impl.config.annotations.EnableEventListeners;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@AllArgsConstructor
@EnableEventListeners
public class EventsHandler {
    private final ProduceEventUseCase sampleUseCase;


    public Mono<Void> handleEventA(DomainEvent<byte[]> event) {
        CloudEvent data = EventFormatProvider
                .getInstance()
                .resolveFormat(JsonFormat.CONTENT_TYPE)
                .deserialize(event.getData());
        byte[] bytes = data.getData().toBytes();
        String stringData = new String(bytes, StandardCharsets.UTF_8);
        PlaceToHide placeToHide;
        try {
            placeToHide = new ObjectMapper().readValue(stringData, PlaceToHide.class);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(sampleUseCase.consumeEvent(placeToHide).block());
        return Mono.empty();
    }
}
