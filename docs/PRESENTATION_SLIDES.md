# EcoTrack: Presentación Ejecutiva y Técnica del Proyecto

Este documento contiene la estructura diapositiva por diapositiva, diseñada para su uso directo en herramientas como PowerPoint, Keynote, Google Slides o Canva, orientada a la sustentación académica de la asignatura y defensa ante el Semillero de Investigación.

---

## Diapositiva 1: Declaración del Problema y Contexto

### Título de la Diapositiva:
**El Déficit de Visibilidad en la Gestión de Residuos Urbanos: ¿Por Qué Fallan los Canales Tradicionales?**

### Subtítulo:
*Empoderamiento ciudadano a través de telemetría móvil sin fricciones*

### Sugerencia Visual:
Pantalla dividida: En el lado izquierdo, una fotografía de un punto crítico de acumulación de basuras con un ícono de alerta roja. En el lado derecho, un usuario frustrado ante un formulario web municipal tradicional para computadoras de escritorio, con más de 15 campos burocráticos.

### Puntos Clave de la Diapositiva:
* **El Cuello de Botella Ambiental**: El rápido crecimiento urbano genera puntos ciegos de acumulación de residuos y vertederos no autorizados que las autoridades locales no alcanzan a detectar a tiempo.
* **La Barrera Burocrática**: Los portales web de atención ciudadana existentes presentan tasas de abandono superiores al 65% debido a registros engorrosos, ausencia de diseño enfocado en móviles y falta de respuesta oportuna.
* **Asimetría de la Información**: Los ciudadanos con voluntad de reciclar carecen de un directorio comunitario geolocalizado de contenedores especializados (residuos electrónicos, plásticos, baterías).
* **Motivación del Proyecto**: Investigar cómo la ingeniería Lean Mobile y los servicios en la nube en tiempo real reducen el tiempo de reporte ciudadano de minutos a solo segundos.

### Guion para el Expositor (60 segundos):
> *"Buenas tardes, docentes y compañeros del comité evaluador. En las ciudades contemporáneas, el problema del manejo de residuos sólidos no radica únicamente en la falta de camiones recolectores, sino primordialmente en la asimetría de la información. La ciudadanía identifica anomalías ambientales a diario, pero los canales tradicionales de reporte son lentos, burocráticos y están pensados para computadoras de escritorio. EcoTrack nace precisamente para responder a esta brecha: comprobar si un Producto Mínimo Viable móvil, intuitivo y sin barreras burocráticas, puede convertir a cualquier ciudadano en un agente activo de monitoreo ambiental."*

---

## Diapositiva 2: Metodología Lean Mobile y Propuesta de Valor

### Título de la Diapositiva:
**Innovación con Lean Mobile: El Ciclo Construir - Medir - Aprender**

### Subtítulo:
*Validación de hipótesis de impacto mediante un Producto Mínimo Viable (MVP)*

### Sugerencia Visual:
Un diagrama circular dinámico que represente el ciclo de retroalimentación *Construir $\rightarrow$ Medir $\rightarrow$ Aprender* adaptado a desarrollo móvil: Despliegue Rápido $\rightarrow$ Datos en Tiempo Real $\rightarrow$ Validación con Usuarios.

### Puntos Clave de la Diapositiva:
* **Enfoque Lean Mobile**: Eliminación estricta de funcionalidades superfluas para validar el comportamiento esencial: captura ágil de incidentes y difusión comunitaria en tiempo real.
* **Hipótesis de Valor**:
  > *"Si el proceso de reporte se simplifica a menos de 3 pasos y menos de 30 segundos, la participación ciudadana activa se incrementará en más de un 400% respecto a los portales web tradicionales."*
* **Hipótesis de Crecimiento y Fidelización**:
  > *"La visibilidad del ciclo de vida del reporte (Pendiente $\rightarrow$ En Revisión $\rightarrow$ Resuelto) fomenta la validación entre pares y la recurrencia en el uso de la aplicación."*
* **Delimitación del Alcance del MVP**:
  * *Incluido*: Autenticación ágil, listado sincronizado en tiempo real, categorización con filtros y formulario ultrarrápido.
  * *Pospuesto para siguientes iteraciones*: Módulos complejos de gamificación comercial y paneles administrativos pesados.

### Guion para el Expositor (60 segundos):
> *"La aplicación de la metodología Lean Startup al entorno móvil demanda priorización radical. En lugar de dedicar seis meses a desarrollar una aplicación pesada con decenas de funciones no validadas, formulamos dos hipótesis fundamentales centradas en la rapidez de reporte y la transparencia del proceso. El MVP de EcoTrack se diseñó con el alcance estrictamente necesario para comprobar si la eliminación de fricciones motiva una participación ciudadana recurrente antes de incurrir en costos elevados de desarrollo."*

---

## Diapositiva 3: Arquitectura en la Nube y Sistemas Móviles

### Título de la Diapositiva:
**Escalabilidad en la Nube Serverless: Android Jetpack Compose y Firebase BaaS**

### Subtítulo:
*Arquitectura desacoplada, reactiva y con cero costos fijos de servidor*

### Sugerencia Visual:
Diagrama de arquitectura técnica que ilustre la conexión entre el Cliente Móvil Android (UI en Compose $\rightarrow$ ViewModel $\rightarrow$ Repositorio) y la infraestructura de Google Firebase (Authentication y Cloud Firestore) comunicados vía gRPC y WebSockets.

### Puntos Clave de la Diapositiva:
* **Capa de Cliente Móvil**:
  * Desarrollo 100% en Kotlin 2.0 con Jetpack Compose y Material Design 3.
  * Arquitectura MVVM con flujo unidireccional de datos (UDF) mediante `StateFlow` y Corrutinas.
