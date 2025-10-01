package PrimerTrimestre.MaquinaCafe


interface ICoffeeMachineState {
    fun onEnter(stateMachine: StateMachine)
}

sealed class CoffeeMachineState : ICoffeeMachineState {

    object Idle : CoffeeMachineState() {
        val inicio = System.currentTimeMillis()

        override fun onEnter(stateMachine: StateMachine) {
            println("Máquina en reposo desde $inicio")
            println("Lista para hacer café")
        }
    }

    object MakingCoffee : CoffeeMachineState() {
        override fun onEnter(stateMachine: StateMachine) {
            println("Haciendo café...")
            Thread.sleep(800)
            println("Café preparado!")
            stateMachine.cambiarEstado(ServingCoffee("Café normal"))
        }
    }


    data class ServingCoffee(val tipo: String) : CoffeeMachineState() {
        override fun onEnter(stateMachine: StateMachine) {
            println("Sirviendo: $tipo")
            Thread.sleep(1500)
            println("Café servido, volviendo a reposo")
            stateMachine.cambiarEstado(Idle)
        }
    }

    data class Error(val problema: String) : CoffeeMachineState() {
        override fun onEnter(stateMachine: StateMachine) {
            println("ERROR: $problema")
        }
    }
}