package com.twinkle.JakSim;

import com.twinkle.JakSim.controller.account.AccountRestApi;
import com.twinkle.JakSim.model.dto.account.UserDto;
import com.twinkle.JakSim.model.service.account.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class JakSimApplicationTests {
	@Autowired
	private AccountService accountService;
	@Autowired
	private AccountRestApi accountRestApi;


	/**
	 * <h3>간편 회원등록입니다.</h3>
	 * 작성시 유의사항)<br>
	 * 1. <b>mariaDB에서 조회하신 후 </b>, 작성해주시길 바랍니다. <br>
	 * 1-1. 특히, id, email, tel의 경우 unique이므로 겹쳐서는 안됩니다.<br>
	 * 2. 내부에 데이터를 입력해주시기 바랍니다.<br>
	 * 3. 우측에 위치한 실행버튼을 눌러 진행해주시면 됩니다.<br>
	 * <p>
	 *     테스트 중에 발생하는 오류에 대해<br>
	 *     - 중복된 데이터를 작성했을 경우, 애러가 발생할 수 있습니다.<br>
	 *     - 혹은 데이터베이스 수정이 이뤄지지 않은 경우 발생할 수 있습니다.<br>
	 *     - 현재 데이터베이스는 user question과 answer가 포함되어있는 상태입니다.
	 * </p>
	 *
	 */
	@Test
	void singleRegisterMember() {
		UserDto userDto = new UserDto();

		userDto.setId("test3");
		userDto.setPw("1234");
		userDto.setName("tester3");
		userDto.setEmail("llsd4026@naver.com");
		userDto.setGender(0);
		userDto.setBirth("2023-03-30");
		userDto.setTel("13");
		userDto.setQuestion(0);
		userDto.setAnswer("Questions");
		userDto.setRole(2); // 0->Admin 1->Trainer 2->User

		accountRestApi.AccountAction(userDto);
	}


}
