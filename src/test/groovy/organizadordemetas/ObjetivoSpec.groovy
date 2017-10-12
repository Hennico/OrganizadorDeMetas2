package organizadordemetas

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Objetivo)
class ObjetivoSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }
	
    void "terminar la unica tarea hija finaliza el objetivo" () {
		Objetivo objetivo = new Objetivo ("1", "2", Obligatoriedad.NECESARIO)
		Tarea tarea  = new Tarea("1","2", Obligatoriedad.NECESARIO)
		objetivo.agregarPaso(tarea)

		tarea.cambiarEstado(Estado.EN_EJECUCION)
		tarea.cambiarEstado(Estado.FINALIZADA)
		
		expect: "esta finalizado"
			objetivo.estado == Estado.FINALIZADA
    } 
	
	void "la validacion de pasos finalizados es valida" () {
		Objetivo objetivo = new Objetivo ("1", "2", Obligatoriedad.NECESARIO)
		Tarea tarea  = new Tarea("1","2", Obligatoriedad.NECESARIO)
		objetivo.agregarPaso(tarea)
		
		tarea.cambiarEstado(Estado.EN_EJECUCION)
		tarea.cambiarEstado(Estado.FINALIZADA)
		
		expect: "pasos finalizados"
			!(objetivo.plan.any { it -> !it.estaCompleta() })
	}
	
	void "agregar paso hace que el objetivo se subscriva al paso" () {
		Objetivo objetivo = new Objetivo ("1", "2", Obligatoriedad.NECESARIO)
		Tarea tarea  = new Tarea("1","2", Obligatoriedad.NECESARIO)
		objetivo.agregarPaso(tarea)
		
		expect: "listeners de tarea aumentaron"
			tarea.listeners[0] == objetivo
	}
	
	void "no pasa"() {
		expect: "anda basura"
			true==true
	}
	/*
    void testPendienteFinalizadaConDosTareas() {
		objetivo = new Tarea("Text", "Text")
		objetivo.estado = Estado.Pendiente
		tarea2 = new Tarea("Text", "Text")
		tarea2.estado = Estado.EnEjecucion
		objetivo.agregarSubMeta(tarea2, true)
		tarea3 = new Tarea("Text", "Text")
		tarea3.estado = Estado.EnEjecucion
		objetivo.agregarSubMeta(tarea3, true)
		
		tarea2.CambiarEstado(Estado.Finalizado)
		
		objetivoestado == Estado.Pendiente
		
		tarea3.CambiarEstado(Estado.Finalizado)
		
        objetivoestado == Estado.Finalizado
    }  
    
    void testFinalizadaNoDejaNueavsSubTareas() {
		objetivo = new Tarea("Text", "Text")
		objetivo.estado = Estado.Finalizado
		
		
		tarea2 = new Tarea("Text", "Text")
		tarea2.estado = Estado.EnEjecucion
		
		def msg = shouldFail {
			objetivo.agregarSubMeta(tarea2, true)
		}
        assert 'No se puede agregar tarea obligatoria cuando ya se comenzo' == msg
		
		tarea3 = new Tarea("Text", "Text")
		tarea3.estado = Estado.EnEjecucion
		
		msg = shouldFail {
			objetivo.agregarSubMeta(tarea3, false)
		}
        assert 'No se puede agregar tarea obligatoria cuando ya se comenzo' == msg

    }
	*/
}
