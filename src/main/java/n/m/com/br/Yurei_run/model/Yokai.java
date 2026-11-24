package n.m.com.br.Yurei_run.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_yokai")
public class Yokai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_yokai", nullable = false, length = 50)
    private String nome;
    @Column(name = "descricao_yokai", nullable = false, length = 255)
    private String descricao;
    @Column(name = "velocidade_yokai")
    private Double velocidade;
    @Column(name = "sprite_yokai")
    private String sprite;
    @Column(name = "ativo_yokai", nullable = false)
    private Boolean ativo = true;

    protected Yokai () { }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(Double velocidade) {
        this.velocidade = velocidade;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
