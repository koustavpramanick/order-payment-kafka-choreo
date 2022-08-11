# order-payment-kafka-choreo
A basic Order payment API using kafka producer and consumer in springboot implementing on basic choreography pattern


##Prerequisites
1. Run zookeeper on default port (2181)
2. Run Kafka configured on localhot:29092
3. Run MySQL server and provide specefic properties in application.properties file

##Problem Statement
1.	Create a producer class as an order service
2.	Create a consumer class as a payment service
3.	Use any DB for storing the order details
4.	Once an Order is placed, the payment should happen automatically and stored in DB. Can be checked by keeping Payment Status first as Pending when order id Created and when Payment is done, the Payment status should be changed Completed
5.	Create a REST controller to place and get the order details.

##Request Bodies
1. Place Order
{
    "orderName": "Macbook Air",
    "price": 120000,
    "quantity": 1
}

