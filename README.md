# exchange-rate
Exchange Rate with Open Exchange

This application is consuming, open exchange restful service to access the real time exchange rates. Since free account allows to query only based on the base currency as US Dollars, all exchange rates are produced only with reference to   USD only.
In addition to that, search results are stored in memory database H2 to avoid too many external web service provider if data is already available.

