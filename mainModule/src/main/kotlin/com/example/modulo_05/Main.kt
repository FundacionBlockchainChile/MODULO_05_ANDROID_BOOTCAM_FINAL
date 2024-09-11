package com.example.modulo_05

fun main() {
    val cuenta1 = CuentaBancaria("Lorena", 1000.0)
    val cuenta2 = CuentaBancaria("Carlos", 500.0)
    val cuenta3 = CuentaBancaria("Ana", 1500.0)

    // Transacciones para Lorena
    cuenta1.deposito(200.0)
    cuenta1.retiro(1200.0)
    cuenta1.deposito(3000.0)
    cuenta1.deposito(500.0)
    cuenta1.retiro(3606.6)

    // Transacciones para Carlos
    cuenta2.deposito(100.0)
    cuenta2.retiro(50.0)
    cuenta2.deposito(200.0)
    cuenta2.retiro(700.0) // Retiro fallido
    cuenta2.deposito(300.0)

    // Transacciones para Ana
    cuenta3.deposito(500.0)
    cuenta3.retiro(1000.0)
    cuenta3.deposito(200.0)
    cuenta3.retiro(300.0)
    cuenta3.deposito(100.0)

    // Mostrar historial y saldo de cada cuenta
    cuenta1.mostrarHistorial()
    cuenta1.mostrarSaldo()

    cuenta2.mostrarHistorial()
    cuenta2.mostrarSaldo()

    cuenta3.mostrarHistorial()
    cuenta3.mostrarSaldo()
}