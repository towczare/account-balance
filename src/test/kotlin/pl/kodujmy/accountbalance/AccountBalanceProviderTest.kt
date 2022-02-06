package pl.kodujmy.accountbalance

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import pl.kodujmy.accountbalance.domain.model.AccountId
import pl.kodujmy.accountbalance.adapters.balanceprovider.DummyAccountBalanceProvider

class AccountBalanceProviderTest {

    val accountBalanceProvider = DummyAccountBalanceProvider()

    @Test
    fun `Should return non empty Money when account exists`() {
        assertThat(accountBalanceProvider.readBalance(AccountId("2"))).isNotNull

    }

    @Test
    fun `When no account found should raise NoSuchElementException`() {
        assertThrows<NoSuchElementException> { accountBalanceProvider.readBalance(AccountId("8")) }
    }

}