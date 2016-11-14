package cn.hankchan.stu.shiro.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import cn.hankchan.stu.util.SecurityUtils;

public class MyShiroRealm extends AuthorizingRealm {

	private static final String USER_NAME = "DavidBeckham";
	private static final String PASSWORD = "123456";
	
	/** 授权 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Set<String> roleNames = new HashSet<String>();
		Set<String> permissions = new HashSet<>();
		// 添加角色
		roleNames.add("administrator");
		// 添加权限
		permissions.add("newPage.html");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(permissions);
		return info;
	}

	/** 验证 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		if(token.getUsername().equals(USER_NAME)) {
			return  new SimpleAuthenticationInfo(USER_NAME, SecurityUtils.turnToMD5(PASSWORD), getName());
		} else {
			throw new AuthenticationException();
		}
	}

}
