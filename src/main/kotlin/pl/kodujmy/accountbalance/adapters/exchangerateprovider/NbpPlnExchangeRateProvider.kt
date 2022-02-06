package pl.kodujmy.accountbalance.adapters.exchangerateprovider


import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import pl.kodujmy.accountbalance.domain.model.CurrencyExchangeRate
import pl.kodujmy.accountbalance.domain.ports.ExchangeRateType
import pl.kodujmy.accountbalance.domain.ports.PlnExchangeRateProvider

import java.util.*

class NbpPlnExchangeRateProvider(
    private val restTemplate: RestTemplate
) : PlnExchangeRateProvider {

    override fun getRate(currency: Currency, exchangeType: ExchangeRateType): CurrencyExchangeRate {
        val nbpResponse =
            restTemplate.getForObject<NbpExchangeRate>("http://api.nbp.pl/api/exchangerates/rates/C/USD?format=json")

        return nbpResponse.toCurrencyExchangeRate(exchangeType)
    }




}