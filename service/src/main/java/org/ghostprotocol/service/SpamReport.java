package org.ghostprotocol.ghostprotocol.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import javax.annotation.Nullable;
import org.ghostprotocol.ghostprotocol.util.ByteArrayAdapter;

public record SpamReport(@JsonSerialize(using = ByteArrayAdapter.Serializing.class)
                         @JsonDeserialize(using = ByteArrayAdapter.Deserializing.class)
                         @Nullable byte[] token) {}
