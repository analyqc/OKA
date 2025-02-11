import requests
from behave import given, when, then

BASE_URL = "https://petstore.swagger.io/v2/store/order"

@given(u'una orden con id {order_id} existe')
def step_impl(context, order_id):
    context.order_id = order_id
    order_data = {
        "id": int(order_id),
        "petId": 500,
        "quantity": 2,
        "status": "placed",
        "complete": True
    }
    response = requests.post(BASE_URL, json=order_data)
    assert response.status_code == 200, f"Error al crear orden: {response.text}"

@when(u'se solicita la orden')
@when(u'se solicita la orden con id {order_id}')
def step_impl(context, order_id=None):
    order_id = order_id or context.order_id
    context.response = requests.get(f"{BASE_URL}/{order_id}")

@then(u'la respuesta contiene la orden con código 200')
def step_impl(context):
    assert context.response.status_code == 200, f"Esperado 200, pero se obtuvo {context.response.status_code}"

@given(u'una orden con id {order_id} no existe en el sistema')
def step_impl(context, order_id):
    context.order_id = order_id

@then(u'la respuesta tiene el código de error 404')
def step_impl(context):
    assert context.response.status_code == 404, f"Esperado 404, pero se obtuvo {context.response.status_code}"

@given(u'el servicio de órdenes está inestable')
def step_impl(context):
    context.base_url = "https://invalid-url.com"

@then(u'la respuesta tiene el código de error 500')
def step_impl(context):
    assert context.response.status_code == 500, f"Esperado 500, pero se obtuvo {context.response.status_code}"

@given(u'un usuario quiere crear una orden con id {order_id}, pet_id {pet_id}, y cantidad {cantidad}')
def step_impl(context, order_id, pet_id, cantidad):
    context.order_data = {
        "id": int(order_id),
        "petId": int(pet_id),
        "quantity": int(cantidad),
        "status": "placed",
        "complete": True
    }

@when(u'envía la solicitud de creación de orden')
def step_impl(context):
    context.response = requests.post(BASE_URL, json=context.order_data)

@then(u'la orden es creada con código de respuesta 200')
def step_impl(context):
    assert context.response.status_code == 200, f"Esperado 200, pero se obtuvo {context.response.status_code}"

@given(u'un usuario quiere crear una orden con datos inválidos')
def step_impl(context):
    context.order_data = {
        "id": "invalid",  
        "petId": "none",  
        "quantity": "wrong"  
    }

@when(u'el usuario intenta crear una orden')
def step_impl(context):
    context.response = requests.post(BASE_URL, json=context.order_data)

@then(u'la respuesta tiene el código de error 400')
def step_impl(context):
    assert context.response.status_code == 400, f"Esperado 400, pero se obtuvo {context.response.status_code}"

@when(u'se elimina la orden')
@when(u'se intenta eliminar la orden con id {order_id}')
def step_impl(context, order_id=None):
    order_id = order_id or context.order_id
    context.response = requests.delete(f"{BASE_URL}/{order_id}")

@then(u'la orden es eliminada con código 200')
def step_impl(context):
    assert context.response.status_code == 200, f"Esperado 200, pero se obtuvo {context.response.status_code}"

@then(u'la orden ya no existe')
def step_impl(context):
    response = requests.get(f"{BASE_URL}/{context.order_id}")
    assert response.status_code == 404, f"Esperado 404, pero se obtuvo {response.status_code}"
