package organizadordemetas

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Tarea)
class TareaSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == true
    }
    //pendienta A
    void testPendienteAEnEjecucion() {
		tarea = new Tarea("Text", "Text")
		tarea.estado = Estado.Pendiente
		tarea.CambiarEstado(Estado.EnEjecucion)
		
        tarea.estado == Estado.EnEjecucion
    }    
    
    void testPendienteAFinalizado() {
		tarea = new Tarea("Text", "Text")
		tarea.estado = Estado.Pendiente
		 def msg = shouldFail {
            tarea.CambiarEstado(Estado.Finalizado)
        }
        assert 'El estado que se utilizo no es valido' == msg
    }  
    
    void testPendienteACancelado() {
		tarea = new Tarea("Text", "Text")
		tarea.estado = Estado.Pendiente		
        tarea.CambiarEstado(Estado.Cancelado)
        
        tarea.estado == Estado.Cancelado
    }
    
    void testPendienteAEnEjecucionConSubTareaNoObligatorioa() {
		tarea = new Tarea("Text", "Text")
		tarea2 = new Tarea("Text", "Text")
		tarea.estado = Estado.Pendiente
		tarea2.estado = Estado.Pendiente
		tarea.agregarSubMeta(tarea2, false)
		
		tarea.CambiarEstado(Estado.EnEjecucion)
		
        tarea.estado == Estado.EnEjecucion
    }  
    
     void testPendienteAEnEjecucionConSubTareaObligatorioa() {
		tarea = new Tarea("Text", "Text")
		tarea2 = new Tarea("Text", "Text")
		tarea.estado = Estado.Pendiente
		tarea2.estado = Estado.Pendiente
		tarea.agregarSubMeta(tarea2, true)
		
		def msg = shouldFail {
			tarea.CambiarEstado(Estado.EnEjecucion)
		}
        assert 'El estado que se utilizo no es valido' == msg
    }     
    
    //En ejecucion A
    void testEnEjecucionAPendiente() {
		tarea = new Tarea("Text", "Text")
		tarea.estado = Estado.EnEjecucion
		 def msg = shouldFail {
            tarea.CambiarEstado(Estado.Pendiente)
        }
        assert 'El estado que se utilizo no es valido' == msg
    }  
     
    void testEnEjecucionAFinalizado() {
		tarea = new Tarea("Text", "Text")
		tarea.estado = Estado.EnEjecucion
        tarea.CambiarEstado(Estado.Finalizado)
        
        tarea.estado == Estado.Finalizado
    }   
     
    void testEnEjecucionACancelado() {
		tarea = new Tarea("Text", "Text")
		tarea.estado = Estado.EnEjecucion
        tarea.CambiarEstado(Estado.Cancelado)
        
        tarea.estado == Estado.Cancelado
    }       
    
    void testEnEjecucionAgregarTareaObligatoria() {
		tarea = new Tarea("Text", "Text")
		tarea2 = new Tarea("Text", "Text")
		tarea.estado = Estado.EnEjecucion
		tarea2.estado = Estado.Pendiente
		
		def msg = shouldFail {
		tarea.agregarSubMeta(tarea2, true)
		}
        assert 'No se puede agregar tarea obligatoria cuando ya se comenzo' == msg
    }  

    void testEnEjecucionAFinalizadoConTareasPendientes() {
		tarea = new Tarea("Text", "Text")
		tarea2 = new Tarea("Text", "Text")
		tarea.estado = Estado.EnEjecucion
		tarea2.estado = Estado.Pendiente
		tarea.agregarSubMeta(tarea2, false)
 
 		def msg = shouldFail {
            tarea.CambiarEstado(Estado.Finalizado)
        }
        assert 'El estado que se utilizo no es valido' == msg
    } 
	
	//En Finalizada A
	void testFinalizadoACualquierEstado() {
		tarea = new Tarea("Text", "Text")
		tarea.estado = Estado.Finalizado
 
 		def msg = shouldFail {
            tarea.CambiarEstado(Estado.Pendiente)
        }
        assert 'El estado que se utilizo no es valido' == msg
        
        msg = shouldFail {
            tarea.CambiarEstado(Estado.Cancelado)
        }
        assert 'El estado que se utilizo no es valido' == msg
        
        msg = shouldFail {
            tarea.CambiarEstado(Estado.EnEjecucion)
        }
        assert 'El estado que se utilizo no es valido' == msg
    } 

	void testFinalizadoNoAgregarNuevasSubMeta() {
		tarea = new Tarea("Text", "Text")
		tarea.estado = Estado.Finalizado
        tarea2 = new Tarea("Text", "Text")
        
		def msg = shouldFail {
		tarea.agregarSubMeta(tarea2, true)
		}
        assert 'No se puede agregar tarea obligatoria cuando ya se comenzo' == msg
        
		msg = shouldFail {
		tarea.agregarSubMeta(tarea2, false)
		}
        assert 'No se puede agregar tarea obligatoria cuando ya se comenzo' == msg
    } 
    
    //En Cancelado A
	
	void testCanceladoACualquierEstado() {
		tarea = new Tarea("Text", "Text")
		tarea.estado = Estado.Cancelado
 
 		def msg = shouldFail {
            tarea.CambiarEstado(Estado.Pendiente)
        }
        assert 'El estado que se utilizo no es valido' == msg
        
        msg = shouldFail {
            tarea.CambiarEstado(Estado.Finalizado)
        }
        assert 'El estado que se utilizo no es valido' == msg
        
        msg = shouldFail {
            tarea.CambiarEstado(Estado.EnEjecucion)
        }
        assert 'El estado que se utilizo no es valido' == msg
    } 

	void testCanceladoAgregarNuevasSubMeta() {
		tarea = new Tarea("Text", "Text")
		tarea.estado = Estado.Cancelado
        tarea2 = new Tarea("Text", "Text")
        
		def msg = shouldFail {
		tarea.agregarSubMeta(tarea2, true)
		}
        assert 'No se puede agregar tarea obligatoria cuando ya se comenzo' == msg
        
		msg = shouldFail {
		tarea.agregarSubMeta(tarea2, false)
		}
        assert 'No se puede agregar tarea obligatoria cuando ya se comenzo' == msg
    } 
}
