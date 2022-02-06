package pl.kodujmy.accountbalance.adapters.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.kodujmy.accountbalance.domain.AccountBalanceChecker
import pl.kodujmy.accountbalance.domain.model.AccountId
import pl.kodujmy.accountbalance.domain.model.Money
import pl.kodujmy.accountbalance.domain.model.currency


@RestController
@RequestMapping("/accounts")
class AccountBalanceController(val accountBalanceChecker: AccountBalanceChecker) {

    @GetMapping("/{accountId}")
    fun readBalance(@PathVariable accountId: AccountId): ResponseEntity<Money> {
        return ResponseEntity.ok(accountBalanceChecker.checkBalance(accountId, currency("USD")))
    }
}