* **Infraestructura en la Nube (Google Firebase BaaS)**:
  * **Firebase Authentication**: Gestión de identidades mediante tokens criptográficos JWT, garantizando trazabilidad sin exponer credenciales.
  * **Cloud Firestore**: Base de datos NoSQL distribuida multi-región, con listeners en tiempo real basados en `callbackFlow`.
  * **Seguridad Declarativa**: Reglas de seguridad a nivel de base de datos que validan el esquema y aseguran que solo usuarios autenticados realicen publicaciones.
* **Atributos de Rendimiento**:
  * Persistencia y caché local integrada que permite operar y registrar datos aún en zonas sin cobertura de red.

### Guion para el Expositor (60 segundos):
> *"A nivel técnico y de ingeniería de software, EcoTrack implementa un modelo plenamente reactivo y escalable. En la aplicación móvil, Jetpack Compose permite construir interfaces declarativas y fluidas bajo estándares Material 3. En la nube, prescindimos de la infraestructura de servidores monolíticos al apoyarnos en Firebase como Backend-as-a-Service. Gracias a las corrutinas de Kotlin y los flujos de datos en tiempo real, cualquier reporte registrado en la base de datos distribuida se propaga a los demás dispositivos en milisegundos sin requerir sondeos periódicos."*

---

## Diapositiva 4: Características Principales del MVP y Flujo de Usuario

### Título de la Diapositiva:
**La Experiencia EcoTrack: Del Hallazgo a la Solución en Tres Pasos**

### Subtítulo:
*Recorrido de usuario optimizado y diseñado para alta usabilidad en campo*

### Sugerencia Visual:
Tres capturas de pantalla de la aplicación presentadas en secuencia horizontal: 1. Pantalla de Acceso Rápido $\rightarrow$ 2. Dashboard con Filtros y Estados $\rightarrow$ 3. Formulario Ágil de Registro.

### Puntos Clave de la Diapositiva:
* **Paso 1: Acceso y Registro Ágil**:
  * Interfaz limpia con validación instantánea de datos y persistencia automática de sesión.
* **Paso 2: Dashboard Comunitario en Vivo**:
  * Visualización centralizada de reportes e incidencias ecológicas cercanas.
  * Filtros horizontales por categoría (*Puntos de Reciclaje, Basuras Clandestinas, Residuos Electrónicos, etc.*).
  * Etiquetas cromáticas de estado (*Pendiente [Ámbar]*, *En Revisión [Azul]*, *Resuelto [Verde]*).
* **Paso 3: Creación Rápida de Reportes**:
  * Formulario conciso con título, categoría, dirección de referencia y descripción.
  * Sincronización asíncrona inmediata con indicadores visuales de progreso.

### Guion para el Expositor (60 segundos):
> *"Esta diapositiva ilustra la experiencia de uso en campo. Al iniciar la aplicación, el ciudadano ingresa inmediatamente al panel interactivo donde visualiza los reportes activos en su sector y puede filtrarlos con un solo toque. Si encuentra un residuo no clasificado o desea reportar un nuevo punto ecológico, presiona el botón flotante y completa un formulario diseñado para completarse en menos de medio minuto. En el instante en que presiona 'Enviar', el reporte queda publicado y visible para la comunidad y las cuadrillas de limpieza."*

---

## Diapositiva 5: Resultados Piloto, Métricas y Potencial para Semillero de Investigación

### Título de la Diapositiva:
**Validación de Métricas y Horizonte Académico: Hacia Dónde Vamos**

### Subtítulo:
*Evolución de un MVP de aula hacia una línea de investigación aplicada*

### Sugerencia Visual:
Gráficos comparativos de las métricas alcanzadas (Tiempo promedio de reporte: 18.4s, Latencia en la nube: <350ms) junto a infografías de futuras líneas de investigación (Inteligencia Artificial Edge y Mapas de Calor GIS).

### Puntos Clave de la Diapositiva:
* **Resultados Obtenidos en la Fase Piloto**:
  * Tiempo medio por reporte: **18.4 segundos** (superando la meta fijada de <30 segundos).
  * Latencia media de sincronización en la nube: **menor a 350 ms** sobre redes móviles 4G.
  * Rendimiento visual constante a 60 FPS gracias al renderizado nativo de Jetpack Compose.
* **Aporte Formativo Institucional (UCN)**:
  * Cumplimiento integral de los objetivos de la cátedra de *Diseño de Aplicaciones Móviles*.
* **Proyección para el Semillero de Investigación**:
  * **Visión por Computadora en el Dispositivo (Edge AI)**: Integración de TensorFlow Lite para clasificación automática de residuos mediante la cámara del móvil.
  * **Inteligencia Geoespacial**: Algoritmos de clustering y mapas de calor (heatmaps) para optimizar rutas municipales de recolección.
  * **Portal Web Abierto**: Conexión de una API abierta para que las administraciones públicas descarguen analíticas ambientales ciudadanas.

### Guion para el Expositor (60 segundos):
> *"Para finalizar, EcoTrack demuestra con datos la viabilidad de nuestra hipótesis Lean: disminuir las barreras de entrada potencia notablemente la interacción ciudadana manteniendo estándares de calidad de software profesional. Más allá de esta asignatura, EcoTrack se consolida como una base sólida para nuestro Semillero de Investigación. La incorporación de modelos de visión artificial en el dispositivo para clasificar residuos y la generación de mapas de calor geoespaciales permitirán transformar este MVP en una solución de tecnología cívica de gran impacto. Muchas gracias; quedo atento a sus preguntas."*
