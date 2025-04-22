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
### Sending a request through Postman to track a new price and schedule a new alert

![image](https://github.com/user-attachments/assets/c004bb53-b708-4f70-960e-31cf0b12220c)

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







