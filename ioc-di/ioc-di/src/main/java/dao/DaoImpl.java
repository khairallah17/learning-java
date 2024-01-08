package dao;

public class DaoImpl implements IDao{

    @Override
    public double getData() {
        System.out.println("db version");

        double data = 34;

        return data;
    }
}
