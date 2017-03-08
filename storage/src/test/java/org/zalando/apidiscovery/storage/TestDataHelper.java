package org.zalando.apidiscovery.storage;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.DateTime;

final class TestDataHelper {

    private TestDataHelper() {}

    public static ApiDefinition createUnsuccessfulApiDefinition() {
        ApiDefinition apiDefinition = createBasicApiDefinition();
        apiDefinition.setStatus("UNSUCCESSFUL");
        return apiDefinition;
    }

    public static ApiDefinition createInactiveApiDefinition() {
        ApiDefinition apiDefinition = createBasicApiDefinition();
        apiDefinition.setLifecycleState(ApiLifecycleManager.INACTIVE);
        return apiDefinition;
    }

    public static ApiDefinition createDecommissionedApiDefinition() {
        ApiDefinition apiDefinition = createBasicApiDefinition();
        apiDefinition.setLifecycleState(ApiLifecycleManager.DECOMMISSIONED);
        return apiDefinition;
    }

    public static ApiDefinition createBasicApiDefinition() {
        ApiDefinition apiDefinition = new ApiDefinition();
        apiDefinition.setApplicationId(RandomStringUtils.randomAlphabetic(20));
        apiDefinition.setStatus("SUCCESS");
        apiDefinition.setLifecycleState(ApiLifecycleManager.ACTIVE);
        apiDefinition.setLastPersisted(DateTime.now());
        apiDefinition.setLastChanged(apiDefinition.getLastPersisted());
        return apiDefinition;
    }
}
