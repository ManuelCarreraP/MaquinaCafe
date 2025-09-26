package PrimerTrimestre.MaquinaCafe

fun main() {
    println("--- Seleccionando producto ---")
    CoffeeMachine.selectProduct("Expresso")

    println("\n--- Insertando dinero ---")
    CoffeeMachine.insertMoney(1.0)
    CoffeeMachine.insertMoney(1.0) // Suficiente para el café

    println("\n--- Intentando hacer café sin seleccionar ---")
    CoffeeMachine.insertMoney(2.0) // Debe indicar que no hay producto seleccionado

    println("\n--- Limpiando máquina ---")
    CoffeeMachine.clean()
}
