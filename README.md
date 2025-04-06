countryapplication
This project is a Spring Boot application secured using JWT (JSON Web Token) for authentication and authorization.

In this Key Features are
User Registration & Login
JWT Token Generation on Successful Login
Stateless Authentication using JWT
How i Implement this project
1.User Authentication Users register with a email and password. Upon login, a JWT token is generated and returned in the response.

JWT Token Handling The token is sent in the header (Authorization: Bearer ) with each request to access secured endpoints.

Spring Security Filters Custom filters validate the JWT token and set authentication in the SecurityContext.

and letter while we want to access any country endpoints or apis we need to provide the authorization. for that the token which is generated on user login we have to use this token in authorization for accessing the country apis.

