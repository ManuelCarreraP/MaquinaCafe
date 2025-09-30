package PrimerTrimestre.MaquinaCafe

fun main() {
    println("Probando la máquina de café")
    println()

    // Prueba 1
    println("1. Hacer café normal:")
    CoffeeMachine.hacerCafe()

    Thread.sleep(1000) // espera entre operaciones

    // Prueba 2 - debería fallar
    println("\n2. Intentar hacer otro café:")
    CoffeeMachine.hacerCafe()

    Thread.sleep(1000)

    // Prueba 3 - limpiar
    println("\n3. Limpiar máquina:")
    CoffeeMachine.limpiarMaquina()

    // Prueba 5 - error
    println("\n5. Simular error:")
    CoffeeMachine.reportarError()

    println("\n6. Limpiar después del error:")
    CoffeeMachine.limpiarMaquina()

    println("\n--- Fin de la prueba ---")
}
