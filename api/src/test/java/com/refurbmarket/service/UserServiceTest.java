package com.refurbmarket.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.refurbmarket.common.util.TokenProvider;
import com.refurbmarket.domain.User;
import com.refurbmarket.dto.request.LoginRequestDto;
import com.refurbmarket.dto.request.SignUpRequestDto;
import com.refurbmarket.dto.response.LoginResponseDto;
import com.refurbmarket.repository.MyBatisUserRepository;
import com.refurbmarket.repository.RedisRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	@InjectMocks
	UserService userService;
	@Mock
	RedisRepository redisRepository;
	@Mock
	MyBatisUserRepository userRepository;
	@Mock
	TokenProvider tokenProvider;

	@DisplayName("회원가입 실패_이미 회원 존재")
	@Test
	public void failOnDuplicateUserSignup() {
		// given
		SignUpRequestDto request = signUpRequestDto();
		User user = toDomain();
		doReturn(Optional.of(user)).when(userRepository).findByEmail(request.getEmail());
		// when
		final RuntimeException result = assertThrows(IllegalArgumentException.class,
			() -> userService.createUser(request));
		// then
		assertThat(result.getMessage()).isEqualTo("이메일 중복입니다.");
	}

	@DisplayName("회원가입 성공")
	@Test
	public void successFulUserSignUp() {
		// given
		SignUpRequestDto request = signUpRequestDto();
		User user = toDomain();
		String token = "asdfasdfasdf";
		doReturn(Optional.empty()).when(userRepository).findByEmail(request.getEmail());
		doReturn(user).when(userRepository).insertUser(any(User.class));
		doReturn(token).when(tokenProvider).generateToken();
		doNothing().when(redisRepository).set(any(String.class), any(String.class));
		// when
		LoginResponseDto response = userService.createUser(request);
		// then
		assertThat(response.getToken()).isEqualTo(token);
		then(userRepository).should(times(1)).insertUser(any(User.class));
		then(redisRepository).should(times(1)).set(any(String.class), any(String.class));
	}

	@DisplayName("로그인 실패_회원이 존재하지 않음")
	@Test
	public void failOnUserNotFoundLogin() {
		// given
		LoginRequestDto request = loginRequestDto();
		doReturn(Optional.empty()).when(userRepository)
			.findByEmailAndPassword(request.getEmail(), request.getPassword());
		// when
		final RuntimeException result = assertThrows(IllegalArgumentException.class,
			() -> userService.login(request));
		// then
		assertThat(result.getMessage()).isEqualTo("로그인 정보가 올바르지 않습니다.");
	}

	@DisplayName("로그인 성공")
	@Test
	public void successFulUserLogin() {
		// given
		LoginRequestDto request = loginRequestDto();
		User user = toDomain();
		String token = "asdfasdfasdf";
		doReturn(Optional.of(user)).when(userRepository)
			.findByEmailAndPassword(request.getEmail(), request.getPassword());
		doReturn(token).when(tokenProvider).generateToken();
		doNothing().when(redisRepository).set(any(String.class), any(String.class));
		// when
		LoginResponseDto response = userService.login(request);
		// then
		assertThat(response.getToken()).isEqualTo(token);
		then(redisRepository).should(times(1)).set(any(String.class), any(String.class));
	}

	private SignUpRequestDto signUpRequestDto() {
		return new SignUpRequestDto("테스트", "test@test.com", "test", "01012341234");
	}

	private LoginRequestDto loginRequestDto() {
		return new LoginRequestDto("test@test.com", "test");
	}

	private User toDomain() {
		return new User(1L, "테스트", "test@test.com", "test", "01012341234");
	}
}
