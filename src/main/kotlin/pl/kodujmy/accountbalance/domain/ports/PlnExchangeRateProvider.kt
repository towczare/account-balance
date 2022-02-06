package pl.kodujmy.accountbalance.domain.ports

import pl.kodujmy.accountbalance.domain.model.CurrencyExchangeRate
import java.util.*

interface PlnExchangeRateProvider {

    fun getRate(currency: Currency, exchangeType: ExchangeRateType): CurrencyExchangeRate
}

enum class ExchangeRateType {
    BID, ASK
}