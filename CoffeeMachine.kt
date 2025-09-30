package PrimerTrimestre.MaquinaCafe

object CoffeeMachine {

    fun hacerCafe(tipo: String = "normal") {
        println("--- Intentando hacer café ---")

        when (StateMachine.estadoActual) {
            is CoffeeMachineState.Idle -> {
                println("Comenzando a preparar café...")
                StateMachine.cambiarEstado(CoffeeMachineState.MakingCoffee)
            }
            is CoffeeMachineState.MakingCoffee -> {
                println("Ya se está haciendo café, espera...")
            }
            is CoffeeMachineState.ServingCoffee -> {
                val cafeActual = StateMachine.estadoActual as CoffeeMachineState.ServingCoffee
                println("Ya hay café listo: ${cafeActual.tipo}")
            }
            is CoffeeMachineState.Error -> {
                val error = StateMachine.estadoActual as CoffeeMachineState.Error
                println("Hay un error: ${error.problema}")
            }
        }
    }

    fun reportarError() {
        StateMachine.cambiarEstado(CoffeeMachineState.Error("Problema desconocido"))
    }

    fun limpiarMaquina() {
        StateMachine.limpiar()
    }
}