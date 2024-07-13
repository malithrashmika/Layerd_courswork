package lk.Ijse.bo;

import lk.Ijse.bo.custom.impl.*;
import lk.Ijse.dao.custom.impl.IngredientDAOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,ITEM,Emp,SUP,IN,PO,RESERVATION
    }

    //Object creation logic for BO objects
    public SuperBO getBO(BOTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case PO:
                return new PlaceorderBOImpl();
            case Emp:
                return new EmployeeBOImpl();
            case SUP:
                return new SupplierBOImpl();
            case IN:
                return new IngredientBOImpl();
            case RESERVATION:
                return new ReservationBOImpl();
            default:
                return null;
        }
    }
}
