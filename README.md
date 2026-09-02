# Desarrollo de una API REST en Spring Boot

El equipo de desarrollo de una fintech necesita crear una API REST para gestionar transacciones financieras. La API debe permitir la creación, lectura, actualización y eliminación de transacciones. Las transacciones se almacenarán en una base de datos H2 y se documentarán utilizando Swagger. Los actores involucrados son el 'originador de créditos', el'motor antifraude' y el 'core bancario'. Las transacciones deben ser idempotentes con una clave compuesta por el número de operación y el canal de origen. La API debe manejar errores comunes como timeouts del motor antifraude y respuestas 5xx del core bancario.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Java Spring Boot REST API |
| **Nivel** | junior-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: Un IDE o editor de código.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Verifica que el proyecto arranca sin errores.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Configuración del entorno y creación de la estructura básica

**Objetivo:** Configurar el entorno de desarrollo y crear la estructura básica de la API REST.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Configurar el proyecto Spring Boot.
- Definir las entidades y repositorios necesarios para gestionar transacciones.
- Crear los controladores para las operaciones CRUD de transacciones.

**Entregable:** Proyecto Spring Boot configurado con entidades, repositorios y controladores básicos.

<details>
<summary>Pistas de conocimiento</summary>

- Estructura de un proyecto Spring Boot.
- Uso de JPA para definir entidades y repositorios.

</details>

### Fase 2: Implementación de la persistencia y documentación

**Objetivo:** Implementar la persistencia de transacciones en H2 y documentar la API utilizando Swagger.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Configurar la base de datos H2.
- Implementar la persistencia de transacciones.
- Documentar la API utilizando Swagger.

**Entregable:** API REST con persistencia en H2 y documentación completa en Swagger.

<details>
<summary>Pistas de conocimiento</summary>

- Configuración de H2 en Spring Boot.
- Uso de Swagger para documentar APIs.

</details>

### Fase 3: Manejo de errores y idempotencia

**Objetivo:** Implementar el manejo de errores comunes y garantizar la idempotencia de las transacciones.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Implementar el manejo de errores comunes como timeouts del motor antifraude y respuestas 5xx del core bancario.
- Garantizar la idempotencia de las transacciones utilizando una clave compuesta por el número de operación y el canal de origen.

**Entregable:** API REST con manejo de errores y garantía de idempotencia de transacciones.

<details>
<summary>Pistas de conocimiento</summary>

- Estrategias para manejar errores en APIs REST.
- Implementación de idempotencia en transacciones.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es una API REST y cuáles son sus componentes principales?
- **paraQueSirve**: ¿Para qué sirve documentar una API y cómo se hace utilizando Swagger?
- **comoSeUsa**: ¿Cómo se usa H2 para la persistencia de datos en una aplicación Spring Boot?
- **erroresComunes**: ¿Cuáles son los errores comunes que puede encontrar una API REST y cómo se manejan?
- **queDecisionesImplica**: ¿Qué decisiones implica garantizar la idempotencia en una API REST?

## Criterios de Evaluacion

- Configuración correcta del proyecto Spring Boot.
- Definición adecuada de entidades y repositorios.
- Implementación de la persistencia en H2.
- Documentación completa de la API utilizando Swagger.
- Manejo efectivo de errores comunes.
- Garantía de idempotencia en las transacciones.

---

*Reto generado automaticamente por Challenge Generator - Pragma*
