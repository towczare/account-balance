package pl.kodujmy.accountbalance.adapters.exchangerateprovider

import pl.kodujmy.accountbalance.domain.model.CurrencyExchangeRate
import pl.kodujmy.accountbalance.domain.model.currency
import pl.kodujmy.accountbalance.domain.ports.ExchangeRateType
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import java.time.LocalDate

data class NbpExchangeRate(val code: String, val rates: List<Rate>) {
    fun toCurrencyExchangeRate(exchangeType: ExchangeRateType): CurrencyExchangeRate =
        when(exchangeType) {
            ExchangeRateType.BID -> CurrencyExchangeRate(currency(code), currency("PLN"), rates.first().bid)
            ExchangeRateType.ASK ->  CurrencyExchangeRate(currency("PLN"), currency(code), reverseRateUsingBankersRounding(rates.first().ask))
        }

    private fun reverseRateUsingBankersRounding(value: BigDecimal): BigDecimal
            = BigDecimal.ONE.divide(value, MathContext(4, RoundingMode.HALF_EVEN))
}

data class Rate(val no: String, val effectiveDate: LocalDate, val bid: BigDecimal, val ask: BigDecimal)