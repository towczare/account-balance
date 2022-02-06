package pl.kodujmy.accountbalance.adapters.balanceprovider

import pl.kodujmy.accountbalance.adapters.balanceprovider.repository.AccountRepository
import pl.kodujmy.accountbalance.domain.model.AccountId
import pl.kodujmy.accountbalance.domain.model.Money
import pl.kodujmy.accountbalance.domain.model.currency
import pl.kodujmy.accountbalance.domain.ports.AccountBalanceProvider

class DatabaseAccountBalanceProvider(
        private val accountRepository: AccountRepository
    ) : AccountBalanceProvider {

    override fun readBalance(accountId: AccountId): Money {
        val account = accountRepository.findByAccountId(accountId.value())
        return Money(account.balance, currency(account.currency))
    }
}