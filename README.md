# DOSW_Lab5_Leguizamon

## Integrante

| Nombre | Usuario GitHub | Correo |
| --- | --- | --- |
| JUAN GUILLERMO LEGUIZAMON RODRIGUEZ | Prometeus1439 | juan.leguizamon-r@mail.escuelaing.edu.co |

### Ciclo TDD - registro de dron

**RED:** prueba que demuestra que un dron válido debe registrarse correctamente.

![Código de prueba](docs/evidence/tdd-red-testcode.png)
![Prueba fallando](docs/evidence/tdd-red.png)

**GREEN:** implementación mínima que hace pasar la prueba.

![Código para que pase la prueba](docs/evidence/tdd-green-code.png)
![Prueba pasando](docs/evidence/tdd-green.png)

**REFACTOR:** no fue necesario refactorizar en este ciclo, ya que la implementación
mínima ya era lo bastante clara y sin necesidad de duplicar el código.

## Evidencia de cobertura

### Primera ejecución
![Cobertura inicial](docs/evidence/coverage-first.png)

### Cobertura final
![Cobertura final](docs/evidence/coverage-final.png)

## Evidencia de SonarQube

Quality Gate: **Passed**

![Dashboard SonarQube](docs/evidence/sonarqube-dashboard.png)

- Security: A (0 issues)
- Reliability: A (2 issues)
- Maintainability: A (10 issues)
- Coverage: 86.5%
- Duplications: 0.0%

## Reflexión técnica

1. **¿Qué error o comportamiento inesperado fue detectado primero gracias a una prueba?**
   Al escribir la prueba para "dron inexistente" en `assignMission`, se detectó que el
   código lanzaba `NullPointerException` en lugar de `IllegalArgumentException`, ya que
   no existía ninguna validación antes de usar el resultado de `drones.get(droneId)`.

2. **¿Qué parte del código cambió durante REFACTOR sin modificar el comportamiento?**
   Se extrajo la lógica de búsqueda de operadores y misiones (usando streams de Java)
   a métodos privados reutilizables (`findOperatorById`, `hasActiveMission`,
   `findMissionById`), reduciendo la duplicación entre `assignMission` y `completeMission`.

3. **¿Qué casos adicionales aparecieron al revisar la cobertura?**
   Ninguno; el desarrollo siguiendo TDD desde el inicio ya garantizaba una cobertura
   de líneas del 88% en la primera ejecución de JaCoCo, superando el mínimo requerido.

4. **¿Qué hallazgo de SonarQube produjo un cambio real en el código?**
   SonarQube detectó 12 issues de Maintainability y Reliability: 2 llamadas a
   `LocalDateTime.now()` sin zona horaria explícita, 6 lambdas de prueba con
   más de una invocación que podía lanzar excepción, 1 comentario TODO
   pendiente, y 1 modificador `public` innecesario en la clase de test. Los
   12 se corrigieron, llevando ambas métricas a 0 issues abiertos.

## Pull Requests

- PR JUnit: https://github.com/Prometeus1439/DOSW_Lab5_Leguizamon/pull/1
- PR clases base: https://github.com/Prometeus1439/DOSW_Lab5_Leguizamon/pull/2
- PR TDD addDrone: https://github.com/Prometeus1439/DOSW_Lab5_Leguizamon/pull/3
- PR TDD assignMission: https://github.com/Prometeus1439/DOSW_Lab5_Leguizamon/pull/4
- PR TDD completeMission: https://github.com/Prometeus1439/DOSW_Lab5_Leguizamon/pull/5
- PR JaCoCo: https://github.com/Prometeus1439/DOSW_Lab5_Leguizamon/pull/6
- PR SonarQube: https://github.com/Prometeus1439/DOSW_Lab5_Leguizamon/pull/7