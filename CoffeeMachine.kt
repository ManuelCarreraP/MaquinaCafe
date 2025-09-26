package PrimerTrimestre.MaquinaCafe

/**
 * Singleton que representa la máquina de café.
 */
object CoffeeMachine {

    private var currentState: CoffeeMachineState = CoffeeMachineState.Idle

    // Productos disponibles y sus precios
    private val products = mapOf(
        "Nescafé" to 1.5,
        "Expresso" to 2.0,
        "Cappuccino" to 2.5
    )

    private var selectedProduct: String? = null
    private var insertedMoney: Double = 0.0

    /** Seleccionar un producto */
    fun selectProduct(product: String) {
        if (!products.containsKey(product)) {
            println("Producto no disponible.")
            return
        }
        selectedProduct = product
        println("Has seleccionado $product. Precio: ${products[product]} €")
    }

    /** Introducir dinero */
    fun insertMoney(amount: Double) {
        if (selectedProduct == null) {
            println("Primero selecciona un producto.")
            return
        }
        insertedMoney += amount
        val price = products[selectedProduct]!!
        println("Has insertado $amount €. Total insertado: $insertedMoney €")
        if (insertedMoney >= price) {
            println("Dinero suficiente. Preparando café...")
            makeCoffee()
            insertedMoney -= price // Retira el dinero usado
            selectedProduct = null // Reinicia selección
        } else {
            println("Faltan ${price - insertedMoney} € para completar el pago.")
        }
    }

    /** Preparar café según estado actual */
    private fun makeCoffee() {
        when (currentState) {
            is CoffeeMachineState.Idle -> {
                currentState = CoffeeMachineState.MakingCoffee
                Thread.sleep(2000)
                val product = selectedProduct ?: "Nescafé"
                val price = products[product] ?: 1.5
                currentState = CoffeeMachineState.ServingCoffee(product, price)
                println("¡Café listo! ${currentState}")
            }
            is CoffeeMachineState.MakingCoffee -> println("¡Espera! La máquina ya está haciendo café.")
            is CoffeeMachineState.ServingCoffee -> println("Ya hay café servido. Por favor, toma tu café.")
            is CoffeeMachineState.Error -> println("Error: ${(currentState as CoffeeMachineState.Error).message}")
        }
    }

    /** Limpiar máquina */
    fun clean() {
        println("Limpiando la máquina...")
        Thread.sleep(1000)
        currentState = CoffeeMachineState.Idle
        println("Máquina limpia. Estado: $currentState")
    }

    /** Provocar un error */
    fun triggerError(message: String) {
        currentState = CoffeeMachineState.Error(message)
        println("Se ha generado un error: $message")
    }

    /** Método para tests: obtener estado actual */
    internal fun getCurrentState(): CoffeeMachineState = currentState
}