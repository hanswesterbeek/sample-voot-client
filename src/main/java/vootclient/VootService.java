package vootclient;

import org.springframework.web.client.RestOperations;

public class VootService {

  private RestOperations vootServiceRestTemplate;

  public VootService(RestOperations vootServiceRestTemplate) {
    this.vootServiceRestTemplate = vootServiceRestTemplate;
  }

  public String getSomeGroupName() {
    final String groupname = vootServiceRestTemplate.getForObject("/voot/me/groups", String.class);
    return groupname;
  }
}
