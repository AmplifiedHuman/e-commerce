package ie.ucd.ibot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InstantSearchResult {
    private long id;
    private String name;
    private double price;
}
