import exceptions.NoBalanceForPaymentException
import java.math.BigDecimal

data class Account(
    val id: String,
    val name: String,
    var balance: BigDecimal = BigDecimal.TEN,
) {

    fun pay(value: BigDecimal, accountToTransfer: Account) {

        if (this.balance <= value) throw NoBalanceForPaymentException()

        accountToTransfer.receiveMoney(value)
        takeMoney(value)

        println("Transferência da conta de ${this.name} (id: ${this.id}) para ${accountToTransfer.name} (id: ${accountToTransfer.id}) com o valor de ${value}")
    }

    fun receiveMoney(value: BigDecimal) {
        this.balance = this.balance.add(value)
    }

    private fun takeMoney(value: BigDecimal) {
        this.balance = this.balance.subtract(value)
    }
}

