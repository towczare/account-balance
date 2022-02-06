package pl.kodujmy.accountbalance.adapters.balanceprovider

import pl.kodujmy.accountbalance.domain.model.AccountId
import pl.kodujmy.accountbalance.domain.model.Money
import pl.kodujmy.accountbalance.domain.model.currency
import pl.kodujmy.accountbalance.domain.ports.AccountBalanceProvider
import java.math.BigDecimal

class DummyAccountBalanceProvider : AccountBalanceProvider {

    companion object {
        var accounts: Map<AccountId, Money> = mapOf(
            Pair(AccountId("1"), Money(100.toBigDecimal(), currency("PLN"))),
            Pair(AccountId("2"), Money(0.01.toBigDecimal(), currency("PLN"))),
            Pair(AccountId("3"), Money(200.toBigDecimal(), currency("PLN")))
        )

    }

    override fun readBalance(accountId: AccountId): Money = accounts.getValue(accountId)

}