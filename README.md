# Carrera profesional de futbolista

Simulador en el que llevas a un futbolista hasta la final de la UEFA Champions League. Gestionas tu energía y tu dinero entre partido y partido, y tomas decisiones en los momentos clave de cada encuentro.

> Proyecto individual de la primera evaluación. Autor: Iker Flores López

## Descripción

Al empezar eliges el nombre, la altura y el peso de tu jugador, un equipo (Real Madrid, Barcelona, Bayern o PSG) y una posición (delantero, centrocampista, defensa o portero). Después juegas desde un menú principal hasta ganar la competición, ser eliminado, quedarte sin energía o retirarte.

## Reglas básicas

- Empiezas con **100 de energía**, **500 €** y **0 puntos de carrera**.
- Hay **3 partidos eliminatorios**: cuartos (Borussia Dortmund), semifinal (Arsenal) y final (Juventus).
- En cada partido hay **3 jugadas clave** (con opciones distintas según tu posición). Cada opción puede dar un gol a favor, ningún gol o un gol en contra.
- Si al final ganas por goles, pasas de ronda. Si pierdes, quedas eliminado. Si empatas, se decide en **penaltis**.
- Jugar cuesta **20 de energía** por partido y solo puedes jugar con **más de 20 de energía**.
- **Entrenar** da +50 puntos y +100 € pero cuesta 25 de energía.
- **Descansar en el spa** cuesta 150 € y recupera 40 de energía (máximo 100).
- Si tu energía llega a 0, pierdes la temporada.
- **Ganas** al superar la final. **Pierdes** si te eliminan o si te quedas sin energía.

## Requisitos

- JDK 17 o superior (se ha probado con JDK 21).
- Una terminal que admita colores ANSI y UTF-8 (terminal de VS Code, Windows Terminal, terminales de Linux y macOS).

## Cómo compilar y ejecutar

Desde la carpeta raíz del repositorio:

```bash
# 1. Compilar (los .class se generan en la carpeta out/)
javac -encoding UTF-8 -d out src/Carreraprofesional.java

# 2. Ejecutar
java -cp out Carreraprofesional
```

También puedes ejecutarlo sin compilar a mano:

```bash
java src/Carreraprofesional.java
```

En VS Code basta con abrir `src/Carreraprofesional.java` y pulsar *Run*.

## Estructura del repositorio

```
.
├── src/
│   └── Carreraprofesional.java   # código fuente del juego
├── docs/
│   └── Iker_Flores_MemoriaVideojuego_v1.1.pdf               # memoria del proyecto
├── .gitignore
└── README.md
```

## Versión

`v1.1-primera-evaluacion`