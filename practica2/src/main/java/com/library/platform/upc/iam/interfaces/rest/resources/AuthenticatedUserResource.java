package com.library.platform.upc.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(Long id, String username, String token) {
}
