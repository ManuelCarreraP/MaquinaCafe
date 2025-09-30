package PrimerTrimestre.MaquinaCafe

object StateMachine {
    var estadoActual: CoffeeMachineState = CoffeeMachineState.Idle

    fun cambiarEstado(nuevoEstado: CoffeeMachineState) {
        if (transicionValida(estadoActual, nuevoEstado)) {
            estadoActual = nuevoEstado
            actualizarEstado()
        } else {
            println("No se puede cambiar de $estadoActual a $nuevoEstado")
        }
    }

    private fun transicionValida(desde: CoffeeMachineState, hacia: CoffeeMachineState): Boolean {
        return when (desde) {
            CoffeeMachineState.Idle -> hacia == CoffeeMachineState.MakingCoffee
            CoffeeMachineState.MakingCoffee -> hacia is CoffeeMachineState.ServingCoffee
            is CoffeeMachineState.ServingCoffee -> hacia == CoffeeMachineState.Idle
            is CoffeeMachineState.Error -> hacia == CoffeeMachineState.Idle
            else -> false
        }
    }

    fun obtenerEstado(): CoffeeMachineState {
        return estadoActual
    }

    fun actualizarEstado() {
        println("Estado: $estadoActual")
        estadoActual.onEnter(this)
    }

    fun limpiar() {
        println("Limpiando máquina...")
        estadoActual = CoffeeMachineState.Idle
        println("Máquina limpia y en reposo")
    }
}