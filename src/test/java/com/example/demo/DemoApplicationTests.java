package com.example.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.Key;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	public void login(String ini,String name,String pass) {
		//获取SecurityManager工厂，此处用ini配置文件初始化SecurityManager
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(ini);
		//得到SecurityManager实例，
		SecurityManager securityManager = factory.getInstance();
		//并把该实例绑定给SecurityUtils，这是一个全局设置，设置一次就可
		SecurityUtils.setSecurityManager(securityManager);
		//得到subject，其会自动绑定到当前线程；如果在web环境在请求结束时需要解除绑定
		Subject subject = SecurityUtils.getSubject();
		//创建用户名及密码的身份验证的Token（即用户身份/凭证）
		UsernamePasswordToken token = new UsernamePasswordToken(name,pass);
		//登录，及身份验证
		subject.login(token);
	}

	@Test
	public void testHasRole() {
		login("classpath:shiro-permission2.ini", "wang", "123");
		Subject subject = SecurityUtils.getSubject();
		//判断拥有权限：user:create
		Assert.assertTrue(subject.isPermitted("system:user:create"));
		//判断拥有权限：user:update and user:delete
		Assert.assertTrue(subject.isPermittedAll("system:user:update", "system:user:delete"));
		//判断没有权限：user:select
		Assert.assertTrue(subject.isPermitted("system:user:select"));
	}

	@Test
	public void testCheckRole(){
		login("classpath:shiro-permission2.ini","li","123");
		Subject subject = SecurityUtils.getSubject();
		try{
			//checkPermission 失败会抛出异常UnauthorizedException

			/*//断言拥有权限：user:update
			subject.checkPermissions("system:user:update","system:user:delete");
			subject.checkPermissions("system:user:create,select");
			subject.checkPermissions("system:user:create,delete,update,view");
			subject.checkPermissions("system:user:select");
			subject.checkPermissions("system:user:view");
			subject.checkPermissions("book:view");
			subject.checkPermissions("book:create");*/
			subject.checkPermission("user:view:1");
			subject.checkPermissions("user:create,update:1");
			subject.checkPermissions("user:create,update,delete:1");
		}catch (UnauthorizedException e){
			System.out.println("验证失败");

		}

		//System.out.println("验证成功")
	}

	@Test
	public void baseTest(){
		String str1 = "hello";
		String base64Encode = Base64.encodeToString(str1.getBytes());
		//进行base64编码
		System.out.println(base64Encode);
		String str2 = Base64.decodeToString(base64Encode);
		//进行base64解码
		Assert.assertEquals(str1,str2);

		String base64Encoded = Hex.encodeToString(str1.getBytes());
		//进行16进制字符串编码
		System.out.println(base64Encoded);
		String str3 = new String(Hex.decode(base64Encoded.getBytes()));
		//进行16进制字符串解码
		Assert.assertEquals(str1,str3);

		byte[] b = CodecSupport.toBytes(str1);
		String s = CodecSupport.toString(b);

	}

	@Test
	public void pass(){
		String pass = "123456";
		String salt = "8080";
		String shal = new Md5Hash(pass,salt).toString();
		System.out.println(shal); //740215aae38dd8525bad7d4f273bf187

		//使用SHA256算法生成相应的散列数据，另外还有如SHA1、SHA512算法。
		String shal2 = new Sha256Hash(pass,salt).toString();
		System.out.println(shal2);//399443e3fedcfb1cdd821dca0819dbcce1b55bcffadc84a7d3f675c973afdc12

		//通过调用SimpleHash时指定散列算法，其内部使用了Java的MessageDigest实现。
		String shal3 = new SimpleHash("SHA-1",pass,salt).toString();//90be8524bdc96009841033651ce46b4022618f76
		System.out.println(shal3);
	}

	@Test
	public void hashServiceTest(){
		DefaultHashService hashService = new DefaultHashService();
		/*hashService.setHashAlgorithmName("SHA-512");
		//可以通过hashAlgorithmName属性修改算法；*/
		hashService.setPrivateSalt(new SimpleByteSource("123"));
		//私盐，默认无
		/*hashService.setGeneratePublicSalt(true);
		//是否生成公盐，默认false
		hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());
		//用于生成公盐，默认就这个
		hashService.setHashIterations(1);
		//生成hash迭代次数*/
		HashRequest hashRequest = new HashRequest.Builder().setAlgorithmName("MD5").
				setSource(ByteSource.Util.bytes("helllo")).setSalt(ByteSource.Util.bytes("123")).
				setIterations(2).build();
		String hex = hashService.computeHash(hashRequest).toHex();
		System.out.println(hex);  //714ab96c2b4b09e5b621410521d1fe83

		SecureRandomNumberGenerator generator = new SecureRandomNumberGenerator();
		System.out.println(generator);
		generator.setSeed("123".getBytes());

		String hex2 = generator.nextBytes().toHex();
		System.out.println(hex2);
	}

	@Test
	public void aesTest(){
		AesCipherService aesCipherService = new AesCipherService();
		aesCipherService.setKeySize(128); //参数值必须为128，或192，或256
		Key key = aesCipherService.generateNewKey();
		String text = "hello";
		String encrptText = aesCipherService.encrypt(text.getBytes(),key.getEncoded()).toHex();
		//加密
		System.out.println("加密后的"+encrptText);
		String text2 = new String(aesCipherService.decrypt(Hex.decode(encrptText),key.getEncoded()).getBytes());
		//解密
		System.out.println("解密后"+text2);
		Assert.assertEquals(text,text2);
	}
}
