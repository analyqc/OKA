Feature: Gestión de órdenes en la API de PetStore

  Scenario: Obtener una orden existente con código 200
    Given una orden con id 15432 existe
    When se solicita la orden
    Then la respuesta contiene la orden con código 200

  Scenario: Obtener una orden inexistente con código 404
    Given una orden con id 99999 no existe en el sistema
    When se solicita la orden
    Then la respuesta tiene el código de error 404

  Scenario: Obtener una orden cuando el servicio está inestable con código 500
    Given el servicio de órdenes está inestable
    When se solicita la orden con id 15432
    Then la respuesta tiene el código de error 500

  Scenario: Crear una orden con datos válidos con código 200
    Given un usuario quiere crear una orden con id 10001, pet_id 500, y cantidad 2
    When envía la solicitud de creación de orden
    Then la orden es creada con código de respuesta 200

  Scenario: Intentar crear una orden con datos inválidos con código 400
    Given un usuario quiere crear una orden con datos inválidos
    When el usuario intenta crear una orden
    Then la respuesta tiene el código de error 400

  Scenario: Eliminar una orden existente con código 200
    Given una orden con id 15432 existe
    When se elimina la orden
    Then la orden es eliminada con código 200
    And la orden ya no existe

  Scenario: Intentar eliminar una orden inexistente con código 404
    Given una orden con id 99999 no existe en el sistema
    When se intenta eliminar la orden
    Then la respuesta tiene el código de error 404

  Scenario: Crear una orden y verificar que se puede obtener con código 200
    Given un usuario quiere crear una orden con id 20001, pet_id 501, y cantidad 1
    When envía la solicitud de creación de orden
    Then la orden es creada con código de respuesta 200
    When se solicita la orden con id 20001
    Then la respuesta contiene la orden con código 200
