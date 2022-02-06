package pl.kodujmy.accountbalance.adapters.balanceprovider.repository

import org.springframework.data.annotation.Id
import java.math.BigDecimal

data class Account(
    @Id val id: String,
    val accountId: String,
    val balance: BigDecimal,
    val currency: String
)
