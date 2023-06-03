package prs.crazyk.cmm.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import prs.crazyk.cmm.model.response.ServerResponseEntity;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class MongoController {

	@GetMapping("/mongo")
	public ServerResponseEntity<String> mongo() {
		return ServerResponseEntity.OK("ADSFDSF");
	}

}
