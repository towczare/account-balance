package pl.kodujmy.accountbalance.config

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import pl.kodujmy.accountbalance.adapters.balanceprovider.DatabaseAccountBalanceProvider
import pl.kodujmy.accountbalance.domain.ports.AccountBalanceProvider
import pl.kodujmy.accountbalance.adapters.balanceprovider.repository.AccountRepository
import pl.kodujmy.accountbalance.adapters.exchangerateprovider.NbpPlnExchangeRateProvider
import pl.kodujmy.accountbalance.domain.AccountBalanceChecker
import pl.kodujmy.accountbalance.domain.ports.PlnExchangeRateProvider


@Configuration
class AccountBalanceAppConfiguration {

    @Bean
    fun accountBalanceProvider(accountRepository: AccountRepository): AccountBalanceProvider =
        DatabaseAccountBalanceProvider(accountRepository)

    @Bean
    fun exchangeRateProvider(restTemplate: RestTemplate): PlnExchangeRateProvider = NbpPlnExchangeRateProvider(restTemplate)

    @Bean
    fun accountBalanceChecker(
        accountBalanceProvider: AccountBalanceProvider,
        plnExchangeRateProvider: PlnExchangeRateProvider
    )
            : AccountBalanceChecker = AccountBalanceChecker(accountBalanceProvider, plnExchangeRateProvider)


    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplateBuilder().build()
    }

//    var httpClient: HttpClient = HttpClient.create()
//        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
//        .responseTimeout(Duration.ofMillis(5000))
//        .doOnConnected { conn ->
//            conn.addHandlerLast(ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
//                .addHandlerLast(WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS))
//        }
//
//    @Bean
//    fun client() = WebClient.builder()
//        .clientConnector(ReactorClientHttpConnector(httpClient))
//        .baseUrl("http://api.nbp.pl/api/")
//        .build()
}