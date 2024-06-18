package fr.iamdamba.entities;

import java.util.ArrayList;
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
@NoArgsConstructor
public class Competition {
  /** Id de la compétition */
  @Id
  private String id;

  /** Code de la compétition */
  @Column(name = "competition_code")
  private String competitionCode;

  /** Nom de la compétition */
  @Column(name = "name")
  private String name;

  /** Type de la compétition */
  @Column(name = "sous_type")
  private String sousType;

  /** Type de la compétition */
  @Column(name = "type")
  private String type;

  /** Id du pays */
  @Column(name = "id_pays")
  private Integer idPays;

  /** Nom du pays */
  @Column(name = "nom_pays")
  private String nomPays;

  /** Code du ligue hebergeur */
  @Column(name = "code_ligue_hebergeur")
  private String codeLigueHebergeur;

  /** Confederation */
  @Column(name = "confederation")
  private String confederation;

  /** Url */
  @Column(name = "url")
  private String url;

  /** Liste des clubs */
  @OneToMany(mappedBy = "competition")
  private List<Club> clubs;

  /** Liste des matchs */
  @OneToMany(mappedBy = "competition")
  private List<Match> matches;
  
  /** Liste des apparitions */
  @OneToMany(mappedBy = "competition")
  private List<Apparition> apparitions;

  /** Affichage a compétition */
  @Override
  public String toString() {
    return "Competition [id=" + id + ", competitionCode=" + competitionCode + ", name=" + name + ", sousType="
        + sousType + ", type=" + type + ", idPays=" + idPays + ", nomPays=" + nomPays + ", codeLigueHebergeur="
        + codeLigueHebergeur + ", confederation=" + confederation + ", url=" + url + "]";
  }

}
