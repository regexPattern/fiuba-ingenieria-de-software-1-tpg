# language: es

# HDU: https://github.com/claudiogimenez26/squad04Psa/issues/11

Requisito: Carga de horas de trabajo
  Escenario: Se cargan horas de trabajo exitosamente
    Dada una tarea con id 23, correspondiente al proyecto con id 3, llamada 'implementar rutas API' # TODO: falta agregar los otros campos
    Y un empleado con id 1, nombre 'Carlos', apellido 'Castillo', cargo 'desarrollador' y salario de $5
    Cuando el empleado carga 5 horas de trabajo a la tarea en la fecha '11/11/2024'
    Entonces la operación debe ser exitosa
    Y ...

  Escenario: No se pueden cargar horas de trabajo a una tarea de un proyecto pausado

  Escenario: No se pueden cargar horas de trabajo en una fecha futura

  Escenario: No se pueden cargar horas de trabajo negativas

  Escenario: No se pueden cargar más de 24 horas de trabajo en una fecha

  Escenario: No se pueden cargar horas de trabajo a una tarea inexistente
