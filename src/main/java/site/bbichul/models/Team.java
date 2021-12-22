package site.bbichul.models;

import lombok.*;
import site.bbichul.dto.TeamRequestDto;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.


public class Team {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false, length = 200)
    private String teamname;

    public Team(String teamname) {
        this.teamname = teamname;
    }


    public Team(String teamname, Long id) {
        this.teamname = teamname;
        this.id = id;
    }

    public Team(TeamRequestDto teamRequestDto) {
        this.teamname = teamRequestDto.getTeamname();
        this.id = teamRequestDto.getId();
    }
//    public Team(TeamRequestDto requestDto, Long id) {
//        if (id == null || id < 0) {
//            throw new IllegalArgumentException("팀 id가 유효하지 않습니다.");
//        }
//
//        if(requestDto.getTeamname() == teamname) {
//            throw new IllegalArgumentException("중복된 팀 이름입니다.");
//        }
//
//        this.id = requestDto.getId();
//        this.teamname = requestDto.getTeamname();
//    }

}
