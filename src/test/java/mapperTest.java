import com.bs.mrmf.mapper.adminMapper;
import com.bs.mrmf.pojo.administrator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class mapperTest {

    @Autowired
    adminMapper am;

    @Test
    public void IntTest(){
        String time = System.currentTimeMillis()+"";
        System.out.println(time);
        String substring = time.substring(5);
        System.out.println(substring);
        Integer integer = Integer.valueOf(substring);
        System.out.println(integer);
        /*Long integer = Long.valueOf(System.currentTimeMillis());
        System.out.println(integer);*/
    }

    @Test
    public void DicTest(){
        BigDecimal bigDecimal = BigDecimal.valueOf(100);
        BigDecimal big = BigDecimal.valueOf(80);
        if(bigDecimal.doubleValue() > big.doubleValue()){
            System.out.println("输出");
        }
    }

    @Test
    public void mathTest() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println(sdf.parse(sdf.format(date)));
    }

    @Test
    public void adminText(){
        Date date = new Date(System.currentTimeMillis()-(1000*60*60*24));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        System.out.println(format);

        Date date1 = new Date();
        System.out.println(date1);
        System.out.println(sdf.format(date1));
        try {
            System.out.println(sdf.parse(sdf.format(date1)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
