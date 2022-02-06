package pl.kodujmy.accountbalance.domain.model

import java.math.BigDecimal
import java.util.*

data class Money(val value: BigDecimal, val currency: Currency) {
    fun exchange(cer: CurrencyExchangeRate): Money {
        check(cer.from == currency) { "Exchange rate for different currency. Expecting $currency but received ${cer.from} instead" }
        return Money(this.value.multiply(cer.exchangeRate), cer.to)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Money

        if (currency != other.currency) return false
        if (value.compareTo(other.value) != 0) return false

        return true
    }

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + currency.hashCode()
        return result
    }


}

fun currency(symbol: String): Currency = Currency.getInstance(symbol)