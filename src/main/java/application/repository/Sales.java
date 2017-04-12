package application.repository;

import application.entity.Sale;
import org.springframework.stereotype.Repository;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by MIHONE on 4/8/2017.
 */

@Repository
@XmlRootElement(name = "Sales")
public class Sales {

    private List<Sale> sales = null;

    @XmlElement(name = "Sale")
    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    public List<Sale> getSales() {
        return sales;
    }
}
