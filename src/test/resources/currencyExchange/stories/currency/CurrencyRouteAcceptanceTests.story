Meta: test meta


Narrative:
Acceptance Tests Suit, for route '/api/v1/currencies/'


Scenario: when a User makes GET request to '/api/v1/currencies/',
he would receive a list of available Currencies

Given Client's role is User
And service DB is in initial state
When Client makes GET request to route '/api/v1/currencies/'
Then the response status code should be 200
And response body should comply with Currencies schema


Scenario: when a Admin makes GET request to '/api/v1/currencies/',
he would receive a list of available Currencies

Given Client's role is Admin
And service DB is in initial state
When Client makes GET request to route '/api/v1/currencies/'
Then the response status code should be 200
And response body should comply with Currencies schema


Scenario: when an UnAuthorized Client makes GET request to '/api/v1/currencies/',
he would receive 403 - issuer had no permission for operation

Given Client's role is UnAuthorized
And service DB is in initial state
When Client makes GET request to route '/api/v1/currencies/'
Then the response status code should be 403




Scenario: when a User makes GET request to '/api/v1/currencies/{id}',
he would receive a Currency details

Given Client's role is User
And service DB is in initial state
When Client makes GET request to route '/api/v1/currencies/1'
Then the response status code should be 200
And response body should comply with Currency schema


Scenario: when a Admin makes GET request to '/api/v1/currencies/{id}',
he would receive a Currency details

Given Client's role is Admin
And service DB is in initial state
When Client makes GET request to route '/api/v1/currencies/1'
Then the response status code should be 200
And response body should comply with Currency schema


Scenario: when an UnAuthorized Client makes GET request to '/api/v1/currencies/{id}',
he would receive 403 - issuer had no permission for operation

Given Client's role is UnAuthorized
And service DB is in initial state
When Client makes GET request to route '/api/v1/currencies/1'
Then the response status code should be 403




Scenario: when a Admin makes DELETE request to '/api/v1/currencies/{id}',
Currency with {id} - will be deleted from service DB

Given Client's role is Admin
And service DB is in initial state
When Client makes DELETE request to route '/api/v1/currencies/1'
Then the response status code should be 204
!-- Then the response status code should be 200

Scenario: when a User makes DELETE request to '/api/v1/currencies/{id}',
he would receive - 403 Forbidden

Given Client's role is User
And service DB is in initial state
When Client makes DELETE request to route '/api/v1/currencies/1'
Then the response status code should be 403


Scenario: when an UnAuthorized Client makes DELETE request to '/api/v1/currencies/{id}',
he would receive - 403 Forbidden

Given Client's role is UnAuthorized
And service DB is in initial state
When Client makes DELETE request to route '/api/v1/currencies/1'
Then the response status code should be 403




Scenario: when a Admin makes POST request to '/api/v1/currencies/',
with Valid request format,
he would receive a New Currency's details

Given Client's role is Admin
And service DB is in initial state
And request params is {"code":"US","country":"USA","value":"1.4"}
When Client makes POST request to route '/api/v1/currencies/'
Then the response status code should be 200
And response body should comply with Currency schema


Scenario: when a Admin makes POST request to '/api/v1/currencies/',
with InValid request format ( malformed JSON payload ),
he would receive - 400 bad request format

Given Client's role is Admin
And service DB is in initial state
And request params is {"code":"US","country":"USA","value":"1.4"
When Client makes POST request to route '/api/v1/currencies/'
Then the response status code should be 400
!-- And response body should comply with Currency schema


Scenario: when a Admin makes POST request to '/api/v1/currencies/',
with InValid Currency Value ( zero ) in request,
he would receive - 422 Unprocessable Entity

Given Client's role is Admin
And service DB is in initial state
And request params is {"code":"US","country":"USA","value":"0"}
When Client makes POST request to route '/api/v1/currencies/'
Then the response status code should be 422
!-- And response body should comply with Currency schema


Scenario: when a User makes POST request to '/api/v1/currencies/',
he would receive - 403 Forbidden

Given Client's role is User
And service DB is in initial state
And request params is {"code":"US","country":"USA","value":"0"}
When Client makes POST request to route '/api/v1/currencies/'
Then the response status code should be 403
!-- And response body should comply with Currency schema


Scenario: when an UnAuthorized Client makes POST request to '/api/v1/currencies/',
he would receive - 403 Forbidden

Given Client's role is UnAuthorized
And service DB is in initial state
And request params is {"code":"US","country":"USA","value":"0"}
When Client makes POST request to route '/api/v1/currencies/'
Then the response status code should be 403
!-- And response body should comply with Currency schema
