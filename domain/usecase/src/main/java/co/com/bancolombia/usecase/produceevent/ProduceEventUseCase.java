package co.com.bancolombia.usecase.produceevent;

import co.com.bancolombia.model.placetohide.PlaceToHide;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ProduceEventUseCase {


    private int floor;

    private String room;

    public Mono<String> consumeEvent(PlaceToHide placeToHide){
        if (placeToHide.getRoom().equals("kitchen") && placeToHide.getFloor() == 1){
            return Mono.just("ME ENCONTRARON");

        }
        else {
            return Mono.just("NO ME ENCONTRARON");
        }
    }
}
