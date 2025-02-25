package org.ghostprotocol.service.security.notes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data class for storing note content and metadata.
 * This class is serialized and encrypted before being stored in the vault.
 */
public class NoteData {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    
    private final String content;
    private final String category;
    private final List<String> tags;
    private final boolean pinned;
    
    public NoteData(
            @JsonProperty("content") String content,
            @JsonProperty("category") String category,
            @JsonProperty("tags") List<String> tags,
            @JsonProperty("pinned") boolean pinned) {
        this.content = content;
        this.category = category;
        this.tags = tags != null ? tags : new ArrayList<>();
        this.pinned = pinned;
    }
    
    @JsonProperty
    public String getContent() {
        return content;
    }
    
    @JsonProperty
    public String getCategory() {
        return category;
    }
    
    @JsonProperty
    public List<String> getTags() {
        return tags;
    }
    
    @JsonProperty
    public boolean isPinned() {
        return pinned;
    }
    
    /**
     * Serializes this object to a JSON string.
     * 
     * @return JSON string representation of this object
     * @throws RuntimeException if serialization fails
     */
    public String serialize() {
        try {
            return MAPPER.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize note data", e);
        }
    }
    
    /**
     * Deserializes a JSON string to a NoteData object.
     * 
     * @param json JSON string to deserialize
     * @return NoteData object
     * @throws RuntimeException if deserialization fails
     */
    public static NoteData deserialize(String json) {
        try {
            return MAPPER.readValue(json, NoteData.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to deserialize note data", e);
        }
    }
}
