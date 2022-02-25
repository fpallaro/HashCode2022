package it.synclab;

import java.util.ArrayList;
import java.util.List;

public class Progetto {

	String name;
	int giornate;
	int score;
	int bestBefore;
	int totRuoli;
	List<Competenza> listaCompetenze = new ArrayList<Competenza>();
	
	List<Contributor> listaContributor = new ArrayList<Contributor>();
	
	
	
	
	public List<Contributor> getListaContributor() {
		return listaContributor;
	}
	public void setListaContributor(List<Contributor> listaContributor) {
		this.listaContributor = listaContributor;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGiornate() {
		return giornate;
	}
	public void setGiornate(int giornate) {
		this.giornate = giornate;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getBestBefore() {
		return bestBefore;
	}
	public void setBestBefore(int bestBefore) {
		this.bestBefore = bestBefore;
	}
	public int getTotRuoli() {
		return totRuoli;
	}
	public void setTotRuoli(int totRuoli) {
		this.totRuoli = totRuoli;
	}
	public List<Competenza> getListaCompetenze() {
		return listaCompetenze;
	}
	public void setListaCompetenze(List<Competenza> listaCompetenze) {
		this.listaCompetenze = listaCompetenze;
	}
	
	

}
