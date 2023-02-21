package exceptions

class NoBalanceForPaymentException(message: String = "Saldo indisponível para esta transação") :
    RuntimeException(message)