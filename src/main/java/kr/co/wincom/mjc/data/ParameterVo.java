package kr.co.wincom.mjc.data;

import kr.co.wincom.mjc.util.CommonUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@Getter
@ToString
@AllArgsConstructor
public class ParameterVo {

    private String method;
    private String leftUrl;
    private String rightUrl;
    private String messageBody;

    public void validation() {
        if (StringUtils.isBlank(this.method) || StringUtils.isBlank(this.leftUrl) || StringUtils.isBlank(this.rightUrl)) {
            throw new RuntimeException("파라미터 오류!!!");
        }

        if (!CommonUtils.isValidURL(this.leftUrl) || !CommonUtils.isValidURL(this.rightUrl)) {
            throw new RuntimeException("파라미터 오류!!!");
        }
    }

    public MyParamDataDto makeRefinedRequest() {
        return MyParamDataDto.builder()
            .method(this.method)
            .leftUrl(this.leftUrl)
            .rightUrl(this.rightUrl)
            .messageBody(this.messageBody)
            .build();
    }
}
