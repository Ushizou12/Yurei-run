package n.m.com.br.Yurei_run.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_jogador")
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nickname;
    private Integer melhorScore;
    private Integer moedas;

    public Jogador () {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getMelhorScore() {
        return melhorScore;
    }

    public void setMelhorScore(Integer melhorScore) {
        this.melhorScore = melhorScore;
    }

    public Integer getMoedas() {
        return moedas;
    }

    public void setMoedas(Integer moedas) {
        this.moedas = moedas;
    }
}
