/***
 * 自定义异常类
 */
public class customException extends Exception
{
    private String msg;
    private static final long serialVersionUID = 1L;
    public customException(String msg)
    {
        super(msg);
        this.setMsg(msg);
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
