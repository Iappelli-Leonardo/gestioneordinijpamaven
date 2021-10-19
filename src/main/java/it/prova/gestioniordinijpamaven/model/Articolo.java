package it.prova.gestioniordinijpamaven.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "articolo")
public class Articolo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "descrizione")
	private String descrizione;

	@Column(name = "prezzoSingolo")
	private int autore;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "articoli")
	private Set<Categoria> categorie = new HashSet<Categoria>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ordine_id")
	private Ordine ordine;

	public Articolo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Articolo(String descrizione, int autore) {
		super();
		this.descrizione = descrizione;
		this.autore = autore;
	}

	public Articolo(String descrizione, int autore, Set<Categoria> categorie, Ordine ordine) {
		super();
		this.descrizione = descrizione;
		this.autore = autore;
		this.categorie = categorie;
		this.ordine = ordine;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getAutore() {
		return autore;
	}

	public void setAutore(int autore) {
		this.autore = autore;
	}

	public Set<Categoria> getCategorie() {
		return categorie;
	}

	public void setCategorie(Set<Categoria> categorie) {
		this.categorie = categorie;
	}

	public Ordine getOrdine() {
		return ordine;
	}

	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}

	@Override
	public String toString() {
		return "Articolo [id=" + id + ", descrizione=" + descrizione + ", autore=" + autore + ", categorie=" + categorie
				+ ", ordine=" + ordine + "]";
	}

}
