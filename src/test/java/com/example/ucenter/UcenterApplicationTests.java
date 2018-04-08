package com.example.ucenter;

import com.example.ucenter.Filter.LoginPara;
import com.example.ucenter.service.impl.UserAuth;
import com.example.ucenter.utils.ResultMsg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UcenterApplicationTests {
	@Autowired
	private UserAuth userAuth;

	@Test
	public void contextLoads() {
	}

	@Test
	public void oauthToken(){
		LoginPara loginPara = new LoginPara();
		loginPara.setClientId("098f6bcd4621d373cade4e832627b4f6");
		loginPara.setUserName("test");
		loginPara.setPassword("test");
		ResultMsg resultMsg = (ResultMsg) userAuth.aouthToken(loginPara);
		System.out.println("啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊"+resultMsg.toString());
	}
}
