package immobi.tec.immobitec.DTO;

import lombok.*;

import java.util.Date;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    private long roleId;
    private String name;
    private String function;

}
