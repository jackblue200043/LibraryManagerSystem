package util.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetNowDate {
    /**
     * 获取2019-01-02格式的时间
     * @return
     */
    public static String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Date nowTime = new Date(System.currentTimeMillis());
        return sdf.format(nowTime);
    }

    public static void main(String[] args) {
        String sql2 = "insert into borrow(userName, bookId, start, timeLeft, flag) " +
                "values("+ "'" + "userName" + "'" + ","+ "'" + "book.getBookId()" + "'" + "," +
                "'" + GetNowDate.getDate() + "'" + "," + "'" + "book.getTime()" + "," + "," + "0" + ")";
        System.out.println(sql2);
    }
}
