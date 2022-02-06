package pl.kodujmy.accountbalance.domain.model

import java.math.BigDecimal
import java.util.*

data class CurrencyExchangeRate(val from: Currency, val to: Currency, val exchangeRate: BigDecimal)