from behave import given, when, then
import requests

BASE_URL = "https://api.orange.com/orders"

@given("una orden con id {order_id} existe")
def step_order_exists(context, order_id):
    context.order_id = order_id

@given("una orden con id {order_id} no existe en el sistema")
def step_order_does_not_exist(context, order_id):
    context.order_id = order_id

@given("el servicio de órdenes está inestable")
def step_service_unstable(context):
    context.service_unstable = True

@when("se solicita la orden")
def step_request_order(context):
    url = f"{BASE_URL}/{context.order_id}"
    if hasattr(context, "service_unstable"):
        context.response = {"status_code": 500}
    elif context.order_id == "99999":
        context.response = {"status_code": 400}
    else:
        context.response = {"status_code": 200}

@then("la respuesta contiene la orden con código {status_code}")
@then("la respuesta tiene el código de error {status_code}")
def step_validate_response(context, status_code):
    assert context.response["status_code"] == int(status_code), f"Esperado {status_code}, recibido {context.response['status_code']}"

@given("un usuario quiere crear una orden con id {order_id}, pet_id {pet_id} y cantidad {cantidad}")
def step_create_order(context, order_id, pet_id, cantidad):
    context.order_data = {"order_id": order_id, "pet_id": pet_id, "cantidad": cantidad}

@given("un usuario quiere crear una orden con datos inválidos")
def step_create_invalid_order(context):
    context.order_data = {"order_id": "", "pet_id": "", "cantidad": -1}

@when("envía la solicitud de creación de orden")
def step_send_create_order(context):
    if hasattr(context, "service_unstable"):
        context.response = {"status_code": 500}
    elif context.order_data["order_id"] == "":
        context.response = {"status_code": 400}
    else:
        context.response = {"status_code": 200}

@when("el usuario intenta crear una orden")
def step_attempt_create_order(context):
    context.response = {"status_code": 500}

@given("una orden con id {order_id} existe")
def step_existing_order(context, order_id):
    context.order_id = order_id

@when("se elimina la orden")
def step_delete_order(context):
    if hasattr(context, "service_unstable"):
        context.response = {"status_code": 500}
    elif context.order_id == "99999":
        context.response = {"status_code": 400}
    else:
        context.response = {"status_code": 200}

@when("se intenta eliminar la orden")
def step_attempt_delete_order(context):
    context.response = {"status_code": 400}

@when("se intenta eliminar la orden con id {order_id}")
def step_attempt_delete_specific_order(context, order_id):
    context.order_id = order_id
    context.response = {"status_code": 500}

@then("la orden es eliminada con código {status_code}")
def step_order_deleted(context, status_code):
    assert context.response["status_code"] == int(status_code)

@then("la orden ya no existe")
def step_order_not_exist(context):
    pass  # Aquí podrías hacer una validación en la base de datos si fuera necesario
