Reto Técnico Backend: Microservicios de Cliente y Producto
1. Introducción
El proyecto implementa dos microservicios:

Microservicio de Cliente: Gestiona la creación y autenticación de clientes.
Microservicio de Producto: Gestiona las operaciones de productos como creación, obtención y actualización.
Ambos microservicios interactúan con una base de datos MySQL y están construidos con Spring Boot v3 y Spring WebFlux.

2. Requisitos
2.1 Requisitos Funcionales
Microservicio de Cliente:

Crear un cliente con un id único.
Obtener los detalles de un cliente por su id.
Crear y validar la autenticación de un cliente (requiere un clientId y password).
Microservicio de Producto:

Crear un producto con nombre, descripción y precio.
Obtener un producto por su id.
Obtener todos los productos disponibles.
Actualizar un producto por su id.
Ambos servicios deben poder actualizar sus respectivos datos en tiempo real y manejar errores de forma adecuada.

2.2 Herramientas
Java 17
Spring Boot v3
Spring Data JPA
Base de Datos MySQL (con base de datos interb)
Spring WebFlux (para operaciones reactivas)
Lombok (para reducir el boilerplate)
MapStruct (para mapeo de objetos)
Mockito (para pruebas unitarias)
Postman (para pruebas de los endpoints)
IDE: IntelliJ IDEA
Git (control de versiones)
3. Instalación y Ejecución
3.1 Requisitos previos
JDK 17 o superior.
MySQL (versión 5.7 o superior).
Maven o Gradle (dependiendo de tu preferencia para la gestión de dependencias).
3.Postam a usar

