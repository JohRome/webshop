## Tempgroup WebShop Project
##### by Johan Romeo, Jonas Torjman and Sandra Jeppsson

### Task, endpoints and structure
#### Task: Create a webshop where you can:
- register as a customer.
- login as either an admin or a customer
- see all the existing products in the webshop without being logged in at all
- add, update and delete products in the webshop logged in as an admin, but not as a customer
- be able to add to, update and delete products from the personalized shopping cart as a customer
- be able to see all the products in the basket, check out the basket and receive a receipt
- be able to see your shopping history as a customer
- be able to see all customers shopping histories as an admin

#### Endpoints:

- http://localhost:8080/auth/register - POST - here you register your username and password
- http://localhost:8080/auth/login - POST - here you enter your credentials and get logged in if you have registered correctly
- http://localhost:8080/webshop/products/admin - POST - adds a product to the webshop-database
- http://localhost:8080/webshop/products/ - GET - see all the existing produts in the webshop
- http://localhost:8080/webshop/products/{id} - GET - finds the product that matches the "{id}" and adds it to the cart
- http://localhost:8080/webshop/products/{id} - PUT - updates the product in the webshop-database that matches the "{id}"
- http://localhost:8080/webshop/products/{id} - DELETE - deletes the product in the webshop-database that matches the "{id}"
- http://localhost:8080/webshop/cart/{id} - POST - adds the product that matches with the "{id}" to the customer's cart. If you add the same product twice it updates the quantity instead
- http://localhost:8080/webshop/cart/ - GET - see all the existing products in the customer cart
- http://localhost:8080/webshop/cart/{id} - DELETE - deletes the product that matches the "{id}" from the customer's cart
- http://localhost:8080/webshop/checkout/ - DELETE - checks out the customer cart with a receipt and total amount before removal
- http://localhost:8080/webshop/history/customer - GET - customer can see the purchase history
- http://localhost:8080/webshop/history/admin - GET - admin can see all customers purchase histories

#### Structure: We chose to:
- create one project with the backend-coding for webshop
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
- Download [MySQL](https://dev.mysql.com/downloads/installer/) and follow the installation instructions
- Go to your MySQL Workbench and create a new schema called "webshop"
- Go to the application.properties and change the username and password to your MySQL username and password
- Open this project in another IntelliJ window.
- Start the first project by pushing "run" in the main-class
- Then start the second project by pushing "run" in that main-class
- Log in as an admin to add some items to the webshop, then please follow the project instructions and enjoy!

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