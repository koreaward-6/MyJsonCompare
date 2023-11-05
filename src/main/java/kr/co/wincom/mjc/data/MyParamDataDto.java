package kr.co.wincom.mjc.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyParamDataDto {

    private String method;
    private String leftUrl;
    private String rightUrl;
    private String messageBody;

    public String getRightUrl() {
        String myUrl = null;

        if (this.rightUrl.endsWith("/")) {
            myUrl = this.rightUrl.substring(0, this.rightUrl.length() - 1);
        } else {
            myUrl = this.rightUrl;
        }

        return myUrl;
    }
}
