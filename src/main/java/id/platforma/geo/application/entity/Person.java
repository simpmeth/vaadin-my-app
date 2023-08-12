package id.platforma.geo.application.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person {
    private String fullName;
    private String firstName;
    private String lastName;
    private String email;
    private String profession;
    private String pictureUrl;
    private Address address;
}
