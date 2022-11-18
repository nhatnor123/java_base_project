package com.library_management.utils;

import com.library_management.enums.ResponseStatus;
import com.library_management.exception.ApiException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Collections;
import java.util.Objects;


public class RestFactory {
    private static final Logger LOGGER = LogManager.getLogger(RestFactory.class);

    public static <T> T postForEntity(String url, Object body, Class<T> clazz, String token) throws ApiException {
        try {
            HttpHeaders httpHeaders = getDefaultHttpHeaders();
            if (token != null) {
                httpHeaders.add(HttpHeaders.AUTHORIZATION, token);
            }
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<Object> request = new HttpEntity<>(body, httpHeaders);
            ResponseEntity<T> response = restTemplate.postForEntity(url, request, clazz);
            if (HttpStatus.OK.equals(response.getStatusCode()) && response.getBody() != null) {
                return response.getBody();
            }
            throw new ApiException(ResponseStatus.CALL_HTTP_HAS_ERROR.formatMessage(""));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ApiException(ResponseStatus.CALL_HTTP_HAS_ERROR.formatMessage(e.getMessage()));
        }
    }

    public static <T> T postForEntity(String url, Object body, ParameterizedTypeReference<T> responseType,
                                      String token, Duration timeout, HttpHeaders customHeaders) throws ApiException {
        try {

            HttpHeaders httpHeaders;
            httpHeaders = Objects.requireNonNullElseGet(customHeaders, RestFactory::getDefaultHttpHeaders);
            if (token != null) {
                httpHeaders.add(HttpHeaders.AUTHORIZATION, token);
            }

            RestTemplate restTemplate;
            if (timeout != null) {
                restTemplate = (new RestTemplateBuilder()).setReadTimeout(timeout).build();
            } else {
                restTemplate = new RestTemplate();
            }

            HttpEntity<Object> request = new HttpEntity<>(body, httpHeaders);

            ResponseEntity<T> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    responseType
            );
            if (HttpStatus.OK.equals(response.getStatusCode()) && response.getBody() != null) {
                return response.getBody();
            }
            throw new ApiException(ResponseStatus.CALL_HTTP_HAS_ERROR.formatMessage(""));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ApiException(ResponseStatus.CALL_HTTP_HAS_ERROR.formatMessage(e.getMessage()));
        }
    }

    public static <T> T getForEntity(String url, ParameterizedTypeReference<T> responseType) throws ApiException {
        try {
            HttpHeaders httpHeaders = getDefaultHttpHeaders();
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<Object> request = new HttpEntity<>(httpHeaders);
            ResponseEntity<T> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    request,
                    responseType
            );
            if (HttpStatus.OK.equals(response.getStatusCode()) && response.getBody() != null) {
                return response.getBody();
            }
            throw new ApiException(ResponseStatus.CALL_HTTP_HAS_ERROR.formatMessage(""));
        } catch (RestClientException e) {
            e.printStackTrace();
            throw new ApiException(ResponseStatus.CALL_HTTP_HAS_ERROR.formatMessage(e.getMessage()));
        }
    }

    public static <T> T putForEntity(String url, Object body, ParameterizedTypeReference<T> responseType, String token) throws ApiException {
        try {
            HttpHeaders httpHeaders = getDefaultHttpHeaders();
            if (token != null) {
                httpHeaders.add(HttpHeaders.AUTHORIZATION, token);
            }
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<Object> request = new HttpEntity<>(body, httpHeaders);
            ResponseEntity<T> response = restTemplate.exchange(
                    url,
                    HttpMethod.PUT,
                    request,
                    responseType
            );
            if (HttpStatus.OK.equals(response.getStatusCode()) && response.getBody() != null) {
                return response.getBody();
            }
            throw new ApiException(ResponseStatus.CALL_HTTP_HAS_ERROR.formatMessage(""));
        } catch (RestClientException e) {
            e.printStackTrace();
            throw new ApiException(ResponseStatus.CALL_HTTP_HAS_ERROR.formatMessage(e.getMessage()));
        }
    }

    public static <T> T patchForEntity(String url, Object body, ParameterizedTypeReference<T> responseType, String token) throws ApiException {
        try {
            HttpHeaders httpHeaders = getDefaultHttpHeaders();
            if (token != null) {
                httpHeaders.add(HttpHeaders.AUTHORIZATION, token);
            }
            RestTemplate restTemplate = new RestTemplate();
            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
            restTemplate.setRequestFactory(requestFactory);

            HttpEntity<Object> request = new HttpEntity<>(body, httpHeaders);
            ResponseEntity<T> response = restTemplate.exchange(
                    url,
                    HttpMethod.PATCH,
                    request,
                    responseType
            );
            if (HttpStatus.OK.equals(response.getStatusCode()) && response.getBody() != null) {
                return response.getBody();
            }
            throw new ApiException(ResponseStatus.CALL_HTTP_HAS_ERROR.formatMessage(""));
        } catch (RestClientException e) {
            e.printStackTrace();
            throw new ApiException(ResponseStatus.CALL_HTTP_HAS_ERROR.formatMessage(e.getMessage()));
        }
    }

    public static <T> T deleteForEntity(String url, Object body, ParameterizedTypeReference<T> responseType, String token) throws ApiException {
        try {
            HttpHeaders httpHeaders = getDefaultHttpHeaders();
            if (token != null) {
                httpHeaders.add(HttpHeaders.AUTHORIZATION, token);
            }
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<Object> request = new HttpEntity<>(body, httpHeaders);
            ResponseEntity<T> response = restTemplate.exchange(
                    url,
                    HttpMethod.DELETE,
                    request,
                    responseType
            );
            if (HttpStatus.OK.equals(response.getStatusCode()) && response.getBody() != null) {
                return response.getBody();
            }
            throw new ApiException(ResponseStatus.CALL_HTTP_HAS_ERROR.formatMessage(""));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ApiException(ResponseStatus.CALL_HTTP_HAS_ERROR.formatMessage(e.getMessage()));
        }
    }

    private static HttpHeaders getDefaultHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
        return httpHeaders;
    }
}
