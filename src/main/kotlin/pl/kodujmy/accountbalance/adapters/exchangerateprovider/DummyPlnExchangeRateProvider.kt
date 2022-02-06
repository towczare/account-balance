package pl.kodujmy.accountbalance.adapters.exchangerateprovider

import pl.kodujmy.accountbalance.domain.model.CurrencyExchangeRate
import pl.kodujmy.accountbalance.domain.model.currency
import pl.kodujmy.accountbalance.domain.ports.ExchangeRateType
import pl.kodujmy.accountbalance.domain.ports.PlnExchangeRateProvider
import java.math.BigDecimal
import java.util.*

class DummyPlnExchangeRateProvider : PlnExchangeRateProvider {

    companion object {
        var rates = listOf(
            CurrencyExchangeRate(currency("USD"), currency("PLN"), 4.0.toBigDecimal()),
            CurrencyExchangeRate(currency("EUR"), currency("PLN"), 4.54.toBigDecimal()),
            CurrencyExchangeRate(currency("PLN"), currency("USD"), 0.2247.toBigDecimal()),
            CurrencyExchangeRate(currency("PLN"), currency("EUR"), 0.25.toBigDecimal()),
        )
    }

    override fun getRate(currency: Currency, exchangeType: ExchangeRateType): CurrencyExchangeRate =
        when(exchangeType) {
            ExchangeRateType.BID -> rates.filter { it.from == currency }.first()
            ExchangeRateType.ASK -> rates.filter { it.to == currency }.first()
        }

}