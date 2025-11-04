# language: es

Característica: Agregar gastos a un grupo

  Regla: El gasto total de un grupo no debe ser negativo

    Escenario: No puedo agregar un gasto que haga al total negativo
      Dado un grupo con total igual a 500
      Cuando el usuario intenta agregar un gasto igual a -1000
      Entonces no debería agregarse el gasto
      Y el total resultante es igual a 500

    Escenario: Puedo agregar un gasto negativo que no haga al total negativo
      Dado un grupo con total igual a 500
      Cuando el usuario intenta agregar un gasto igual a -100
      Entonces debería agregarse el gasto
      Y el total resultante es igual a 400

    Escenario: Puedo agregar un gasto negativo que haga que el total sea cero
      Dado un grupo con total igual a 500
      Cuando el usuario intenta agregar un gasto igual a -500
      Entonces debería agregarse el gasto
      Y el total resultante es igual a 0
