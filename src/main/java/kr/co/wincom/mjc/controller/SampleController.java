package kr.co.wincom.mjc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class SampleController {

    @GetMapping(value = "/getSample", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSample(@RequestParam("myParam") String myParam) throws Exception {
        Map<String, Object> mapBody = new HashMap<>();
        mapBody.put("key-1", myParam + "-get-val-1");
        mapBody.put("key-2", myParam + "-get-val-2");
        mapBody.put("key-3", myParam + "-get-val-3");

        List<Map<String, String>> listMessage = new ArrayList<>();
        Map<String, String> mapMessage = new HashMap<>();
        mapMessage.put("key-5", "-get-val-5");
        mapMessage.put("key-6", myParam + "-get-val-6");
        listMessage.add(mapMessage);
        mapBody.put("listMessage", listMessage);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(mapBody);

        return new ResponseEntity<>(jsonStr, HttpStatus.OK);
    }

    @PostMapping(value = "/postSample", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postSample(@RequestParam("myParam") String myParam) throws Exception {
        Map<String, Object> mapBody = new HashMap<>();
        mapBody.put("key-1", myParam + "-post-val-1");
        mapBody.put("key-2", myParam + "-post-val-2");
        mapBody.put("key-3", myParam + "-post-val-3");

        List<Map<String, String>> listMessage = new ArrayList<>();
        Map<String, String> mapMessage = new HashMap<>();
        mapMessage.put("key-5", "-post-val-5");
        mapMessage.put("key-6", myParam + "-post-val-6");
        listMessage.add(mapMessage);
        mapBody.put("listMessage", listMessage);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(mapBody);

        return new ResponseEntity<>(jsonStr, HttpStatus.OK);
    }

    @PutMapping(value = "/putSample", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> putSample(@RequestParam("myParam") String myParam) throws Exception {
        Map<String, Object> mapBody = new HashMap<>();
        mapBody.put("key-1", myParam + "-put-val-1");
        mapBody.put("key-2", myParam + "-put-val-2");
        mapBody.put("key-3", myParam + "-put-val-3");

        List<Map<String, String>> listMessage = new ArrayList<>();
        Map<String, String> mapMessage = new HashMap<>();
        mapMessage.put("key-5", "-put-val-5");
        mapMessage.put("key-6", myParam + "-put-val-6");
        listMessage.add(mapMessage);
        mapBody.put("listMessage", listMessage);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(mapBody);

        return new ResponseEntity<>(jsonStr, HttpStatus.OK);
    }

    @PatchMapping(value = "/patchSample", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> patchSample(@RequestParam("myParam") String myParam) throws Exception {
        Map<String, Object> mapBody = new HashMap<>();
        mapBody.put("key-1", myParam + "-patch-val-1");
        mapBody.put("key-2", myParam + "-patch-val-2");
        mapBody.put("key-3", myParam + "-patch-val-3");

        List<Map<String, String>> listMessage = new ArrayList<>();
        Map<String, String> mapMessage = new HashMap<>();
        mapMessage.put("key-5", "-patch-val-5");
        mapMessage.put("key-6", myParam + "-patch-val-6");
        listMessage.add(mapMessage);
        mapBody.put("listMessage", listMessage);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(mapBody);

        return new ResponseEntity<>(jsonStr, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteSample", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteSample(@RequestParam("myParam") String myParam) throws Exception {
        Map<String, Object> mapBody = new HashMap<>();
        mapBody.put("key-1", myParam + "-delete-val-1");
        mapBody.put("key-2", myParam + "-delete-val-2");
        mapBody.put("key-3", myParam + "-delete-val-3");

        List<Map<String, String>> listMessage = new ArrayList<>();
        Map<String, String> mapMessage = new HashMap<>();
        mapMessage.put("key-5", "-delete-val-5");
        mapMessage.put("key-6", myParam + "-delete-val-6");
        listMessage.add(mapMessage);
        mapBody.put("listMessage", listMessage);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(mapBody);

        return new ResponseEntity<>(jsonStr, HttpStatus.OK);
    }

    @PostMapping(value = "/postBodySample", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postBodySample(
            @RequestParam("myParam") String myParam
            , @RequestBody String bodyData
    ) throws Exception {
        log.info("[{}]", bodyData);

        Map<String, Object> mapBody = new HashMap<>();
        mapBody.put("key-1", myParam + "-post-val-1");
        mapBody.put("key-2", myParam + "-post-val-2");
        mapBody.put("key-3", myParam + "-post-val-3");

        List<Map<String, String>> listMessage = new ArrayList<>();
        Map<String, String> mapMessage = new HashMap<>();
        mapMessage.put("key-5", "-post-val-5");
        mapMessage.put("key-6", myParam + "-post-val-6");
        listMessage.add(mapMessage);
        mapBody.put("listMessage", listMessage);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(mapBody);

        return new ResponseEntity<>(jsonStr, HttpStatus.OK);
    }
}
