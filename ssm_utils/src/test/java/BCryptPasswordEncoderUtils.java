import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 测试加密方法
 */
public class BCryptPasswordEncoderUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String encodePassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String password="123";
        String password1 = encodePassword(password);
        System.out.println(password1);
    }
}
