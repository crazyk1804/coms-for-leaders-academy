package prs.crazyk.cmm.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import prs.crazyk.cmm.model.response.ServerResponseEntity;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

@ApiIgnore
@RestController
@RequiredArgsConstructor
public class TestController {
//	@Resource(name = "testVVDSEQIdGnrService")
//    private EgovIdGnrService vvdId;
//	@Resource(name = "esntlIdGnrService")
//	private EgovIdGnrService esntlId;

//	@GetMapping("/test/vvd")
//	public ServerResponseEntity<String> testVVD() throws FdlException {
//		return ServerResponseEntity.OK(vvdId.getNextStringId());
//	}
//
//	@GetMapping("/test/esntl")
//	public ServerResponseEntity<String> testEsntlId() throws FdlException {
//		return ServerResponseEntity.OK(esntlId.getNextStringId());
//	}
}
