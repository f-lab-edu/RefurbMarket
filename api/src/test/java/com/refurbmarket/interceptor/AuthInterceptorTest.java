package com.refurbmarket.interceptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;

import com.refurbmarket.common.define.HeaderKey;
import com.refurbmarket.common.interceptor.AuthInterceptor;
import com.refurbmarket.repository.RedisRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ExtendWith(MockitoExtension.class)
public class AuthInterceptorTest {
	@InjectMocks
	AuthInterceptor authInterceptor;
	@Mock
	RedisRepository redisRepository;
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;

	@DisplayName("인가 실패_잘못된 인증 헤더")
	@Test
	public void failOnInvalidAuthHeader() {
		// given
		doReturn("").when(request).getHeader(HttpHeaders.AUTHORIZATION);
		// when
		final RuntimeException result = assertThrows(RuntimeException.class,
			() -> authInterceptor.preHandle(request, response, any(Object.class)));
		//then
		assertThat(result.getMessage()).isEqualTo("인증 헤더가 없거나 잘못되었습니다.");
	}

	@DisplayName("인가 실패_유효하지 않은 토큰")
	@Test
	public void failOnInvalidToken() {
		// given
		String token = "asdfasdf";
		doReturn(HeaderKey.BEARER + token).when(request).getHeader(HttpHeaders.AUTHORIZATION);
		doReturn(Optional.empty()).when(redisRepository).get(token);
		// when
		final RuntimeException result = assertThrows(RuntimeException.class,
			() -> authInterceptor.preHandle(request, response, any(Object.class)));
		//then
		assertThat(result.getMessage()).isEqualTo("유효하지 않거나 만료된 토큰입니다.");
	}

	@DisplayName("인가 성공")
	@Test
	public void successfulAuthorization() {
		// given
		String token = "asdfasdf";
		doReturn(HeaderKey.BEARER + token).when(request).getHeader(HttpHeaders.AUTHORIZATION);
		doReturn(Optional.of("1")).when(redisRepository).get(token);
		// when
		final boolean result = authInterceptor.preHandle(request, response, any(Object.class));
		//then
		then(redisRepository).should(times(1)).get(any(String.class));
		assertThat(result).isTrue();
	}
}
