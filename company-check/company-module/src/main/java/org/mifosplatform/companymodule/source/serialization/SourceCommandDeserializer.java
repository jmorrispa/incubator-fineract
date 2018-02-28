package org.mifosplatform.companymodule.source.serialization;

import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.mifosplatform.companymodule.exceptions.InvalidJsonException;
import org.mifosplatform.companymodule.exceptions.DataValidationException;
import org.mifosplatform.companymodule.utils.ApiParameterError;
import org.mifosplatform.companymodule.utils.DataValidatorBuilder;
import org.mifosplatform.companymodule.utils.JsonParserHelper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.*;

@Component
public class SourceCommandDeserializer {

    private static final Set<String> supportedParameters = new HashSet<>(Arrays.asList("name", "status","createdOn","dateFormat",
            "locale","createdBy","approvedOn","approvedBy","rejectedOn","rejectedBy","updatedOn"));

    public static void validateForCreate(final String json) {
        if (StringUtils.isBlank(json)) { throw new InvalidJsonException(); }

        final Type typeOfMap = new TypeToken<Map<String, Object>>() {}.getType();
        JsonParserHelper.checkForUnsupportedParameters(typeOfMap, json, supportedParameters);

        final List<ApiParameterError> dataValidationErrors = new ArrayList<>();
        final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("source");

        final JsonElement element = JsonParserHelper.parse(json);

        final String name = JsonParserHelper.extractStringNamed("name", element);
        baseDataValidator.reset().parameter("name").value(name).notBlank().notExceedingLengthOf(100);

        final String createdOn = JsonParserHelper.extractStringNamed("createdOn", element);
        baseDataValidator.reset().parameter("createdOn").value(createdOn).notBlank();

        throwExceptionIfValidationWarningsExist(dataValidationErrors);
    }

    public static void validateForUpdate(final String json) {
        if (StringUtils.isBlank(json)) { throw new InvalidJsonException(); }

        final Type typeOfMap = new TypeToken<Map<String, Object>>() {}.getType();
        JsonParserHelper.checkForUnsupportedParameters(typeOfMap, json, supportedParameters);

        final List<ApiParameterError> dataValidationErrors = new ArrayList<>();
        final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("source");

        final JsonElement element = JsonParserHelper.parse(json);

        final String name = JsonParserHelper.extractStringNamed("name", element);
        baseDataValidator.reset().parameter("name").value(name).notBlank().notExceedingLengthOf(100);

        final String updatedOn = JsonParserHelper.extractStringNamed("updatedOn", element);
        baseDataValidator.reset().parameter("updatedOn").value(updatedOn).notBlank();

        throwExceptionIfValidationWarningsExist(dataValidationErrors);
   }

    public static void validateForApprove(final String json) {
        if (StringUtils.isBlank(json)) { throw new InvalidJsonException(); }

        final Type typeOfMap = new TypeToken<Map<String, Object>>() {}.getType();
        JsonParserHelper.checkForUnsupportedParameters(typeOfMap, json, supportedParameters);

        final List<ApiParameterError> dataValidationErrors = new ArrayList<>();
        final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("source");

        final JsonElement element = JsonParserHelper.parse(json);

        final String approvedOn = JsonParserHelper.extractStringNamed("approvedOn", element);
        baseDataValidator.reset().parameter("approvedOn").value(approvedOn).notBlank();

        throwExceptionIfValidationWarningsExist(dataValidationErrors);
    }

    public static void validateForReject(final String json) {
        if (StringUtils.isBlank(json)) { throw new InvalidJsonException(); }

        final Type typeOfMap = new TypeToken<Map<String, Object>>() {}.getType();
        JsonParserHelper.checkForUnsupportedParameters(typeOfMap, json, supportedParameters);

        final List<ApiParameterError> dataValidationErrors = new ArrayList<>();
        final DataValidatorBuilder baseDataValidator = new DataValidatorBuilder(dataValidationErrors).resource("source");

        final JsonElement element = JsonParserHelper.parse(json);

        final String rejectedOn = JsonParserHelper.extractStringNamed("rejectedOn", element);
        baseDataValidator.reset().parameter("rejectedOn").value(rejectedOn).notBlank();

        throwExceptionIfValidationWarningsExist(dataValidationErrors);
    }

    private static void throwExceptionIfValidationWarningsExist(final List<ApiParameterError> dataValidationErrors) {
        if (!dataValidationErrors.isEmpty()) { throw new DataValidationException(dataValidationErrors); }
    }
}