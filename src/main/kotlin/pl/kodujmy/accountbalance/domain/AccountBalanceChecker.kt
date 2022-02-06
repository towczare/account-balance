package pl.kodujmy.accountbalance.domain

import pl.kodujmy.accountbalance.domain.ports.AccountBalanceProvider
import pl.kodujmy.accountbalance.domain.model.AccountId
import pl.kodujmy.accountbalance.domain.ports.PlnExchangeRateProvider
import pl.kodujmy.accountbalance.domain.model.Money
import pl.kodujmy.accountbalance.domain.ports.ExchangeRateType
import java.util.*

class AccountBalanceChecker (
    private val accountBalanceProvider: AccountBalanceProvider,
    private val plnExchangeRateProvider: PlnExchangeRateProvider
) {
    fun checkBalance(accountId: AccountId, expectedCurrency: Currency): Money {
        val balance = accountBalanceProvider.readBalance(accountId)
        val plnToUsdExchangeRate = plnExchangeRateProvider.getRate(expectedCurrency, ExchangeRateType.ASK)
        return balance.exchange(plnToUsdExchangeRate)
    }
}