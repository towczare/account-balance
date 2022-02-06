package pl.kodujmy.accountbalance

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import pl.kodujmy.accountbalance.domain.model.CurrencyExchangeRate
import pl.kodujmy.accountbalance.domain.model.Money
import pl.kodujmy.accountbalance.domain.model.currency
import org.assertj.core.api.Assertions.*

class MoneyTest {

    @Test
    fun `Should fail fast when wrong currency in exchange rate during exchange`() {
        assertThrows<IllegalStateException> { Money(100.0.toBigDecimal(), currency("PLN"))
            .exchange(CurrencyExchangeRate(currency("EUR"), currency("USD"), 1.14.toBigDecimal())) }
    }

    @Test
    fun `Should exchange 100 EUR to 400 PLN when exchange rate is 5`() {
        assertThat(
            Money(100.0.toBigDecimal(), currency("EUR"))
                .exchange(CurrencyExchangeRate(currency("EUR"), currency("PLN"), 5.0.toBigDecimal()))
        ).isEqualTo(
            Money(500.toBigDecimal(), currency("PLN")))
    }

    @Test
    fun `Should exchange 100 PLN to 25 EUR when exchange rate is 0_25`() {
        assertThat(
            Money(100.0.toBigDecimal(), currency("PLN"))
                .exchange(CurrencyExchangeRate(currency("PLN"), currency("EUR"), 0.25.toBigDecimal()))
        ).isEqualTo(
            Money(25.toBigDecimal(), currency("EUR")))
    }

    @Test
    fun `Should exchange 99_99 PLN to 24_9975 EUR when exchange rate is 0_25 without rounding`() {
        assertThat(
            Money(99.99.toBigDecimal(), currency("PLN"))
                .exchange(CurrencyExchangeRate(currency("PLN"), currency("EUR"), 0.25.toBigDecimal()))
        ).isEqualTo(
            Money(24.9975.toBigDecimal(), currency("EUR")))
    }
}