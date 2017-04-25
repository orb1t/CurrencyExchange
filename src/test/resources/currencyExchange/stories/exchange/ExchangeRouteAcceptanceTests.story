Meta: test meta


Narrative:
Acceptance Tests Suit, for route '/api/v1/exchange/'




Scenario: when a Admin makes POST request to '/api/v1/exchange/,
with Valid request format,
he would receive a New Currency's details

Given Client's role is Admin
And service DB is in initial state
And request params is {"currencyFromId":"1","currencyToId":"2","amount":"100"}
When Client makes POST request to route '/api/v1/exchange/'
Then the response status code should be 200
!-- And response body should comply with Currency schema
