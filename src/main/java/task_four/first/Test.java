package task_four.first;

import task_four.first.bean.DataBean;
import task_four.first.bean.DataBeanTwo;
import task_four.first.bean.TelephoneBean;
import task_four.first.pojo.Data;
import task_four.first.pojo.Telephone;

public class Test {

    public void runTest() {
        CacheInjector injector = new CacheInjector();

        DataBean dataBean = new DataBean();
        injector.inject(dataBean);
        dataBean.setData(new Data("Buy new computer", 1));
        dataBean.setData(new Data("Go to the cinema", 2));
        System.out.println(dataBean.getData(1).getDescription());

        DataBean dataBeanTwo = new DataBeanTwo();
        injector.inject(dataBeanTwo);
        dataBeanTwo.setData(new Data("Take milk", 3));
        dataBeanTwo.setData(new Data("Clean room", 4));
        System.out.println(dataBeanTwo.getData(4).getDescription());

        TelephoneBean telephoneBean = new TelephoneBean();
        injector.inject(telephoneBean);
        telephoneBean.setTelephone(new Telephone(21222,
                "Eldar"));
        telephoneBean.setTelephone(new Telephone(20022,
                "Gennady"));
        System.out.println(telephoneBean.getOwnerByNumberPhone(20022).getNameOwner());
    }

}
