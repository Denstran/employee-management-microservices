package org.example.administrationservice.event;

import lombok.Data;

@Data
public class PositionLeadingChanged {
    private final Long positionChanged;
    private final boolean leading;
}
