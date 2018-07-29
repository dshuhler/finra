####FINRA Programming Challenge

- To run:  `mvn spring-boot:run` will start the spring boot server on localhost:8080
- You will need a SMTP server (I used FakeSMTP) or it will fail when sending emails.
- To submit an order, send a `POST` to /order.
    - example CURL command: `curl -d '{"userId":"joe_friday", "creditCardNumber":"123-456-7890", "productId":"A-1"}' -H "Content-Type: application/json" -X POST http://localhost:8080/order`