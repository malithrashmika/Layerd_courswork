package lk.Ijse.entity;


import lk.Ijse.model.OrderDTO;
import lk.Ijse.model.order_itemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomEntity {
    //customer
    String id;
    String name;
    String tel;
    String email;
    //employee
    String e_id;
    String e_name;
    String e_Tel;
    String e_salary;
    String e_Role;
    //item
    String code;
    String i_name;
    String description;
    String category;
    double price;
    int QtyOnHand;
    //order
    private String orderId;
    private String customerId;
    private Date orderDate;
    private Time time;
    private String Table;
    private String employeeId;
    private double NetTotal;
    //supplier
    private String supplierId;
    private String supplierName;
    private String contactNumber;
    private String contactEmail;
    //ingredients
    String Ingredient_id;
    String Ingredient_name;
    String In_category;
    int qty_avalible;
    double unit_price;
    String supplier_id;
    //orderDetails
    private Time orderTime;
    private String tableType;
    private String waiter;
    private String CustomerID;
    private String ItemId;
    private double orderPrice;
    private int quantity;
    private double netTotal;
    //order_item
    private String ItemID;
    private String orderid;
    private int Qty;
    private double unitprice;
    private double totalprice;
    //Reservation
    String reservationId;
    Date date_of_reservation;
    Date reserved_date;
    Time reserved_time;
    String employee_id;
    String table_Number;
    String Customer_id;
    String Start_time;
    String End_time;
    String Event;
    //placeOrder
    private OrderDTO orderDTO;
    private List<order_itemDTO> odList;

}
