<body>
    <h1>LiquorLogic</h1>
    <p>LiquorLogic is a platform for managing a liquor store's inventory, sales, and user accounts. The project is divided into three microservices, all developed with Spring Boot: Inventory Service, POS Service, and User Service.</p>
    <h2>Microservices</h2>
    <h3>1) Inventory Service</h3>
    <ul>
        <li><strong>Description</strong>: Manages the inventory, including stock and suppliers.</li>
        <li><strong>Tech Stack</strong>: Java, Spring Boot</li>
    </ul>
    <h4>Endpoints:</h4>
   <h5>Stock: <code>/api/stock</code></h5>
    <ul>
        <li>Add new stock items or update stock items: <code>GET /api/stock/save</code></li>
        <li>Find stock items by stock id: <code>POST /api/stock/stockId</code></li>
    </ul>
    <h5>Supplier: <code>/api/supplier</code></h5>
    <ul>
        <li>Add new supplier or update supplier: <code>GET /api/supplier/save</code></li>
        <li>Find supplier by status, supplier id and contact: <code>POST /api/supplier/findSupplier</code></li>
        <li>Get all suppliers list: <code>POST /api/supplier/getAllSuppliersr</code></li>
    </ul>
    <h3>2) POS Service</h3>
    <ul>
        <li><strong>Description</strong>: Handles point of sale operations, including managing items and orders.</li>
        <li><strong>Tech Stack</strong>: Java, Spring Boot</li>
    </ul>
     <h4>Endpoints:</h4>
   <h5>Item: <code>/api/item</code></h5>
    <ul>
        <li>Add new items or update items: <code>GET /api/item/save</code></li>
        <li>Find items by item id: <code>POST /api/item/itemId</code></li>
        <li>Find items by supplier id: <code>POST /api/item/supplierId</code></li>
        <li>Find items by brand: <code>POST /api/item/brand</code></li>
        <li>Find items by unit price of item: <code>POST /api/item/unitPrice</code></li>
        <li>Find items by item name: <code>POST /api/item/name</code></li>
        <li>Find items by manufacture date: <code>POST /api/item/manufactureDate</code></li>
        <li>Find items by expire date: <code>POST /api/item/expireDate</code></li>
        <li>Find items by user id: <code>POST /api/item/userId</code></li>
    </ul>
    <h5>Order: <code>/api/order</code></h5>
    <ul>
        <li>Add new order or update order: <code>GET /api/order/save</code></li>
        <li>Find order by order idt: <code>POST /api/order/orderId</code></li>
        <li>Find order by item: <code>POST /api/order/itemId</code></li>
        <li>Find order by user involved: <code>POST /api/order/userId</code></li>
        <li>Find order by order status: <code>POST /api/order/status</code></li>
        <li>Find order by order created day: <code>POST /api/order/createDate</code></li>
        <li>Find order by order updated dat: <code>POST /api/order/updateDate</code></li>
    </ul>
        <h3>3) User Service</h3>
    <ul>
        <li><strong>Description</strong>: Manages user accounts and authentication.</li>
        <li><strong>Tech Stack</strong>: Java, Spring Boot</li>
    </ul>
   <h4>Endpoints:</h4>
   <h5>User:<code>/api/user</code></h5>
    <ul>
        <li>User Signup: <code>POST /api/user/singup</code></li>
        <li>User login : <code>POST /api/user/login</code></li>
        <li>Get all users list : <code>GET /api/user/getAllUsers</code></li>
        <li>Find user by user id, status, user role, contact: <code>GET /api/user/findUserBy</code></li>
        <li>Forgot password : <code>POST /api/user/forgot-password</code></li>
        <li>Reset password : <code>GET /api/user/reset-password</code></li>
    </ul>
        <h2>Installation</h2>
    <h3>Prerequisites</h3>
    <ul>
        <li>Docker</li>
        <li>Docker Compose</li>
        <li>Java 11 or higher</li>
        <li>Maven</li>
    </ul>
 <h2>Functionality</h2>
      <ul>
        <li>All are run on the eureka discovery client and all the series are running on the 8080 port  from API gateway </li>
    </ul>
     <h4>Diagram of functionality:</h4>
    ![readme drawio (1)](https://github.com/Cypso-Labs/Project-Liquor-Logic-Backend/assets/167277954/dbd58816-5946-4476-b74f-2061a1ce3465)
  </body>
