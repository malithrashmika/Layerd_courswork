package lk.Ijse.bo.custom.impl;

import lk.Ijse.bo.custom.PlaceOrderBO;
import lk.Ijse.dao.CrudDAO;
import lk.Ijse.dao.DAOFactory;
import lk.Ijse.dao.custom.CustomerDAO;
import lk.Ijse.dao.custom.ItemDAO;
import lk.Ijse.dao.custom.OrderDAO;
import lk.Ijse.dao.custom.OrderDetailsDAO;
import lk.Ijse.db.DBConnection;
import lk.Ijse.dto.OrderDTO;
import lk.Ijse.dto.OrderDetailsDTO;
import lk.Ijse.entity.Item;
import lk.Ijse.entity.Order;
import lk.Ijse.entity.OrderDetails;
import lk.Ijse.model.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceorderBOImpl implements PlaceOrderBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);
    Connection connection = null;
    @Override
    public boolean purchaseOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {
/*        connection = DBConnection.getDbConnection().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isOrderSaved = OrderDAO.add(new Order(dto.getOrderId(), dto.getOrderDate(),dto.getTime(),dto.getTable(),dto.getCustomerId(),dto.getEmployeeId(),dto.getNetTotal()));
            System.out.println("isOrederSave"+isOrderSaved);
            if (isOrderSaved) {
                System.out.println("isOrederSave"+isOrderSaved);
                boolean isQtyUpdated = false;
                for (OrderDetailsDTO od : dto.getOdlist()) {
                    if (od.getFishId() == null) {

                        isQtyUpdated = accessoriesDao.updateQty(od.getAccId(),od.getQty());

                    } else {
                        System.out.println("isOrederSave"+isOrderSaved);
                        isQtyUpdated = fishDao.updateQty1(od.getFishId(),od.getQty());
                    }
                }
                System.out.println("isQtyUpdated"+isQtyUpdated);
                if (isQtyUpdated) {
                    boolean isSave=false;
                    for (OrderDetailDTO od : dto.getOdlist()){
                        if (od.getFishId() == null) {

                            isSave = accessoriesOrderDao.save(new AccessoriesOrder(od.getOrdId(),od.getAccId(),od.getQty(),od.getDescription(),od.getStatus(),od.getDate()));

                        } else {
                            isSave = fishOrderDao.save1(od.getOrdId(),od.getFishId(),od.getQty(),od.getDescription(),od.getStatus(),od.getDate());
                        }
                    }
                    System.out.println("isSave"+isSave);
                    if (isSave ) {
                        boolean isSave1 = false;
                        for (OrderDetailDTO od : dto.getOdlist()) {
                            isSave1 = orderEmployeeDao.save1(od.getOrdId(),od.getEmpId());
                        }
                        System.out.println("isSave1"+isSave1);
                        if (isSave1) {
                            connection.commit();
                            return true;
                        }
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (SQLException e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }*/

        Connection connection = null;
        try {
            connection = DBConnection.getDbConnection().getConnection();
            boolean b1 = orderDAO.exist(dto.getOrderId());
            /*if order id already exist*/
            if (b1) {
                return false;
            }

            connection.setAutoCommit(false);
            //Save the Order to the order table
            boolean b2 = CrudDAO.add(new Order(dto.getOrderId(), dto.getOrderDate(),dto.getTime(),dto.getTable(),dto.getCustomerId(),dto.getEmployeeId(),dto.getNetTotal()));
            if (!b2) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            for (OrderDetailsDTO entity : dto.getOrderDetails()) {
                OrderDetails orderDetails = new OrderDetails(entity.getOrderId(),entity.getOrderDate(),entity.getOrderTime(),entity.getTableType(),entity.getWaiter(),entity.getCustomerID(),entity.getItemId(),entity.getOrderPrice(),entity.getQuantity(),entity.getNetTotal());
                boolean b3 = orderDetailsDAO.add(orderDetails);
                if (!b3) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
                //Search & Update Item
                ItemDTO item = findItem(entity.getItemCode());
                item.setQtyOnHand(item.getQtyOnHand() - entity.getQty());

                //update item
                boolean b = itemDAO.update(new Item(item.getCode(),item.getName(),item.getDescription(),item.getCategory(),item.getPrice(),item.getQtyOnHand()));

                if (!b) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false
    }
    @Override
    public ItemDTO findItem(String code)throws SQLException, ClassNotFoundException {
        try {
            Item i = itemDAO.search(code);
            return new ItemDTO(i.getCode(),i.getName(),i.getDescription(),i.getCategory(),i.getPrice(),i.getQtyOnHand());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + code, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
