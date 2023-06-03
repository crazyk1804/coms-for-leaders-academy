package prs.crazyk.cmm.service.impl;

import org.springframework.stereotype.Service;
import prs.crazyk.cmm.exception.CustomException;

@Service("TestService")
public class TestServiceImpl {
	public void test() throws CustomException {
		throw new CustomException("CUSTOM EXCEPTION");
	}
}