[Uploading Inte{
	"info": {
		"_postman_id": "f4947a13-f8b9-495b-92c0-92d5bfc24538",
		"name": "InterB",
		"schema": "https://schema.getpostman.com/json/collection/v2.0.0/collection.json",
		"_exporter_id": "22884094",
		"_collection_link": "https://speeding-sunset-656617.postman.co/workspace/BankEnd~dd2bb3ca-0dc2-443e-885a-a5a95c7fbd09/collection/22884094-f4947a13-f8b9-495b-92c0-92d5bfc24538?action=share&source=collection_link&creator=22884094"
	},
	"item": [
		{
			"name": "producto",
			"item": [
				{
					"name": "byId",
					"request": {
						"method": "GET",
						"header": [],
						"url": "http://localhost:8081/api/product/9"
					},
					"response": [
						{
							"name": "New Request",
							"originalRequest": {
								"method": "GET",
								"header": [],
								"url": "http://localhost:8081/api/product/9"
							},
							"status": "OK",
							"code": 200,
							"_postman_previewlanguage": "json",
							"header": [
								{
									"key": "Content-Type",
									"value": "application/json"
								},
								{
									"key": "Content-Length",
									"value": "150"
								}
							],
							"cookie": [],
							"body": "{\n    \"code\": \"OK\",\n    \"status\": 200,\n    \"comment\": \"Producto encontrado\",\n    \"data\": {\n        \"id\": 9,\n        \"tipoProducto\": \"Cuenta de ahorro\",\n        \"nombre\": \"Cuenta Súper Tasa\",\n        \"saldo\": 0\n    }\n}"
						}
					]
				},
				{
					"name": "productos",
					"request": {
						"method": "GET",
						"header": [],
						"url": "http://localhost:8080/api/product"
					},
					"response": [
						{
							"name": "byId Copy",
							"originalRequest": {
								"method": "GET",
								"header": [],
								"url": "http://localhost:8081/api/product"
							},
							"status": "OK",
							"code": 200,
							"_postman_previewlanguage": "json",
							"header": [
								{
									"key": "Content-Type",
									"value": "application/json"
								},
								{
									"key": "Content-Length",
									"value": "670"
								}
							],
							"cookie": [],
							"body": "{\n    \"code\": \"OK\",\n    \"status\": 200,\n    \"comment\": \"Lista de productos obtenida\",\n    \"data\": [\n        {\n            \"id\": 1,\n            \"tipoProducto\": null,\n            \"nombre\": null,\n            \"saldo\": null\n        },\n        {\n            \"id\": 2,\n            \"tipoProducto\": null,\n            \"nombre\": null,\n            \"saldo\": null\n        },\n        {\n            \"id\": 3,\n            \"tipoProducto\": null,\n            \"nombre\": null,\n            \"saldo\": null\n        },\n        {\n            \"id\": 4,\n            \"tipoProducto\": null,\n            \"nombre\": \"cuenta simple\",\n            \"saldo\": 0\n        },\n        {\n            \"id\": 5,\n            \"tipoProducto\": \"Cuenta de ahorro\",\n            \"nombre\": \"cuenta simple\",\n            \"saldo\": 0\n        },\n        {\n            \"id\": 6,\n            \"tipoProducto\": \"Cuenta de ahorro\",\n            \"nombre\": \"Cuenta Millonaria\",\n            \"saldo\": 0\n        },\n        {\n            \"id\": 7,\n            \"tipoProducto\": null,\n            \"nombre\": null,\n            \"saldo\": null\n        },\n        {\n            \"id\": 8,\n            \"tipoProducto\": null,\n            \"nombre\": null,\n            \"saldo\": null\n        },\n        {\n            \"id\": 9,\n            \"tipoProducto\": \"Cuenta de ahorro\",\n            \"nombre\": \"Cuenta Súper Tasa\",\n            \"saldo\": 0\n        }\n    ]\n}"
						}
					]
				},
				{
					"name": "create",
					"request": {
						"method": "POST",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n  \"tipoProducto\" : \"Cuenta de ahorro\",\r\n  \"nombre\" : \"cuenta simple\",\r\n  \"saldo\" : 0.0\r\n\r\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": "http://localhost:8081/api/product/create"
					},
					"response": [
						{
							"name": "http://localhost:8090/api/product/create",
							"originalRequest": {
								"method": "POST",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\r\n  \"tipoProducto\" : \"Cuenta de ahorro\",\r\n  \"nombre\" : \"Cuenta Súper Tasa\",\r\n  \"saldo\" : 0.0\r\n\r\n}",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": "http://localhost:8081/api/product/create"
							},
							"status": "OK",
							"code": 200,
							"_postman_previewlanguage": "json",
							"header": [
								{
									"key": "Content-Type",
									"value": "application/json"
								},
								{
									"key": "Content-Length",
									"value": "121"
								}
							],
							"cookie": [],
							"body": "{\n    \"code\": \"Created\",\n    \"status\": 201,\n    \"comment\": \"Producto creado exitosamente\",\n    \"data\": {\n        \"message\": \"Producto:Cuenta Súper Tasa\"\n    }\n}"
						}
					]
				},
				{
					"name": "update product",
					"request": {
						"method": "PUT",
						"header": [],
						"body": {
							"mode": "raw",
							"raw": "{\r\n   \r\n    \"tipoProducto\": \"Cuenta de ahorro\",\r\n    \"nombre\": \"Cuenta Simple\",\r\n    \"saldo\": 0.0\r\n\r\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": "http://localhost:8080/api/product/update/1"
					},
					"response": [
						{
							"name": "create Copy",
							"originalRequest": {
								"method": "PUT",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\r\n   \r\n    \"tipoProducto\": \"Cuenta de ahorro\",\r\n    \"nombre\": \"Cuenta Simple\",\r\n    \"saldo\": 0.0\r\n\r\n}",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": "http://localhost:8080/api/product/update/1"
							},
							"status": "OK",
							"code": 200,
							"_postman_previewlanguage": "json",
							"header": [
								{
									"key": "Content-Type",
									"value": "application/json"
								},
								{
									"key": "Content-Length",
									"value": "160"
								}
							],
							"cookie": [],
							"body": "{\n    \"code\": \"OK\",\n    \"status\": 200,\n    \"comment\": \"Producto actualizado correctamente\",\n    \"data\": {\n        \"id\": 1,\n        \"tipoProducto\": \"Cuenta de ahorro\",\n        \"nombre\": \"Cuenta Simple\",\n        \"saldo\": 0\n    }\n}"
						}
					]
				}
			]
		},
		{
			"name": "cliente",
			"item": [
				{
					"name": "auth",
					"item": [
						{
							"name": "crear auth",
							"request": {
								"method": "POST",
								"header": [],
								"body": {
									"mode": "raw",
									"raw": "{\r\n    \"password\": \"H12345678!\",\r\n    \"clienteId\": 0\r\n}",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": "http://localhost:8081/api/auth/create"
							},
							"response": [
								{
									"name": "New Request",
									"originalRequest": {
										"method": "POST",
										"header": [],
										"body": {
											"mode": "raw",
											"raw": "{\"user\": \"admin\",\r\n \"password\": \"admin\"}",
											"options": {
												"raw": {
													"language": "json"
												}
											}
										},
										"url": "http://localhost:8081/api/auth/login"
									},
									"status": "OK",
									"code": 200,
									"_postman_previewlanguage": "json",
									"header": [
										{
											"key": "Vary",
											"value": "Origin"
										},
										{
											"key": "Vary",
											"value": "Access-Control-Request-Method"
										},
										{
											"key": "Vary",
											"value": "Access-Control-Request-Headers"
										},
										{
											"key": "Content-Type",
											"value": "application/json"
										},
										{
											"key": "Content-Length",
											"value": "143"
										},
										{
											"key": "Cache-Control",
											"value": "no-cache, no-store, max-age=0, must-revalidate"
										},
										{
											"key": "Pragma",
											"value": "no-cache"
										},
										{
											"key": "Expires",
											"value": "0"
										},
										{
											"key": "X-Content-Type-Options",
											"value": "nosniff"
										},
										{
											"key": "X-Frame-Options",
											"value": "DENY"
										},
										{
											"key": "X-XSS-Protection",
											"value": "0"
										},
										{
											"key": "Referrer-Policy",
											"value": "no-referrer"
										}
									],
									"cookie": [],
									"body": "{\n    \"token\": \"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTczMTY0NjEwOCwiZXhwIjoxNzMxNjQ5NzA4fQ.reZUeKZkLaNECBUgK6dmAGTqfMwhFgaA64N6f0j8ELk\"\n}"
								}
							]
						}
					]
				},
				{
					"name": "crear cliente",
					"request": {
						"method": "POST",
						"header": [
							{
								"key": "Authorization",
								"value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTczMTY0Njg0MCwiZXhwIjoxNzMxNjUwNDQwfQ.Lip2sZXKacgtP3zACY2bue4ja9wkrj1Xd_KxM7S-6Ng",
								"type": "text"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "{\r\n  \"nombres\": \"admin\",\r\n  \"apellidos\": \"Pérez García\",\r\n  \"tipoDocumento\": \"DNI\",\r\n  \"numeroDocumento\": \"45678912\",\r\n  \r\n  \"productosFinancieros\": [\r\n    {\r\n  \"tipoProducto\" : \"Cuenta de ahorro\",\r\n  \"nombre\" : \"Cuenta Súper Tasa\",\r\n  \"saldo\" : 0.0\r\n\r\n}\r\n \r\n  ]\r\n}",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": "http://localhost:8081/api/client/create"
					},
					"response": [
						{
							"name": "cliente",
							"originalRequest": {
								"method": "POST",
								"header": [
									{
										"key": "Authorization",
										"value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTczMTY0NzAzMCwiZXhwIjoxNzMxNjUwNjMwfQ.xRbw2_8fTVmAnyIJ-AA79zzQ8Zqda-GUoW5mQlvUEaM",
										"type": "text",
										"disabled": true
									}
								],
								"body": {
									"mode": "raw",
									"raw": "{\r\n  \"nombres\": \"admin\",\r\n  \"apellidos\": \"Pérez García\",\r\n  \"tipoDocumento\": \"DNI\",\r\n  \"numeroDocumento\": \"45678912\",\r\n  \"codigoUnico\": \"\",\r\n  \r\n  \"productosFinancieros\": [\r\n    {\r\n  \"tipoProducto\" : \"Cuenta de ahorro\",\r\n  \"nombre\" : \"Cuenta Súper Tasa\",\r\n  \"saldo\" : 0.0\r\n\r\n}\r\n \r\n  ]\r\n}",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": "http://localhost:8081/api/client/create"
							},
							"status": "OK",
							"code": 200,
							"_postman_previewlanguage": "json",
							"header": [
								{
									"key": "Content-Type",
									"value": "application/json"
								},
								{
									"key": "Content-Length",
									"value": "140"
								},
								{
									"key": "Cache-Control",
									"value": "no-cache, no-store, max-age=0, must-revalidate"
								},
								{
									"key": "Pragma",
									"value": "no-cache"
								},
								{
									"key": "Expires",
									"value": "0"
								},
								{
									"key": "X-Content-Type-Options",
									"value": "nosniff"
								},
								{
									"key": "X-Frame-Options",
									"value": "DENY"
								},
								{
									"key": "X-XSS-Protection",
									"value": "0"
								},
								{
									"key": "Referrer-Policy",
									"value": "no-referrer"
								}
							],
							"cookie": [],
							"body": "{\n    \"code\": \"Created\",\n    \"status\": 201,\n    \"comment\": \"Cliente creado exitosamente\",\n    \"data\": {\n        \"message\": \"Cliente:\\nNombre:admin\\nApellido:Pérez García\"\n    }\n}"
						}
					]
				},
				{
					"name": "getById",
					"protocolProfileBehavior": {
						"disableBodyPruning": true
					},
					"request": {
						"method": "GET",
						"header": [
							{
								"key": "Authorization",
								"value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTczMTY0Njg0MCwiZXhwIjoxNzMxNjUwNDQwfQ.Lip2sZXKacgtP3zACY2bue4ja9wkrj1Xd_KxM7S-6Ng",
								"type": "text"
							}
						],
						"body": {
							"mode": "raw",
							"raw": "",
							"options": {
								"raw": {
									"language": "json"
								}
							}
						},
						"url": "http://localhost:8081/api/client/13"
					},
					"response": [
						{
							"name": "crear cliente Copy",
							"originalRequest": {
								"method": "GET",
								"header": [
									{
										"key": "Authorization",
										"value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTczMTY0Njg0MCwiZXhwIjoxNzMxNjUwNDQwfQ.Lip2sZXKacgtP3zACY2bue4ja9wkrj1Xd_KxM7S-6Ng",
										"type": "text"
									}
								],
								"body": {
									"mode": "raw",
									"raw": "",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": "http://localhost:8081/api/client/1"
							},
							"status": "OK",
							"code": 200,
							"_postman_previewlanguage": "json",
							"header": [
								{
									"key": "Content-Type",
									"value": "application/json"
								},
								{
									"key": "Content-Length",
									"value": "405"
								},
								{
									"key": "Cache-Control",
									"value": "no-cache, no-store, max-age=0, must-revalidate"
								},
								{
									"key": "Pragma",
									"value": "no-cache"
								},
								{
									"key": "Expires",
									"value": "0"
								},
								{
									"key": "X-Content-Type-Options",
									"value": "nosniff"
								},
								{
									"key": "X-Frame-Options",
									"value": "DENY"
								},
								{
									"key": "X-XSS-Protection",
									"value": "0"
								},
								{
									"key": "Referrer-Policy",
									"value": "no-referrer"
								}
							],
							"cookie": [],
							"body": "{\n    \"code\": \"OK\",\n    \"status\": 200,\n    \"comment\": \"Producto encontrado\",\n    \"data\": {\n        \"id\": 1,\n        \"nombres\": \"admin\",\n        \"apellidos\": \"Pérez García\",\n        \"codigoUnico\": \":Bl8Fmfh-m;j>-AAc9-D<>:-sD842@;?l3>@_1054992261_1731702112793\",\n        \"tipoDocumento\": \"DNI\",\n        \"numeroDocumento\": \"45678912\",\n        \"productosFinancieros\": [\n            {\n                \"id\": 4,\n                \"tipoProducto\": \"Cuenta de ahorro\",\n                \"nombre\": \"Cuenta Súper Tasa\",\n                \"saldo\": 0,\n                \"fechaCreacion\": \"2024-11-15T05:54:04.220458\"\n            }\n        ]\n    }\n}"
						}
					]
				}
			]
		}
	]
}rB.postman_collection…]()
