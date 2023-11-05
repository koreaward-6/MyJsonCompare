package kr.co.wincom.mjc.controller;

import java.util.ArrayList;
import java.util.List;
import kr.co.wincom.mjc.data.MyParamDataDto;
import kr.co.wincom.mjc.data.MyResultDto;
import kr.co.wincom.mjc.data.ParameterVo;
import kr.co.wincom.mjc.service.MyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MyController {

    private final MyService myService;

    @RequestMapping("/")
    public String index() {
        return "/index";
    }

    @PostMapping(value = "/compare", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<MyResultDto>> compare(@RequestBody List<ParameterVo> parameterList) throws Exception {
        if (ObjectUtils.isEmpty(parameterList)) {
            throw new RuntimeException("파라미터 오류!!!");
        }

        // validation 체크
        parameterList.stream().forEach(vo -> vo.validation());

        List<MyParamDataDto> myDataList = new ArrayList<>();
        parameterList.stream().forEach(vo -> {
            myDataList.add(MyParamDataDto.builder()
                .method(vo.getMethod())
                .leftUrl(vo.getLeftUrl())
                .rightUrl(vo.getRightUrl())
                .messageBody(vo.getMessageBody())
                .build());
        });

        List<MyResultDto> list = this.myService.compare(myDataList);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}




























