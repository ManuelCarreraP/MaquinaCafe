// CoffeeMachineTest.kt
package PrimerTrimestre.MaquinaCafe

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

class CoffeeMachineTest {

    @BeforeEach
    fun setup() {
        // 1. Asegurarse de que la máquina esté en estado inicial antes de cada prueba.
        // Se usa limpiarMaquina() para restablecer StateMachine.estadoActual a Idle.
        CoffeeMachine.limpiarMaquina()
        assertEquals(CoffeeMachineState.Idle, StateMachine.obtenerEstado(), "El estado debe ser Idle después de la limpieza inicial.")
    }

    // -------------------------------------------------------------------------
    // Pruebas de la lógica de StateMachine y CoffeeMachine (Integración y Unidad)
    // -------------------------------------------------------------------------

    @Test
    fun `01_hacerCafe_desde_Idle_completa_el_ciclo_y_termina_en_Idle`() {
        // Cuando se llama a hacerCafe, se inicia la cadena de transiciones:
        // Idle -> MakingCoffee -> ServingCoffee -> Idle
        // Debido a que los metodos onEnter contienen Thread.sleep y llamadas a cambiarEstado,
        // la ejecución es síncrona y la prueba debe verificar el estado final.

        CoffeeMachine.hacerCafe()

        assertEquals(CoffeeMachineState.Idle, StateMachine.obtenerEstado(), "El ciclo de hacer café debe completarse (Idle a Idle).")
    }

    @Test
    fun `02_hacerCafe_desde_MakingCoffee_no_cambia_estado_y_avisa_al_usuario`() {
        // Configuramos el estado directamente para simular que está en curso.
        // Nota: En un entorno de producción, esto se haría mockeando la función onEnter.
        StateMachine.estadoActual = CoffeeMachineState.MakingCoffee

        CoffeeMachine.hacerCafe()

        assertEquals(CoffeeMachineState.MakingCoffee, StateMachine.obtenerEstado(), "El estado debe permanecer en MakingCoffee.")
    }

    @Test
    fun `03_hacerCafe_desde_ServingCoffee_no_cambia_estado_y_avisa_al_usuario`() {
        val cafeListo = CoffeeMachineState.ServingCoffee("Latte")
        StateMachine.estadoActual = cafeListo

        CoffeeMachine.hacerCafe()

        // El estado debe permanecer como ServingCoffee ya que la lógica de CoffeeMachine lo impide.
        // La prueba se basa en que CoffeeMachine.hacerCafe() no ejecuta cambiarEstado() en este caso.
        assertEquals(cafeListo, StateMachine.obtenerEstado(), "El estado debe permanecer en ServingCoffee.")
    }

    @Test
    fun `04_reportarError_cambia_a_estado_Error`() {
        // Empezamos en Idle y reportamos un error.
        CoffeeMachine.reportarError()
        val estadoActual = StateMachine.obtenerEstado()

        // Verificamos que el estado actual sea una instancia de Error
        assertTrue(estadoActual is CoffeeMachineState.Error, "El estado debe ser Error después de reportar un error.")
    }

    @Test
    fun `05_hacerCafe_desde_Error_no_cambia_estado_y_avisa_al_usuario`() {
        val errorActual = CoffeeMachineState.Error("Falta agua")
        StateMachine.estadoActual = errorActual

        CoffeeMachine.hacerCafe()

        // El estado debe permanecer en el estado de Error
        assertEquals(errorActual, StateMachine.obtenerEstado(), "El estado debe permanecer en Error.")
    }

    @Test
    fun `06_limpiarMaquina_desde_Error_vuelve_a_Idle`() {
        StateMachine.estadoActual = CoffeeMachineState.Error("Falta café")

        CoffeeMachine.limpiarMaquina()

        assertEquals(CoffeeMachineState.Idle, StateMachine.obtenerEstado(), "Limpiar máquina debe regresar a Idle desde Error.")
    }

    @Test
    fun `07_limpiarMaquina_desde_MakingCoffee_vuelve_a_Idle`() {
        // Simulamos un estado intermedio para probar la limpieza
        StateMachine.estadoActual = CoffeeMachineState.MakingCoffee

        CoffeeMachine.limpiarMaquina()

        assertEquals(CoffeeMachineState.Idle, StateMachine.obtenerEstado(), "Limpiar máquina debe regresar a Idle desde MakingCoffee.")
    }

    @Test
    fun `08_transicion_invalida_de_Idle_a_Error_es_rechazada_por_StateMachine`() {
        // Probamos una transición que no está permitida en transicionValida()
        StateMachine.estadoActual = CoffeeMachineState.Idle
        val estadoAntes = StateMachine.obtenerEstado()

        // La máquina de estados debería imprimir un mensaje, pero el estado no debe cambiar.
        StateMachine.cambiarEstado(CoffeeMachineState.Error("Fallo interno"))

        assertEquals(estadoAntes, StateMachine.obtenerEstado(), "El estado no debe cambiar de Idle a Error.")
    }
}