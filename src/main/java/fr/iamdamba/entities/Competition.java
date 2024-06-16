package fr.iamdamba.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Competition {
  @Id
  private String id;

  @Column(name = "competition_code")
  private String competitionCode;

  @Column(name = "name")
  private String name;

  @Column(name = "sous_type")
  private String sousType;

  @Column(name = "type")
  private String type;

  @Column(name = "id_pays")
  private Integer idPays;

  @Column(name = "nom_pays")
  private String nomPays;

  @Column(name = "code_ligue_hebergeur")
  private String codeLigueHebergeur;

  @Column(name = "confederation")
  private String confederation;

  @Column(name = "url")
  private String url;

  @OneToMany(mappedBy = "competition")
  private List<Club> clubs;

  @OneToMany(mappedBy = "competition")
  private List<Match> matches;

  @OneToMany(mappedBy = "competition")
  private List<Apparition> apparitions;

}
