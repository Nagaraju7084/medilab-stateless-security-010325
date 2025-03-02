package com.medilab.preclinic.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class CustomUserCheckingFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("I am from customuserchekcingfilter...");
		System.out.println("user name is:\t"+request.getParameter("name"));
		System.out.println("password is:\t"+request.getParameter("password"));
		System.out.println(request.getHeader("Authorization"));
		filterChain.doFilter(request, response);
	}

}
