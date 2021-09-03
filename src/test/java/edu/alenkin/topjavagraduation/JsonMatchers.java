package edu.alenkin.topjavagraduation;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.alenkin.topjavagraduation.util.JsonUtil;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.function.BiConsumer;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class JsonMatchers {
    public static <T> void assertNoIdEquals(T actual, T expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("id").isEqualTo(expected);
    }

    public static <T> T parseJson(MvcResult mvcResult, Class<T> clazz) throws UnsupportedEncodingException, JsonProcessingException {
        String jsonActual = mvcResult.getResponse().getContentAsString();
        return JsonUtil.readValue(jsonActual, clazz);
    }

    public static <T> List<T> parseJsonToList(MvcResult mvcResult, Class<T> clazz) throws IOException {
        String jsonActual = mvcResult.getResponse().getContentAsString();
        return JsonUtil.readValues(jsonActual, clazz);
    }

    public static <T> ResultMatcher jsonMatcher(T expected, Class<T> clazz, BiConsumer<T, T> equalsAssertion) {
        return mvcResult -> equalsAssertion.accept(parseJson(mvcResult, clazz), expected);
    }

    public static <T> ResultMatcher jsonMatcher(List<T> expected, Class<T> clazz, BiConsumer<List<T>, List<T>> equalsAssertion) {
        return mvcResult -> equalsAssertion.accept(parseJsonToList(mvcResult, clazz), expected);
    }
}
