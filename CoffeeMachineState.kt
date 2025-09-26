package PrimerTrimestre.MaquinaCafe

/**
 * Clase sellada que representa los posibles estados de la máquina de café.
 */

sealed class CoffeeMachineState {
    object Idle : CoffeeMachineState()
    object MakingCoffee : CoffeeMachineState()
    data class ServingCoffee(val brand: String, val price: Double) : CoffeeMachineState()
    data class Error(val message: String) : CoffeeMachineState()
}