package pl.kodujmy.accountbalance.config

import java.time.LocalDateTime

data class ErrorDetails(val date: LocalDateTime, val msg: String, val exceptionStack: String)