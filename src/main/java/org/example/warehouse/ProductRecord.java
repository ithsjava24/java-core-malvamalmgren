package org.example.warehouse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public record ProductRecord(UUID uuid, String name, Category category, BigDecimal price){
}