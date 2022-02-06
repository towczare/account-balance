package pl.kodujmy.accountbalance.domain.model


data class AccountBalance(val accountId: AccountId, val balance: Money)

@JvmInline
value class AccountId(private val accountId: String) {
    fun value(): String = accountId
}