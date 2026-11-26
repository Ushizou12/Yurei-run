package n.m.com.br.Yurei_run.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_partida")
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer distância;
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "jogador_id", nullable = false)
    private Jogador jogador;

    public Partida () {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDistância() {
        return distância;
    }

    public void setDistância(Integer distância) {
        this.distância = distância;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }
}
