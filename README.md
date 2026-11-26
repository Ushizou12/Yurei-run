# Yurei-run

Trabalho final da disciplina **Desenvolvimento Web II (2026)** — Análise e Desenvolvimento de Sistemas, IFRS Campus Osório.

## Objetivo

**Yurei-run** é um jogo 2D de corrida infinita no estilo do jogo do dinossauro do Chrome. O jogador controla a fantasma **Yurei**, que corre automaticamente enquanto **Yokais do folclore japonês** avançam em sua direção. Os controles são `W` (sobe) e `S` (desce).

O jogo é feito em **Godot** e se comunica com uma aplicação **Java Spring Boot**, que guarda no **PostgreSQL** os jogadores, os recordes e as partidas, e oferece telas web para consulta e administração.

## Integrantes

- Nicolas Paulo Portulan de Araujo
- [A CONFIRMAR: nome do segundo integrante]

## Arquitetura

```
Godot (GDScript)  ──HTTP/JSON──►  Spring Boot  ──►  PostgreSQL
 jogo e mecânicas                 API REST + telas web (Thymeleaf)
```

- **Godot:** movimentação, geração dos inimigos (Yokais), colisão, pontuação e fim de jogo. Os inimigos são gerados pelo próprio jogo e **não** ficam no banco de dados.
- **Spring Boot:** regras de negócio, persistência, API REST para o jogo e telas web.
- **PostgreSQL:** dados que precisam persistir entre as sessões de jogo.

## Tecnologias e versões

| Item | Versão |
|---|---|
| Java | 27 (não é LTS: é preciso ter o mesmo JDK instalado) |
| Spring Boot | 4.1.2 |
| Maven | 3.10.0, via Maven Wrapper (`mvnw`), sem instalação própria |
| Persistência | Spring Data JPA (Hibernate) |
| Banco de dados | PostgreSQL [A CONFIRMAR: versão] |
| Jogo | Godot [A CONFIRMAR: versão] |

## Pré-requisitos

1. JDK 27 instalado, com `JAVA_HOME` configurado ou `java` no `PATH`.
2. PostgreSQL instalado e em execução na porta padrão (`5432`).
3. Godot [A CONFIRMAR: versão] para abrir e executar o jogo.

## Como executar

### 1. Banco de dados

Crie o banco no PostgreSQL:

```sql
CREATE DATABASE yurei_run;
```

A aplicação usa o usuário `postgres` e o host `localhost` (veja `src/main/resources/application.properties`). As tabelas são criadas automaticamente pelo Hibernate na primeira execução (`spring.jpa.hibernate.ddl-auto=update`).

[A CONFIRMAR: scripts `schema.sql`/`data.sql` na pasta `database/` ou `docker-compose.yml` com o PostgreSQL]

### 2. Senha do banco

A senha **não** fica no repositório. Defina a variável de ambiente `DB_PASSWORD` antes de iniciar a aplicação:

- Linux/macOS: `export DB_PASSWORD=sua_senha`
- Windows (PowerShell): `$env:DB_PASSWORD="sua_senha"`

### 3. Backend

Na raiz do projeto:

- Linux/macOS: `./mvnw spring-boot:run`
- Windows: `mvnw.cmd spring-boot:run`

Por padrão, a aplicação sobe em `http://localhost:8080`.

### 4. Jogo

[A CONFIRMAR: forma de entrega do jogo — projeto Godot executado separadamente ou exportação HTML5 servida pelo Spring Boot]

## Funcionalidades

Legenda: ✅ implementado e testado · 🚧 em desenvolvimento · 📝 planejado.

| Funcionalidade | Status |
|---|---|
| Cadastro de jogador por nickname (feito pelo jogo, via API) | 🚧 |
| Registro do resultado de cada partida | 🚧 |
| Recorde (melhor score) por jogador | 🚧 |
| Tela web com o **Top 10** maiores scores | 🚧 |
| Cadastro completo (incluir, editar, excluir, listar) na interface web | 🚧 [A CONFIRMAR: entidade — proposta: `Skin`] |
| Moedas ganhas ao fim de cada partida | 📝 |
| Loja de skins para a Yurei, comprada com moedas | 📝 |
| Jogo em Godot integrado à API | 🚧 |

> Remover/atualizar esta tabela conforme o que estiver realmente funcionando na entrega.

## Modelo de dados

```
tb_jogador                      tb_partida
──────────                      ──────────
id (PK)                         id (PK)
nickname (único)                segundos
melhor_score  (planejado)       data
moedas        (planejado)       jogador_id (FK → tb_jogador)
```

Um **jogador** tem várias **partidas**. O `melhor_score` só aumenta e alimenta o ranking. As `moedas` são o saldo gastável na loja; ficam separadas do recorde para que uma compra não altere o ranking.

Entidades planejadas para a loja: `Skin` (nome, preço, sprite, ativo), a relação de skins que cada jogador possui e a skin equipada.

## Organização do código

```
src/main/java/n/m/com/br/Yurei_run
├── model        # entidades JPA (Jogador, Partida, ...)
├── repository   # acesso a dados (Spring Data JPA, faz o papel da camada DAO)
├── service      # regras de negócio
└── controller   # telas web (Thymeleaf) e API REST usada pelo jogo
```

Os `Repository` do Spring Data JPA fazem o papel da camada de persistência (DAO) exigida no enunciado.

## API REST (usada pelo jogo)

Rotas planejadas, ainda sujeitas a ajuste:

| Método | Rota | Uso |
|---|---|---|
| POST | `/api/jogadores` | Cria ou identifica o jogador pelo nickname |
| POST | `/api/partidas` | Registra o resultado da partida e atualiza recorde e moedas |
| GET | `/api/ranking` | Retorna os 10 maiores scores |

## Limitações conhecidas

- O score é enviado pelo próprio jogo, sem autenticação. Um cliente alterado poderia enviar valores falsos. Isso é aceito no escopo da disciplina.
- A aplicação usa Java 27, que não é versão LTS.
