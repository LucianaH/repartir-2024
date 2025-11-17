package ar.com.grupoesfera.repartir.steps.gastos;

import ar.com.grupoesfera.repartir.model.Grupo;
import ar.com.grupoesfera.repartir.steps.FastCucumberSteps;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TotalNoEsNegativoSteps extends FastCucumberSteps {
    private Grupo grupo;
    private BigDecimal totalViejo;
    private String mensajeError;

    @Dado("un grupo con total igual a {int}")
    public void existeUnGrupoConTotalIgualA(int total) {
        List<String> miembros = new LinkedList<String>();
        miembros.add( "Oscar" );
        miembros.add( "Juan" );

        grupo = new Grupo();

        grupo.setMiembros( miembros );

        grupo.setTotal(new BigDecimal(total));
    }

    @Cuando("el usuario intenta agregar un gasto igual a {int}")
    public void elUsuarioIntentaAgregarUnGastoIgualA(int gasto) {
        mensajeError = null;
        totalViejo = grupo.getTotal();

        BigDecimal nuevoGasto = new BigDecimal(gasto);

        try {
            grupo.setTotal(totalViejo.add(nuevoGasto));
        }
        catch (Exception e) {
            mensajeError = e.getMessage();
        }
    }

    @Entonces("no debería agregarse el gasto")
    public void noDeberíaAgregarseElGasto() {
        assertThat(mensajeError).isEqualTo("El total no debe ser negativo");
    }

    @Y("el total resultante es igual a {int}")
    public void elTotalResultanteEsIgualA(int intTotal) {
        BigDecimal total = new BigDecimal(intTotal);
        assertThat(grupo.getTotal()).isEqualTo(total);
    }

    @Entonces("debería agregarse el gasto")
    public void deberíaAgregarseElGasto() {
        assertThat(mensajeError).isNull();
    }
}
