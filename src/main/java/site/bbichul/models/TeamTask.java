package site.bbichul.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.bbichul.dto.TeamTaskRequestDto;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.

public class TeamTask extends TimeStamped {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false, length = 500)
    private String task;

    @Column(nullable = false)
    private Boolean done;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    public TeamTask(TeamTaskRequestDto teamTaskRequestDto) {
        this.task = teamTaskRequestDto.getTask();
        this.done = teamTaskRequestDto.getDone();
    }

    public void taskUpdate(TeamTaskRequestDto requestDto) {
        this.task = requestDto.getTask();
    }

    public TeamTask(TeamTaskRequestDto teamTaskRequestDto, Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("to do list task id가 유효하지 않습니다.");
        }

        if(teamTaskRequestDto.getTask() == null) {
            throw new IllegalArgumentException("공백은 입력할 수 없습니다.");
        }

        this.id = teamTaskRequestDto.getId();
        this.done = teamTaskRequestDto.getDone();
        this.task = teamTaskRequestDto.getTask();
    }
}
