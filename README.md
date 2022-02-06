# Account Balance

This simple REST API service is responsible for serving information about account balance in USD currency based on two information:
  * account balance information received from in memory H2 database,
  * current exchange rate received from [http://api.nbp.pl/](http://api.nbp.pl/)

## API Reference

#### Show balance in USD currency

```http
  GET /accounts/{accountId}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `accountId` | `string` | **Required**. Id of account |

## Couple project notes

I really enjoyed this exercise and had quite fun developing it. Thanks to that I learned for example what [Bankers Rounding](https://squareup.com/help/us/en/article/5092-rounding) term is. For people working in finance domain it might be obvious, however for someone like me, working in totally different domain this is something new.

### Assumptions

- I omitted security layer of this service for sake of simplicity
- Because of this, with full responsibility I'm exposing some 'sensitive credentials' to database and web console access. 
- I strictly focused on received requirements not developing any additional features, however there are some parts which might be overkill for this exercise. 
  
### Architecture Decision Record

1. At the beginning I was considering usage of **Java Money** ([JSR 354](https://jcp.org/en/jsr/detail?id=354)) but decided this will be overkill for this stage of project. In the end I created simple data class based on BigDecimal and Currency class.
2. I applied parts of **hexagonal architecture** to this project, moving I/O operations to **adapters** package and leaving core classes is **domain** package. 
3. I decided to use [Spring Data JDBC](https://spring.io/projects/spring-data-jdbc) as lightweight alternative to regular Spring DATA. Even though for this scale of project, it doesn't make much difference it might play way better in the future. Some Kotlin alternative solutions could be also worth investigation like [Exposed](https://github.com/JetBrains/Exposed) or [JOOQ](https://www.jooq.org/doc/latest/manual/getting-started/jooq-and-kotlin/)
4. At this stage of project, decided to use simple **Spring MVC** stack instead **Webflux**. Knowing more about nonfunctional requirements could probably change that decision. 
5. For maintaining schema database I picked [Flyway](https://flywaydb.org/) Simple yet, powerful solution tracking database schema changes together with code.
6. For error handling I used ControllerAdvice feature of Spring Framework allowing to map in easy way Exceptions to proper HTTP status responses.

### Potential improvements

- Potential improvement in the code could be applying reactive programming based on Coroutines and Spring WebFlux. This could potentially increase throughput and execute some parts of code simultaneously. 
- Another potential improvement could be storing or caching external calls to NBP service as the exchange rate doesn't change so often. Current approach of making call each time we look for information might be suboptimal.
- Developing some integration tests testing overall solution could be another good step. 
- Depending on usage of following API, thinking of API contract testing could be also a valid approach.
- Next time I could show some progress in git repository by making commits smaller  