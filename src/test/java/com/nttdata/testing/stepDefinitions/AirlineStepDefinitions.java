package com.nttdata.testing.stepDefinitions;

import com.nttdata.testing.questions.ResponseCode;
import com.nttdata.testing.tasks.*;
import io.cucumber.java.Before;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.equalTo;

public class AirlineStepDefinitions {
    public static Logger LOGGER = LoggerFactory.getLogger(AirlineStepDefinitions.class);

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }


    @Given("el {actor} establece el endpoint GET para obtener las aerolineas")
    public void elActorEstableceElEndpointGETParaObtenerLasAerolineas(Actor actor) {
        actor.whoCan(CallAnApi.at("https://api.instantwebtools.net/v1"));
    }

    @When("el {actor} envia una solicitud HTTP GET")
    public void elActorEnviaUnaSolicitudHTTPGET(Actor actor) {
        actor.attemptsTo(GetAirlines.fromEndpoint("/airlines"));

    }

    @Then("el codigo de respuesta HTTP deberia ser {int}")
    public void elCodigoDeRespuestaHTTPDeberiaSer(int responseCode) {
        theActorInTheSpotlight().should(seeThat("El código de respuesta", ResponseCode.getStatus(), equalTo(responseCode)));
    }

    @Given("el {actor} establece el endpoint POST para crear una aerolinea")
    public void elActorEstableceElEndpointPOSTParaCrearUnaAerolinea(Actor actor) {
        actor.whoCan(CallAnApi.at("https://api.instantwebtools.net/v1"));
    }

    @When("el envia una solicitud HTTP POST con el {string} {string} {string} {string} {string} {string} {string} {string}")
    public void elEnviaUnaSolicitudHTTPPOSTConEl(String _id, String name, String country, String logo, String slogan, String head_quaters, String website, String established) {
        theActorInTheSpotlight().attemptsTo(PostAirline.fromPage(_id, name, country, logo, slogan, head_quaters, website, established));
    }


    @Given("el {actor} establece el endpoint GET para que se obtener la aerolinea por ID")
    public void elActorEstableceElEndpointGETParaQueSeObtenerLaAerolineaPorID(Actor actor) {
        actor.whoCan(CallAnApi.at("https://api.instantwebtools.net/v1"));
    }

    @When("el envia una solicitud HTTP GET con el id {string}")
    public void enviarSolicitudGETConId(String id) {
        theActorInTheSpotlight().attemptsTo(
                GetAirlineById.withId(id)
        );
    }

    @Given("el {actor} establece el endpoint GET para Gestionar a los pasajeros")
    public void elActorEstableceElEndpointPATCHParaActualizarElNombreDeUnPasajero(Actor actor) {
        actor.whoCan(CallAnApi.at("https://api.instantwebtools.net/v1"));
    }
    @When("el envia una solicitud HTTP GET para obtener el nombre del pasajero por ID {string}")
    public void elEnviaUnaSolicitudHTTPGETParaObtenerElNombreDelPasajeroPorID(String id) {
            theActorInTheSpotlight().attemptsTo(GetPassengerById.withId(id));
    }

    @When("el envia una solicitud HTTP PATCH con el ID {string} para cambiar el nombre {string}")
    public void elEnviaUnaSolicitudHTTPPATCHConElIDParaCambiarElNombre(String id, String newName) {
        theActorInTheSpotlight().attemptsTo(
                PatchNamePassenger.withIdAndName(id, newName)
        );
    }


    /*
   

    @When("el envie una solicitud HTTP PATCH con el {string} y el nombre {string}")
    public void actualizarPasajeroConIdYNombre(String id, String newName) {

    }*/



}


