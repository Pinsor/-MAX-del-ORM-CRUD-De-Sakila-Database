# 🎬 Proyecto Sakila - Sistema de Gestión de Películas

Proyecto desarrollado como parte del curso **Informática Básica** en la **Universidad Autónoma de Santo Domingo (UASD)**.

Este sistema permite gestionar datos de la base de datos **Sakila** usando Java y JDBC, implementando arquitectura MVC, manejo de entidades con claves primarias y foráneas, reportes y exportaciones de datos.

---

## 📚 Tecnologías utilizadas

- Java (JDK 17+ / 23)
- Eclipse IDE
- MySQL + Sakila DB
- JDBC (conector MySQL)
- Gson (para exportar JSON)
- Patrón MVC + DAO

---

## 📦 Estructura del Proyecto

## ✅ Funcionalidades

- Gestión completa de actores y películas (CRUD)
- Exportación a archivos `.csv` y `.json`
- Consultas estadísticas (total de películas, rentas por ciudad, actores por película)
- Arquitectura limpia basada en paquetes (MVC)
- Uso de clases genéricas, abstractas y herencia
- Manejo de claves foráneas y autoincrementales

---

## ▶️ Instrucciones de ejecución

1. Asegúrate de tener la base de datos **Sakila** cargada en MySQL
2. Abre el proyecto en Eclipse
3. Configura el `.jar` del conector JDBC y Gson:
   - `gson-2.10.1.jar`
   - `mysql-connector-java-x.x.x.jar`
4. Ejecuta la clase `App.java` → `Run As → Java Application`
5. Usa el menú para gestionar actores, películas o ver reportes

---

## 📊 Reportes incluidos (`Reportes.java`)

- Total de películas
- Películas con más actores
- Rentas por ciudad (Top 10)

---

## 🧑‍💻 Autor

Desarrollado por: **[Odiseo García Santos]**  
Carrera: Informática  
Asignatura: Informática Básica  
Universidad Autónoma de Santo Domingo – **UASD**

---

## 📝 Licencia

Uso académico. Proyecto con fines educativos y demostrativos.
