package it.prova.gestioniordinijpamaven.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "descrizione")
	private String descrizione;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "categoria_articolo", joinColumns = @JoinColumn(name = "categoria_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "genere_id", referencedColumnName = "ID"))
	private Set<Articolo> articoli = new HashSet<Articolo>();

	public Categoria() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Categoria(String descrizione) {
		super();
		this.descrizione = descrizione;
	}

	public Categoria(String descrizione, Set<Articolo> articoli) {
		super();
		this.descrizione = descrizione;
		this.articoli = articoli;
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

	public Set<Articolo> getArticoli() {
		return articoli;
	}

	public void setArticoli(Set<Articolo> articoli) {
		this.articoli = articoli;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", descrizione=" + descrizione + ", articoli=" + articoli + "]";
	}

}
