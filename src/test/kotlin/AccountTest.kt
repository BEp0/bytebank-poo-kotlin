import domain.Account
import exceptions.NoCashForPaymentException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
import java.util.UUID

class AccountTest {

    private val defaultId = UUID.randomUUID()
    private val defaultName = "Bernardo"
    private val defaultCash = BigDecimal.TEN
    private val bernardoAccount= Account(defaultId, defaultName)
    private val defaultErrorMessageNoCashForPayment = "Saldo indisponível para esta transação"

    @Test
    fun `must create an account correctly`() {

        assertEquals(defaultId, bernardoAccount.id)
        assertEquals(defaultName, bernardoAccount.name)
        assertEquals(defaultCash, bernardoAccount.cash)
    }

    @Test
    fun `must transfer correct value to another account`() {

        val ernaniId = UUID.randomUUID()
        val ernaniName = "Ernani"
        val ernaniCash = BigDecimal.valueOf(100000)

        val valueToPay = BigDecimal.valueOf(5000)
        val valueCashBernardoExpect = BigDecimal.valueOf(5010)
        val valueCashErnaniExpect = BigDecimal.valueOf(95010)

        val ernaniAccount = Account(ernaniId, ernaniName)
        ernaniAccount.cashDeposit(ernaniCash)

        ernaniAccount.pay(valueToPay, bernardoAccount)

        assertEquals(valueCashErnaniExpect, ernaniAccount.cash)
        assertEquals(valueCashBernardoExpect, bernardoAccount.cash)
    }

    @Test
    fun `must return a exception when try to transfer negative value to another account`() {

        val ernaniId = UUID.randomUUID()
        val ernaniName = "Ernani"

        val valueToPay = BigDecimal.valueOf(5000)

        val ernaniAccount = Account(name = ernaniName, id = ernaniId)

        val exception = assertThrows<NoCashForPaymentException> { ernaniAccount.pay(valueToPay, bernardoAccount) }

        assertEquals(defaultErrorMessageNoCashForPayment, exception.message)
        assertEquals(defaultCash, bernardoAccount.cash)
        assertEquals(defaultCash, ernaniAccount.cash)
    }

    @Test
    fun `must withdraw money when cash is enough to withdraw`() {

        val valueToWithdraw = BigDecimal.ONE
        val valueExpected = BigDecimal.valueOf(9)
        bernardoAccount.withdraw(valueToWithdraw)

        assertEquals(valueExpected, bernardoAccount.cash)
    }
    @Test
    fun `should return a exception when when cash isn't enough to withdraw`() {

        val valueToWithdraw = BigDecimal.TEN
        bernardoAccount.withdraw(valueToWithdraw)
        
        val exception = assertThrows<NoCashForPaymentException> { bernardoAccount.withdraw(valueToWithdraw) }
        
        assertEquals(defaultErrorMessageNoCashForPayment, exception.message)
        assertEquals(BigDecimal.ZERO, bernardoAccount.cash)
    }
}