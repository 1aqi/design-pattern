package cn.aqi.chatgpt.domain.security.service.realm;

import cn.aqi.chatgpt.domain.security.model.vo.JwtToken;
import cn.aqi.chatgpt.domain.security.service.JwtUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title: JwtRealm
 * @Author Xu Jing
 * @Package cn.aqi.chatgpt.domain.security.service.realm
 * @Date 2024/1/4 15:27
 * @description:Jwt校验工具
 */
public class JwtRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(JwtRealm.class);

    private static JwtUtil jwtUtil = new JwtUtil();

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String jwt = (String) token.getPrincipal();
        if (jwt == null) {
            throw new NullPointerException("jwtToken is null");
        }
        if (!jwtUtil.isVerify(jwt)) {
            throw new UnknownAccountException();
        }
        String username = (String) jwtUtil.decode(jwt).get("username");
        logger.info("授权用户：username :{}", username);
        return new SimpleAuthenticationInfo(jwt, jwt, "JwtRealm");
    }
}
