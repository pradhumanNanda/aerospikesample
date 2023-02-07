import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
public class User implements java.io.Serializable {

    private String name;

    private String userId;


    public User(String name, String id) {
        this.name = name;
        this.userId = id;
    }
}