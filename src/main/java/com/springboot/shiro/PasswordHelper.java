package com.springboot.shiro;

import com.springboot.entity.Employee;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author Administrator on 2018/9/19.
 */
public class PasswordHelper {
    private String algorithmName = "md5";
    private final int hashIterations = 1024;
    public void encryptPassword(Employee emp) {
        String newPassword = new SimpleHash(
                algorithmName,
                emp.getEmpPassword(),
                ByteSource.Util.bytes(ShiroRealm.SALT),
                hashIterations).toHex();
        emp.setEmpPassword(newPassword);
    }
}
