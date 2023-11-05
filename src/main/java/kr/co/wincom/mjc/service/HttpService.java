package kr.co.wincom.mjc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.concurrent.Future;

@Slf4j
@Service
@RequiredArgsConstructor
public class HttpService {

    private final RestTemplate restTemplate;

    @Async
    public Future<String> httpCall(String method, String strUrl, String body) throws Exception {
        String result = null;
        ResponseEntity<String> responseEntity = null;
        HttpEntity<String> entity = null;

        log.info("[{}][{}][{}]", method, strUrl, body);

        HttpMethod httpMethod = HttpMethod.resolve(method);
        URI uri = new URI(strUrl.trim());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        if (StringUtils.isBlank(body)) {
            entity = new HttpEntity<>(headers);
        } else {
            entity = new HttpEntity<>(body, headers);
        }

        responseEntity = this.restTemplate.exchange(uri, httpMethod, entity, String.class);

        if (responseEntity != null) {
            result = responseEntity.getBody();
        }

        return new AsyncResult<>(StringUtils.defaultIfBlank(result, ""));
    }
}

















