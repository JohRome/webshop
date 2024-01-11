## Tempgroup WebShop Project
##### by Johan Romeo, Jonas Torjman and Sandra Jeppsson

### Task, endpoints and structure
#### Task: Create a webshop where you can:
- register as either an admin or a customer.
- login as either an admin or a customer
- see all the existing products in the webshop without being logged in at all
- add, update and delete products in the webshop logged in as an admin, but not as a customer
- be able to add to, update and delete products from the personalized shopping cart as a customer
- be able to see all the products in the cart, check out the cart and receive a receipt

#### Endpoints:
- http://localhost:8080/admin/ - GET - gets the information about the logged in admin
- http://localhost:8080/customer/ - GET - gets the information about the logged in customer
- http://localhost:8080/auth/register - POST - here you register your username and password
- http://localhost:8080/auth/login - POST - here you enter your credentials and get logged in if you have registered correctly
- http://localhost:8080/products/admin - POST - adds a product to the webshop-database
- http://localhost:8080/products/ - GET - see all the existing produts in the webshop
- http://localhost:8080/products/{id} - GET - finds the product that matches the "{id}" and adds it to the cart
- http://localhost:8080/products/{id} - PUT - updates the product in the webshop-database that matches the "{id}"
- http://localhost:8080/products/{id} - DELETE - deletes the product in the webshop-database that matches the "{id}"
- http://localhost:8080/cart/{id} - POST - adds the product that matches with the "{id}" to the customer's cart
- http://localhost:8080/cart/ - GET - see all the existing products in the customer cart
- http://localhost:8080/cart/{id} - DELETE - deletes the product that matches the "{id}" from the customer's cart
- http://localhost:8080/cart - DELETE - checks out the customer cart with a receipt and total amount before removal

#### Structure: We chose to:
- create one project with the backend coding for webshop
    - where we also chose to separate the security implementation and the webshop in different packages
- create another project with the frontend coding for the webshop-client


### How to start the application:
- Download a Java supported IDEA of choice, for an example the free "IntelliJ IDEA Community Edition":
  ***https://www.jetbrains.com/idea/download/?section=windows***
- Go to Github and download our WebShop-project:
  ***https://github.com/JohRome/webshop/tree/master***
- Open this in IntelliJ
- Go to Github and download our Webshop-client-project:
  ***https://github.com/JohRome/client-webshop***
- Open this project in another IntelliJ window.
- Start the first project by pushing "run" in the main-class
- Then start the second project by pushing "run" in that main-class
- Then please follow the project instructions and enjoy our webshop!

### Dependencies
- org.springframework.boot:spring-boot-starter-security
- org.springframework.boot:spring-boot-starter-data-jpa
- org.springframework.boot:spring-boot-starter-web
- org.springframework.boot:spring-boot-devtools
- org.springframework.boot:spring-boot-starter-test
- org.springframework.boot:spring-security-test
- io.json.webtoken:jjwt-impl:0.11.5
- io.json.webtoken:jjwt-api:0.11.5
- io.json.webtoken:jjwt-jackson:0.11.5
- org.projectlombok:lombok
- com.mysql:mysql-connector-j

***Java 17***

***December 2023 to January 2024***
