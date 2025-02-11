Feature: Reserva de viajes en PeruRail

Scenario: Login  ok
    Given El usuario ingresa a la pagina de orange
    When El usuario ingresa el user "Admin" y el password "admin123"
    Then aqui se verificara los datos son correctos

Scenario: Login  fallido
    Given El usuario ingresa a la pagina de orange
    When El usuario ingresa el user "Admin1" y el password "admin123"
    Then aqui se verificara los datos son incorrectos


Scenario: El usuario utiliza el filtro para visualizar unicamente los usuarios que tienen el rol de: Admin
		Given El usuario ingresa a la pagina de orange
    When El usuario ingresa el user "Admin" y el password "admin123"
    And Buscar administradores
    Then Validar que los registros contengan actions editar y eliminar
  

    
      
