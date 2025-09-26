package PrimerTrimestre.MaquinaCafe

/*
class CoffeeMachineTest {

    @Test
    fun makeCoffee_whenIdle_shouldTransitionToServingCoffee() {
        CoffeeMachine.clean() // Ensure the machine starts in Idle state
        CoffeeMachine.makeCoffee()
        assertTrue(CoffeeMachine.currentState is CoffeeMachineState.ServingCoffee)
    }

    @Test
    fun makeCoffee_whenMakingCoffee_shouldNotChangeState() {
        CoffeeMachine.clean()
        CoffeeMachine.makeCoffee() // Transition to ServingCoffee
        CoffeeMachine.makeCoffee() // Attempt to make coffee again
        assertTrue(CoffeeMachine.currentState is CoffeeMachineState.ServingCoffee)
    }

    @Test
    fun makeCoffee_whenServingCoffee_shouldNotChangeState() {
        CoffeeMachine.clean()
        CoffeeMachine.makeCoffee() // Transition to ServingCoffee
        CoffeeMachine.makeCoffee() // Attempt to make coffee again
        assertTrue(CoffeeMachine.currentState is CoffeeMachineState.ServingCoffee)
    }

    @Test
    fun clean_shouldResetStateToIdle() {
        CoffeeMachine.makeCoffee() // Transition to ServingCoffee
        CoffeeMachine.clean()
        assertTrue(CoffeeMachine.currentState is CoffeeMachineState.Idle)
    }

    @Test
    fun makeCoffee_whenError_shouldNotChangeState() {
        CoffeeMachine.currentState = CoffeeMachineState.Error("Test error")
        CoffeeMachine.makeCoffee()
        assertTrue(CoffeeMachine.currentState is CoffeeMachineState.Error)
    }
}
*/