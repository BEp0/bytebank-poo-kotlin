package exceptions

class NoCashForPaymentException(message: String = "Saldo indisponível para esta transação") :
    RuntimeException(message)