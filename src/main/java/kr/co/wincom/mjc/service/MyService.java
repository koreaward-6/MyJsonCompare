package kr.co.wincom.mjc.service;

import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;
import kr.co.wincom.mjc.data.MyParamDataDto;
import kr.co.wincom.mjc.data.MyResultDto;
import kr.co.wincom.mjc.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyService {

    private final HttpService httpService;

    public List<MyResultDto> compare(List<MyParamDataDto> myDataList) throws Exception {
        List<Future<String>> leftFutureList = new ArrayList<>();
        List<Future<String>> rightFutureList = new ArrayList<>();
        List<MyResultDto> tempList = new ArrayList<>();
        List<MyResultDto> myResultList = new ArrayList<>();

        for (MyParamDataDto dto : myDataList) {
            leftFutureList.add(this.httpService.httpCall(dto.getMethod(), dto.getLeftUrl(), dto.getMessageBody()));
            rightFutureList.add(this.httpService.httpCall(dto.getMethod(), CommonUtils.makeUrl(dto.getLeftUrl(), dto.getRightUrl()), dto.getMessageBody()));
        }

        while (true) {
            boolean isDone = true;

            TimeUnit.MILLISECONDS.sleep(1);

            for (int i = 0; i < leftFutureList.size(); i++) {
                Future<String> leftFuture = leftFutureList.get(i);
                Future<String> rightFuture = rightFutureList.get(i);

                if (!leftFuture.isDone() || !rightFuture.isDone()) {
                    isDone = false;
                }
            }

            if (isDone) {
                break;
            }
        }

        for (int i = 0; i < leftFutureList.size(); i++) {
            Future<String> leftFuture = leftFutureList.get(i);
            Future<String> rightFuture = rightFutureList.get(i);

            tempList.add(MyResultDto.builder()
                    .leftUrl(myDataList.get(i).getLeftUrl())
                    .rightUrl(CommonUtils.makeUrl(myDataList.get(i).getLeftUrl(), myDataList.get(i).getRightUrl()))
                    .leftData(CommonUtils.makeJsonPrettyData(leftFuture.get()))
                    .rightData(CommonUtils.makeJsonPrettyData(rightFuture.get()))
                    .build());
        }

        DiffRowGenerator generator = DiffRowGenerator.create()
                .showInlineDiffs(true)
                .inlineDiffByWord(true)
                .oldTag(f -> "")
                .newTag(f -> "")
                .build();

        for (MyResultDto dto : tempList) {
            String sameYn = "Y";
            StringBuilder leftSb = new StringBuilder();
            StringBuilder rightSb = new StringBuilder();

            String leftData = dto.getLeftData();
            String rightData = dto.getRightData();

            String[] arrLeftData = StringUtils.splitByWholeSeparatorPreserveAllTokens(leftData, "\n");
            String[] arrRightData = StringUtils.splitByWholeSeparatorPreserveAllTokens(rightData, "\n");

            List<DiffRow> diffRowList = generator.generateDiffRows(Arrays.asList(arrLeftData), Arrays.asList(arrRightData));

            for (DiffRow row : diffRowList) {
                String leftDiffTrimData = row.getOldLine().trim();
                String rightDiffTrimData = row.getNewLine().trim();
                String leftDiffData = row.getOldLine();
                String rightDiffData = row.getNewLine();

                if (StringUtils.isBlank(leftDiffData)) {
                    leftDiffData = rightDiffData.replace(rightDiffTrimData, "") + "(NO DATA)";
                }

                if (StringUtils.isBlank(rightDiffData)) {
                    rightDiffData = leftDiffData.replace(leftDiffTrimData, "") + "(NO DATA)";
                }

                if (leftDiffData.equals(rightDiffData)) {
                    leftSb.append(CommonUtils.replaceColor(leftDiffData) + "<br>");
                    rightSb.append(CommonUtils.replaceColor(rightDiffData) + "<br>");
                } else {
                    sameYn = "N";
                    leftSb.append("<font color='#DF01D7'>" + leftDiffData.replace(" ", "&nbsp;").replace("<", "&lt;").replace(">", "&gt;") + "</font><br>");
                    rightSb.append("<font color='#DF01D7'>" + rightDiffData.replace(" ", "&nbsp;").replace("<", "&lt;").replace(">", "&gt;") + "</font><br>");
                }
            }

            myResultList.add(MyResultDto.builder()
                    .leftUrl(dto.getLeftUrl().replace("<", "&lt;").replace(">", "&gt;"))
                    .rightUrl(dto.getRightUrl().replace("<", "&lt;").replace(">", "&gt;"))
                    .leftData(leftSb.toString())
                    .rightData(rightSb.toString())
                    .sameYn(sameYn)
                    .build());
        }

        return myResultList;
    }
}


























