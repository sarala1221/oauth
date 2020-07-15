package com.api.gateway.cfg;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.api.gateway.filters.SimpleFilter;

@Configuration
@EnableZuulProxy
@EnableDiscoveryClient
@EnableEurekaClient
public class GatewayConfiguration {

//    private final static String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
//            "MIIEpQIBAAKCAQEAsCdla+eXbtqhHU5k0RLhvBGcL/7t9GVevERGOwVOnIR5Hj/g\n" +
//            "XkpU4Rpow+Dzi6sM4W4GQSp22JmNwToEtx4wYrsgyPrftiBUBAum7VcpwXi7yi8x\n" +
//            "QyFf+SDXlOUDEu0W3xDciM+1DS4P0C7Y/ywwR81ruNB5OfGRVJVHPi9/lT/MEkC4\n" +
//            "s9YDigTWD9TAkVEkqXcA6pOeBWp74L8nDBi9ZEFOD7egWJAPmeR7OOFFe190dibq\n" +
//            "QtEsehwrtrTVs5ikWJi7uIrNPmhFFpI+G1k7Xt+MkRE6kTDVdbuA/WTqaSOqiWRU\n" +
//            "vDX3+C52lNPxG9oxBotJ02vwQooaNc9PD1wxpQIDAQABAoIBAQCUsozgy3x/bTq3\n" +
//            "3GcqPgAjm9H2igIgXjncPt3yEIbNhN4g9NbXgXOpGXv6bbfo3Jlg6lmJROlR6j1J\n" +
//            "MUqtsWQy07fXMD8MJLoHODbO/tcWpG4RPEkfW9sCGAEjhTZVEaJO5pLjQkKZP5rF\n" +
//            "p+YxcQHIetVGC2VpzktZ1TFwgvP17aApOk534kLiZI1EEdxE6ScSfoErE/99j1pr\n" +
//            "Glglxv/Wi1jN0842Z+XXevNPCr8OXjn9dufP69N9+GUSWvtdSo8X7cISIYtJ7qNg\n" +
//            "Tvx+pOOtI/1zHeHCYmkL92TsPoiC4wTXn88JamNJzrDA3bj+roul3ow5hS/bPwQt\n" +
//            "Jpl40rEBAoGBANgZuLdjfJppM/NqEh1gYCnCiY7q/qSNWl2L6TnUrKF7X4fGdPju\n" +
//            "H6AlWsERVkz69TCad3v/s3Yos/EQmMZMOKAkD9eJAdcogn2LQta0SUXYMA+j4b7h\n" +
//            "pAR9SydEK+WzR7vWync3vm4Ua+8xVPK2/n7BOyKUiwCvabiSSrW7PmSFAoGBANCt\n" +
//            "i7x79toB3GQJmNc6WzLgm76aaUBtrNA8gp4PB1DLWnFsKGXJysC8FYJ3vIMDwTtV\n" +
//            "91qcNJovG+YoR5ZZLar+sesZgDfTWIska/ObXVs8xwCB9hImmrqi+h1rk5oYtoQd\n" +
//            "e6K+PR5PSvGQIVpsQ6VeFA0u+OGUTD46i5MCmTKhAoGBAKu0XFHSycnC95VcXYJb\n" +
//            "9myX90bIr4Y+DmQv7COYOYahiblPVdgJFLXhmGe5h9HowXNZ+MBc3tJu6vMLFejR\n" +
//            "3VhY7wF6Ci1UMcLhKT2ByIqniSCUPaW2keVDTLipcRu1RLUaqIrrjvLWZSCMGQw1\n" +
//            "nKdIj90dHAlyV1VhX91gJuYlAoGATfkKxZrsWfloCFktqv47JX+XsliwC0rvz79M\n" +
//            "bxLegOn7GXZ4gdAs2mdtAhsLbUjx4uH0Uv8bCKGhoxZZ6WvZDJOul1In/rQR2av6\n" +
//            "SkQ7VeQX17C36YBTQHm1S1jvBh1F1YfOsriyzaiDME/Spp7dh+TnSSXMCT4IXxgx\n" +
//            "HvVRmQECgYEAzShRxd5pVw/Nc4QrmvWC29hYz4t00J6Y7HkG0ydWMm84Jk8JsCZn\n" +
//            "iWu+sHz+CivrEw3LhJbgCqIsrksuCDIoFM5w+FKfKMht6FK6uIo/GFhBeiVNOTbT\n" +
//            "BebPhRbl8L2ybbilLC2IabcotnZ2/IkQATOezQY2saO6yYf4/bGFbNI=\n" +
//            "-----END RSA PRIVATE KEY-----";
//    private final static String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
//            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsCdla+eXbtqhHU5k0RLh\n" +
//            "vBGcL/7t9GVevERGOwVOnIR5Hj/gXkpU4Rpow+Dzi6sM4W4GQSp22JmNwToEtx4w\n" +
//            "YrsgyPrftiBUBAum7VcpwXi7yi8xQyFf+SDXlOUDEu0W3xDciM+1DS4P0C7Y/yww\n" +
//            "R81ruNB5OfGRVJVHPi9/lT/MEkC4s9YDigTWD9TAkVEkqXcA6pOeBWp74L8nDBi9\n" +
//            "ZEFOD7egWJAPmeR7OOFFe190dibqQtEsehwrtrTVs5ikWJi7uIrNPmhFFpI+G1k7\n" +
//            "Xt+MkRE6kTDVdbuA/WTqaSOqiWRUvDX3+C52lNPxG9oxBotJ02vwQooaNc9PD1wx\n" +
//            "pQIDAQAB\n" +
//            "-----END PUBLIC KEY-----";

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public JwtTokenStore tokenStore() {
        JwtTokenStore jwtTokenStore = new JwtTokenStore(tokenEnhancer());
        return jwtTokenStore;
    }

    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey(privateKey);
//        converter.setVerifierKey(publicKey);
        return new JwtAccessTokenConverter();
    }
    
    @Bean
    public SimpleFilter simpleFilter() {
      return new SimpleFilter();
    }


}
