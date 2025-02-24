package org.ghostprotocol.service.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VaultStorageConfiguration {
    @JsonProperty
    private String bucketName;

    @JsonProperty
    private String region;

    public String getBucketName() {
        return bucketName;
    }

    public String getRegion() {
        return region;
    }
}
