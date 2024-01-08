package business;


import dao.IDao;

/* BUSINESS LAYER */
public class BusinessImpl implements IBusiness{

    private IDao dao; // loose coupling

    @Override
    public double calculate() {

        double data = dao.getData();

        double res = data * 11.4;

        return res;
    }

    /**
     *  allow injection for dao variable
     *  implementation of the IDao interface
     *  @param dao
    */
    public void setDao (IDao dao) {
        this.dao = dao;
    }

}
