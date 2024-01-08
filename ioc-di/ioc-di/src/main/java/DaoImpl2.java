import dao.IDao;

public class DaoImpl2 implements IDao {

    @Override
    public double getData() {
        System.out.println("Web Service Version");
        double data = 55;

        return data;
    }

}
