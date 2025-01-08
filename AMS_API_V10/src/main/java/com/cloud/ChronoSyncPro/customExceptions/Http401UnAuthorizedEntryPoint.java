//package com.cryptosoft.customExceptions;
//
//import java.io.IOException;
//
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//public class Http401UnAuthorizedEntryPoint implements AuthenticationEntryPoint {
//
//	@Override
//	public void commence(HttpServletRequest request, HttpServletResponse response,
//			AuthenticationException authException) throws IOException, ServletException {
//		log.info("PreAuthorized entry Point called Rejecting Access");
//		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Access Denied");
//	}
//
//}
//package com.cryptosoft.customExceptions;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//
//import jakarta.servlet.ServletException;
//
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//public class Http401UnAuthorizedEntryPoint implements AuthenticationEntryPoint {
//
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
//            throws IOException, ServletException {
//        response.setContentType("application/json");
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//
//        Map<String, Object> errorDetails = new HashMap<>();
//        errorDetails.put("message", "Unauthorized access");
//        errorDetails.put("error", authException.getMessage());
//
//        ObjectMapper mapper = new ObjectMapper();
//        response.getOutputStream().println(mapper.writeValueAsString(errorDetails));
//    }
//
//	@Override
//	public void commence(jakarta.servlet.http.HttpServletRequest request,
//			jakarta.servlet.http.HttpServletResponse response, AuthenticationException authException)
//			throws IOException, ServletException {
//		// TODO Auto-generated method stub
//		
//	}
//}
package com.cloud.ChronoSyncPro.customExceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class Http401UnAuthorizedEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("message", "Unauthorized access");
        errorDetails.put("error", authException.getMessage());

        ObjectMapper mapper = new ObjectMapper();
        response.getOutputStream().println(mapper.writeValueAsString(errorDetails));
    }
}
