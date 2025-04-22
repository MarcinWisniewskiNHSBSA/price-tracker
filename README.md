# Band 6 Developer - Assignment - Product Price Tracker with Custom Alerts

An API to track prices and send scheduled alerts.

## Project setup
### Prerequisites 
Before cloning the project please ensure you have the following prerequisites:

**Required:**
- Java 21
- Postman to send API requests

## Running the app

- Run the command in your console to clone the repo:
```
git clone https://github.com/MarcinWisniewskiNHSBSA/price-tracker
```
- Once that done, open the repo's location and run the below:
```
mvn spring-boot:run
```
then you should be able to see the following output: 

```
Started Band6assignmentApplication in 1.576 seconds (process running for 1.951)
```

- In case something goes wrong, try to run the below commands and then try the second command again: 
```
mvn clean install
mvn dependency:resolve
```

## Examples of API requests and their responses
### Sending a request through Postman to track a new price and schedule a new alert (first request example)

![image](https://github.com/user-attachments/assets/f2581a46-75b4-40ad-b45b-55b3bcbedc5c)

- Open Postman
- Add a new tab
- Change HTTP request to POST
- Paste the following URL
  
```
http://localhost:8080/track/set-alert
```
- Under the URL, click on Body -> raw -> JSON and paste the below request body:
```
{
"url": "https://example.com/product/1",
"frequency": "MORNING",
"expectedPrice": 150.00
}
```

- Postman should then return the below response from the API:

```
{
"id": 2,
"name": "Product 1",
"url": "https://example.com/product/1",
"expectedPrice": 150.00,
"currentPrice": 200.00,
"message": "A new alert for Product 1 has been added. You will be notified when the current price £200.00 will drop to £150.00"
}
```

In this example the alert gets scheduled straight away based on the passed frequency. For the testing purpose, the MORNING frequency is set to 10 seconds which means that after creating this new alert the API will send a request to a different endpoint

```
http://localhost:8080/track/check-price
```
to check if the current price has dropped. If so, a new alert will be send: "Product 1 now has a new price of £100.00"  

### Second request 

```
{
"url": "https://example.com/product/2",
"frequency": "MORNING",
"expectedPrice": 150.00
}
```
and its response
```
{
"id": 4,
"name": "Product 2",
"url": "https://example.com/product/2",
"expectedPrice": 150.00,
"currentPrice": 100.00,
"message": "Your alert has not been created as the expected price £150.00 is higher or equal than the current price £100.00"
}
```

In this case a new alert will not be created as the expected price is higher than the product's current price so this price will not be tracked. 

### Third request 
```
{
"url": "https://example.com/product/3",
"frequency": "MORNING",
"expectedPrice": 150.00
}
```
and its response

```
{
"id": 5,
"name": "Product 3",
"url": "https://example.com/product/3",
"expectedPrice": 150.00,
"currentPrice": 300.00,
"message": "A new alert for Product 3 has been added. You will be notified when the current price £300.00 will drop to £150.00"
}
```
In this case the price will be tracked and the API will return the following alert: "Product 3 has not dropped in price"


