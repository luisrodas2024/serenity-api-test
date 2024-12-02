package com.nttdata.testing.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetAirlineById implements Task {
    private final String id;

    public GetAirlineById(String id) {
        this.id = id;
    }

    public static Performable withId(String id) {
        return instrumented(GetAirlineById.class, id);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/airlines/" + id)
                        .with(request -> request
                                .contentType(ContentType.JSON)
                                .log().all()
                        )
        );
    }
}