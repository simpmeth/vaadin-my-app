package id.platforma.geo.application.entity.graphql.base;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class TypedEntity extends UniqueEntity {
    private EntityType type;
}
