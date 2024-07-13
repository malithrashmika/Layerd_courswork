package lk.Ijse.dao;

import lk.Ijse.bo.custom.impl.QueryDAOImpl;
import lk.Ijse.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER,ITEM,ORDER,Employee,ORDER_DETAILS,QUERY_DAO,Reservation,Ing,Sup
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case Employee:
                return new EmployeeDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_DETAILS:
                return new OrderDetailsDAOImpl();
            case Reservation:
                return new ReservationDAOImpl();
            case Ing:
                return new IngredientDAOImpl();
            case Sup:
                return new SupplierDAOImpl();
            case QUERY_DAO:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }
}
