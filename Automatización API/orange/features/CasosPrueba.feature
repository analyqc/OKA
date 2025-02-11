Feature: Gestión de órdenes en la API de Orange

  # Obtención de órdenes
  Scenario: Obtener una orden existente
    Given una orden con id 12345 existe
    When se solicita la orden
    Then la respuesta contiene la orden con código 200

  Scenario: Obtener una orden inexistente
    Given una orden con id 99999 no existe en el sistema
    When se solicita la orden
    Then la respuesta tiene el código de error 400

  Scenario: Error interno al obtener una orden
    Given el servicio de órdenes está inestable
    When se solicita la orden con id 12345
    Then la respuesta tiene el código de error 500

  # Creación de órdenes
  Scenario: Crear una nueva orden válida
    Given un usuario quiere crear una orden con id 12345, pet_id 6789 y cantidad 1
    When envía la solicitud de creación de orden
    Then la orden es creada con código de respuesta 200

  Scenario: Crear una orden con datos inválidos
    Given un usuario quiere crear una orden con datos inválidos
    When envía la solicitud de creación de orden
    Then la respuesta tiene el código de error 400

  Scenario: Error interno al crear una orden
    Given el servicio de órdenes está inestable
    When el usuario intenta crear una orden
    Then la respuesta tiene el código de error 500

  # Eliminación de órdenes
  Scenario: Eliminar una orden existente
    Given una orden con id 12345 existe
    When se elimina la orden
    Then la orden es eliminada con código 200
    And la orden ya no existe

  Scenario: Eliminar una orden inexistente
    Given una orden con id 99999 no existe en el sistema
    When se intenta eliminar la orden
    Then la respuesta tiene el código de error 400

  Scenario: Error interno al eliminar una orden
    Given el servicio de órdenes está inestable
    When se intenta eliminar la orden con id 12345
    Then la respuesta tiene el código de error 500