package pl.kodujmy.accountbalance.adapters.exchangerateprovider

import org.junit.jupiter.api.Test
import pl.kodujmy.accountbalance.domain.model.CurrencyExchangeRate
import pl.kodujmy.accountbalance.domain.model.currency
import pl.kodujmy.accountbalance.domain.ports.ExchangeRateType
import java.time.LocalDate
import org.assertj.core.api.Assertions.*


class NbpExchangeRateTest {

    @Test
    fun `Should generate correct BID CurrencyExchangeRate from NbpExchangeRate`() {
        val nbpExchangeRate =
            NbpExchangeRate("USD", listOf(Rate("1", LocalDate.now(), 4.0122.toBigDecimal(), 4.1012.toBigDecimal())))

        val currencyExchangeRate = nbpExchangeRate.toCurrencyExchangeRate(ExchangeRateType.BID)

        assertThat(currencyExchangeRate).isEqualTo(CurrencyExchangeRate(currency("USD"), currency("PLN"), 4.0122.toBigDecimal()))
    }

    @Test
    fun `Should generate correct ASK CurrencyExchangeRate from NbpExchangeRate`() {
        val nbpExchangeRate =
            NbpExchangeRate("USD", listOf(Rate("1", LocalDate.now(), 4.0122.toBigDecimal(), 4.1012.toBigDecimal())))

        val currencyExchangeRate = nbpExchangeRate.toCurrencyExchangeRate(ExchangeRateType.ASK)

        assertThat(currencyExchangeRate).isEqualTo(CurrencyExchangeRate(currency("PLN"), currency("USD"), 0.2438.toBigDecimal()))
    }
}