package application.misc;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Date;

/**
 * Created by MIHONE on 4/8/2017.
 */

public abstract class DateAdapter extends XmlAdapter{

    public long unmarshal(String date) throws Exception {
        return Date.parse(date);
    }

    public String marshal(Date date) throws Exception {
        return date.toString();
    }
}
