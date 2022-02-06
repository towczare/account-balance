package pl.kodujmy.accountbalance.domain.ports

import pl.kodujmy.accountbalance.domain.model.AccountId
import pl.kodujmy.accountbalance.domain.model.Money

interface AccountBalanceProvider {

    fun readBalance(accountId: AccountId): Money
}