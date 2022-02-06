package pl.kodujmy.accountbalance.adapters.balanceprovider.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : CrudRepository<Account, Int> {
    fun findByAccountId(accountId: String): Account
}