/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.grpc;

import io.grpc.Metadata;
import io.grpc.Status;
import java.util.Optional;

/**
 * Interface to be implemented by our custom exceptions that are consistently mapped to a gRPC status.
 */
public interface ConvertibleToGrpcStatus {

  Status grpcStatus();

  Optional<Metadata> grpcMetadata();
}
