/**
 * @Author :caijx
 * @Description:
 * @Date :Created in 10:13 2018/5/4
 * @Modified By:
 */

public class testException {

    public static void main(String[] args) {
        try {
            Object obj  = null;
            System.out.println(obj.toString());
        }catch(NullPointerException e){
            System.out.println("空指针异常了");
        }

    }
}
