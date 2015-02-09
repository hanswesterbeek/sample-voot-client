package vootclient;

import java.util.Arrays;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@SpringBootApplication
public class VootClientApplication {

  public static void main(String[] args) {
    SpringApplication.run(VootClientApplication.class, args);
  }

  @Configuration
  @EnableOAuth2Client
  protected static class ResourceConfiguration {

    private String accessTokenUri = "http://localhost:8080/oauth/token";

    private String userAuthorizationUri = "http://localhost:8080/oauth/authorize";

    @Resource
    @Qualifier("accessTokenRequest")
    private AccessTokenRequest accessTokenRequest;

    @Bean
    public OAuth2ProtectedResourceDetails vootResource() {
      AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
      details.setId("vootResource");
      details.setClientId("sample-voot-client");
      details.setClientSecret("secret");
      details.setAccessTokenUri(accessTokenUri);
      details.setUserAuthorizationUri(userAuthorizationUri);
      details.setScope(Arrays.asList("read", "write"));
      return details;
    }

    @Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public OAuth2RestTemplate vootServiceRestTemplate() {
      return new OAuth2RestTemplate(vootResource(), new DefaultOAuth2ClientContext(accessTokenRequest));
    }

    @Bean
    VootService vootService(){
      return new VootService(vootServiceRestTemplate());
    }

  }
}
