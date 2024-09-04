package com.example.modulo_05

class CuentaBancaria(val nombreCuenta: String, var saldoDisponible: Double) {
    private val historial = mutableListOf<String>()

    fun deposito(monto: Double) {
        saldoDisponible += monto
        historial.add("$nombreCuenta depositó $$monto")
    }

    fun retiro(monto: Double) {
        if (monto <= saldoDisponible) {
            saldoDisponible -= monto
            historial.add("$nombreCuenta retiró $$monto")
        } else {
            historial.add("$nombreCuenta intentó retirar $$monto pero no tenía suficiente saldo")
        }
    }

    fun mostrarSaldo() {
        println("$nombreCuenta. Su saldo disponible es de: $$saldoDisponible")
    }

    fun mostrarHistorial() {
        println("Historial de Transacciones - $nombreCuenta")
        historial.forEach { println(it) }
    }
}