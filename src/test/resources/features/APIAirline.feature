@Airlines
Feature: Obtener todas las aerolíneas
  Como un usuario de la API de aerolíneas
  Quiero obtener la lista de todas las aerolíneas
  Para poder verificar los detalles de las aerolíneas disponibles

  @CP1
  Scenario: Obtener todas las aerolíneas exitosamente
    Given el actor establece el endpoint GET para obtener las aerolineas
    When el actor envia una solicitud HTTP GET
    Then el codigo de respuesta HTTP deberia ser 200

  @CP02
  Scenario Outline: Crear una aerolinea exitosamente
    Given el actor establece el endpoint POST para crear una aerolinea
    When el envia una solicitud HTTP POST con el "<_id>" "<name>" "<country>" "<logo>" "<slogan>" "<head_quaters>" "<website>" "<established>"
    Then el codigo de respuesta HTTP deberia ser 200
    Examples:
      | _id | name           | country | logo     | slogan                   | head_quaters | website     | established |
      | 1   | Ramon Castilla | Lima    | lima.png | Miraflores ciudad amable | Miraflores   | flowers.com | 1857        |
      | 3   | Alfredo Parodi | Lima    | lima.png | Centro financiero        | San Isidro   | isidro.pe   | 1931        |
  @CP3
  Scenario Outline: Obtener una aerolinea mediante su ID
    Given el actor establece el endpoint GET para que se obtener la aerolinea por ID
    When el envia una solicitud HTTP GET con el id "<_id>"
    Then el codigo de respuesta HTTP deberia ser 200
    Examples:
      | _id                                    |
      | 73dd5420-3bf9-48f3-a0b6-17cf7aa61b19   |
  @CP4
  Scenario Outline: Actualizar y verificar a un pasajero mediante su ID
    Given el actor establece el endpoint GET para Gestionar a los pasajeros
    When el envia una solicitud HTTP GET para obtener el nombre del pasajero por ID "<_id>"
    Then el codigo de respuesta HTTP deberia ser 200
    When el envia una solicitud HTTP PATCH con el ID "<_id>" para cambiar el nombre "<name>"
    Then el codigo de respuesta HTTP deberia ser 200
    When el envia una solicitud HTTP GET para obtener el nombre del pasajero por ID "<_id>"
    Examples:
    | _id                    | name           |
    |667ab79e7ad8fb6bef4343d4| LUIS Carlos    |

