# EcoTrack: Página de Presentación Interactiva (Landing Page)

La aplicación web interactiva para la presentación, evaluación y sustentación del proyecto **EcoTrack** se encuentra disponible en el directorio [`landing/`](../landing/):

* 🌐 **Archivo principal**: [`landing/index.html`](../landing/index.html)
* 🎨 **Estilos de diseño**: [`landing/styles.css`](../landing/styles.css)
* ⚡ **Lógica interactiva**: [`landing/script.js`](../landing/script.js)

---

## Módulos Interactivos Destacados

1. **Simulador de Smartphone en Vivo**:
   - Marco de smartphone interactivo con tres pantallas: *Feed de Reportes en Vivo*, *Formulario de Nuevo Reporte* e *Inicio de Sesión*.
   - Filtrado dinámico por categorías: *Puntos de Reciclaje*, *Vertederos Ilegales*, *Residuos Electrónicos (E-Waste)*, *Basura General* y *Compost Orgánico*.
   - Interacción con estados en tiempo real: ciclo interactivo de estados (*Pendiente* $\rightarrow$ *En Revisión* $\rightarrow$ *Resuelto*) al hacer clic sobre los badges.
   - Creación interactiva de eco-reportes con retroalimentación inmediata en el feed y mensaje de sincronización con Firestore.

2. **Calculadora de Impacto Comunitario**:
   - Deslizador de rango para simular el número de vecinos activos (de 20 a 2,500 ciudadanos).
   - Estimación en tiempo real de kilogramos de residuos reciclados, kilogramos de CO₂ evitados, micro-vertederos saneados y horas burocráticas ahorradas.

3. **Explorador Visual de Arquitectura**:
   - Diagrama de flujo interactivo que permite inspeccionar la justificación técnica de cada componente (*Cliente Android Jetpack Compose*, *Firebase Auth*, *Cloud Firestore* y *Extensiones Serverless / Edge AI*).

4. **Soporte de Temas Claro y Oscuro**:
   - Conmutador dinámico de tema con persistencia en `localStorage`.

5. **Preguntas Frecuentes (FAQ)**:
   - Acordeón interactivo con explicaciones técnicas sobre ejecución en Android Studio, persistencia offline y justificación de Firebase BaaS.

---

Para visualizar la landing page, abre [`landing/index.html`](../landing/index.html) en tu navegador preferido.
