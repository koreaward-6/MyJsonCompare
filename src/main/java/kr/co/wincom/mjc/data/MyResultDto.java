package kr.co.wincom.mjc.data;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyResultDto {

    private String leftUrl;
    private String rightUrl;
    private String leftData;
    private String rightData;
    private String sameYn;
}
