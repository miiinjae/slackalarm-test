package site.bbichul.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TeamRequestDto {
    private Long id;
    private String teamname;
}
