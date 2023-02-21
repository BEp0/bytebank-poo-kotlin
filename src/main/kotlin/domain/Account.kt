package domain

import exceptions.NoCashForPaymentException
import java.math.BigDecimal
import java.util.UUID

data class Account(
    val id: UUID = UUID.randomUUID(),
    val name: String,
) {
    var cash: BigDecimal = BigDecimal.TEN
        private set(value) {
            if (value < BigDecimal.ZERO) throw NoCashForPaymentException()
            field = value
        }
    fun pay(value: BigDecimal, accountToTransfer: Account) {

        subtractMoney(value)
        accountToTransfer.addMoney(value)

        println("Transferência da conta de $name (id: $id) para ${accountToTransfer.name} (id: ${accountToTransfer.id}) com o valor de $value")
    }

    fun withdraw(value: BigDecimal) = subtractMoney(value)
    fun cashDeposit(value: BigDecimal) = addMoney(value)

    private fun addMoney(value: BigDecimal) {
        cash = cash.add(value)
    }

    private fun subtractMoney(value: BigDecimal) {
        cash = cash.subtract(value)
    }
}

