package com.spring.jwt.security;

import java.io.IOException;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.ArrayList;

import com.spring.jwt.model.ApplicationUser;
import com.spring.jwt.security.SecurityConstants;
import com.spring.jwt.service.CustomUserDetailsService;

import io.jsonwebtoken.Jwts;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	public CustomUserDetailsService customUserDetails;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager,
			CustomUserDetailsService customUserDetails) {
		super(authenticationManager);
		this.customUserDetails = customUserDetails;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// for second request...
		System.out.println("===================================");
		System.out.println("filter call");
		String header = req.getHeader(SecurityConstants.HEADER_STRING);
		System.out.println("header");
		if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(SecurityConstants.HEADER_STRING);
		if (token != null) {
			// parse the token.
			/*
			 * String user =
			 * JWT.require(Algorithm.HMAC512(SECRET.getBytes())).build()
			 * .verify(token.replace(TOKEN_PREFIX, "")).getSubject();
			 */
			String user = Jwts.parser().setSigningKey(SecurityConstants.SECRET)
					.parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, "")).getBody().getSubject();
			System.out.println("=========================================================");
			System.out.println(user);
			UserDetails uDetails = customUserDetails.loadUserByUsername(user);
			ApplicationUser appUser = customUserDetails.loadAppUser(user);
			if (user != null) {
				// return new UsernamePasswordAuthenticationToken(user, null,
				// new ArrayList<>());
				return new UsernamePasswordAuthenticationToken(appUser, null, uDetails.getAuthorities());
			}
			return null;
		}
		return null;
	}
}