package presentation;

import business.BusinessImpl;
import dao.DaoImpl;

public class Pres1 {

    public static void main(String[] args) {

        BusinessImpl business = new BusinessImpl();
        DaoImpl dao = new DaoImpl();
        business.setDao(dao);
        System.out.println("resultat ==> "+business.calculate());

    }

}