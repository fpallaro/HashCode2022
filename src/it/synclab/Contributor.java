package it.synclab;

import java.util.ArrayList;
import java.util.List;

public class Contributor {

	String name;
	List<Competenza> elencoCompetenze;
	boolean isFree=true;
	

	public boolean isFree() {
		return isFree;
	}

	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}

	public List<Competenza> getElencoCompetenze() {
		return elencoCompetenze;
	}

	public void setElencoCompetenze(List<Competenza> elencoCompetenze) {
		this.elencoCompetenze = elencoCompetenze;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}
