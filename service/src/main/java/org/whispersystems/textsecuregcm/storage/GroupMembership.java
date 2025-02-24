package org.whispersystems.textsecuregcm.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.UUID;

public class GroupMembership {
  public enum Role {
    MEMBER,
    ADMIN,
    OWNER
  }

  @JsonProperty
  private UUID groupId;

  @JsonProperty
  private UUID memberUuid;

  @JsonProperty
  private Role role;

  @JsonProperty
  private Instant joinedAt;

  public UUID getGroupId() { return groupId; }
  public void setGroupId(UUID groupId) { this.groupId = groupId; }

  public UUID getMemberUuid() { return memberUuid; }
  public void setMemberUuid(UUID memberUuid) { this.memberUuid = memberUuid; }

  public Role getRole() { return role; }
  public void setRole(Role role) { this.role = role; }

  public Instant getJoinedAt() { return joinedAt; }
  public void setJoinedAt(Instant joinedAt) { this.joinedAt = joinedAt; }
}
