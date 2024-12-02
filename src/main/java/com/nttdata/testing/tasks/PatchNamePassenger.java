package com.nttdata.testing.tasks;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Patch;
import net.serenitybdd.screenplay.rest.interactions.Put;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PatchNamePassenger implements Task {
        private final String id;
        private final String newName;

        public PatchNamePassenger(String id, String newName) {
            this.id = id;
            this.newName = newName;
        }

        public static Performable withIdAndName(String id, String newName) {
            return instrumented(PatchNamePassenger.class, id, newName);
        }

        @Override
        public <T extends Actor> void performAs(T actor) {
            actor.attemptsTo(
                    Patch.to("/passenger/" + id)
                            .with(request -> request
                                    .contentType(ContentType.JSON)
                                    .body("{\"name\":\"" + newName + "\"}")
                                    .log().all()
                            )
            );
        }
}