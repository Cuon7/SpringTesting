package com.example.demo11;//package com.example.demo;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.filter.CommonsRequestLoggingFilter;
//
// 
//@Configuration
//public class RequestLoggingFilterConfig {
//	
//   @Bean   
//   public CommonsRequestLoggingFilter logFilter() {
//       CommonsRequestLoggingFilter filter
//               = new CommonsRequestLoggingFilter();
//       filter.setIncludeQueryString(true);
//       filter.setIncludePayload(true);
//       filter.setMaxPayloadLength(10000);
//       filter.setIncludeHeaders(false);
//       filter.setAfterMessagePrefix("REQUEST DATA : ");       
//       return filter;
//       
//   
//   } 
//   
////   public void logRequest(HttpServletRequest httpServletRequest, Object body) {
////       if (httpServletRequest.getRequestURI().contains("medias")) {
////           return ;
////           String a = GsonParserUtils.parseObjectToString(body);
//       
//// }
//}