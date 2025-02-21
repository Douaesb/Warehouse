package com.progresssoft.warehouse.exception;

import java.util.UUID;

public class DuplicateDealException extends RuntimeException {
    public DuplicateDealException(UUID dealId) {
        super("Duplicate Deal: A deal with ID " + dealId + " already exists.");
    }
}
