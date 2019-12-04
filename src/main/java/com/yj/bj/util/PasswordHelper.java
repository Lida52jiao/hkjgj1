package com.yj.bj.util;


import com.yj.bj.entity.UserEntity;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordHelper {
	private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	private static String algorithmName = "md5";
	private static int hashIterations = 2;

	public static UserEntity encryptPassword(UserEntity infoEntity) {
		String salt = randomNumberGenerator.nextBytes().toHex();
		infoEntity.setCredentialsSalt(salt);
		String newPassword = new SimpleHash(algorithmName,
				infoEntity.getPassWord(), ByteSource.Util.bytes(infoEntity
						.getAccountName() + salt), hashIterations).toHex();
		infoEntity.setPassWord(newPassword);
		/*System.out.println(newPassword);
		System.out.println(salt);*/
		return infoEntity;
	}

	public static void main(String[] args) {
		PasswordHelper passwordHelper = new PasswordHelper();
		UserEntity userFormMap = new UserEntity();
		userFormMap.setPassWord("123456");
		userFormMap.setAccountName("admin");
		passwordHelper.encryptPassword(userFormMap);
	}
}
