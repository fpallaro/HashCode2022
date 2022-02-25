package it.synclab;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class HashCode {
	String[] fileNames = new String[] { "a_an_example.in", "b_better_start_small.in", "c_collaboration.in",
			"d_dense_schedule.in", "e_exceptional_skills.in", "f_find_great_mentors.in" };
	List<Contributor> contribs = new ArrayList<Contributor>();
	List<Progetto> projs = new ArrayList<Progetto>();
	String delay = "1";
	int totContribs = 0;
	int totProj = 0;
	int totIntersezioni = 0;
	int totStrade = 0;
	int totAuto = 0;
	int bonusPoints = 0;

	// LinkedList<Pizza> listaConcPizze = new LinkedList<Pizza>();
	ArrayList<String> listaStradeFrequentate;
	Map<String, Integer> mappaStradeFrequentate;

	public static void main(String[] args) {

		System.out.println("Inizio Applicazione");
		HashCode app = new HashCode();
		app.run();
		System.out.println("\n\nFine Applicazione");

	}

	// Ritorna il contributore con livello e comp sufficiente
	private boolean checkCompLev(String skill, Integer level, Contributor contrib) {
		for (Competenza comp : contrib.getElencoCompetenze()) {
			if (skill.equals(comp.getName()) && level <= comp.getLevel()) {
				return true;
			}
		}
		return false;

	}

	// Ritorna il contributore con livello e comp sufficiente
	private boolean checkCompLevOtt(String skill, Integer level, Contributor contrib) {
		for (int i = 0; i < 11; i++) {
			for (Competenza comp : contrib.getElencoCompetenze()) {
				if (skill.equals(comp.getName()) && level == (comp.getLevel() - i)) {
					if (i == 0) {
						comp.setLevel(comp.getLevel() + 1);
						//contrib.setFree(true);
					}
					return true;
				}
				
			}

		}

		return false;

	}

	// ritorna il primo contrib libero con la competenza richiesta
	private Contributor getContribByCompLev(Competenza compRequired) {
		for (Contributor contrib : contribs) {
			if (contrib.isFree() && checkCompLevOtt(compRequired.getName(), compRequired.getLevel(), contrib)) {
				return contrib;
			}
		}

		return null;
	}

	private void run() {
		System.out.println("" + (new Date()));

		// for (int index = 5; index < 6; index++) {
		for (int index = 0; index < 6; index++) {
			leggiDati(fileNames[index]);

//			Collections.sort(projs, new Comparator<Progetto>() {
//				@Override
//				public int compare(Progetto proj1, Progetto proj2) {
//					return (proj2.getScore() - proj1.getScore());
//				}
//			});

			System.out.println("totContribs: " + totContribs);
			System.out.println("totProj: " + totProj);

			List<Progetto> out_projs = new ArrayList<Progetto>();

			for (Progetto proj : projs) {
				System.out.println(proj.getName());
				System.out.println("BB------------" + proj.getBestBefore());
				System.out.println("Score------------" + proj.getScore());
				boolean flagExit = false;
				List<Contributor> out_contribs = new ArrayList<Contributor>();
				for (Competenza comp : proj.getListaCompetenze()) {
					System.out.println(".." + comp.getName() + "-- " + comp.getLevel());
					Contributor contrib = getContribByCompLev(comp);
					if (contrib != null) {

						contrib.setFree(false);
						out_contribs.add(contrib);
						System.out.println("Contrib aggiunto " + contrib.getName());
					}
				}
				if (out_contribs.size() == proj.getListaCompetenze().size()) {
					System.out.println("AddProj---------------------------");
					Progetto out_proj = new Progetto();
					out_proj.setName(proj.getName());
					out_proj.setListaContributor(out_contribs);
					out_projs.add(out_proj);
				}
			}

			scriviFile(out_projs, fileNames[index]);
		}
	}

	private void scriviFile(List<Progetto> out_projs, String fileName) {
		try {
			System.out.println("---------------------------Scrivi File-------------------------");
			File file1 = new File("C:\\Fabio\\dev\\Google\\HashCode2022\\" + fileName + ".out");
			FileWriter fr1 = new FileWriter(file1);
			fr1.append(out_projs.size() + "\n");

			for (Progetto progetto : out_projs) {
				System.out.println("Progetto: " + progetto.getName());
				fr1.append(progetto.getName() + "\n");
				for (Contributor contrib : progetto.getListaContributor()) {
					System.out.println("--- Contrib: " + contrib.getName());
					fr1.append(contrib.getName() + " ");
				}
				fr1.append("\n");

			}

			fr1.flush();
			fr1.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void leggiDati(String fileName) {
		try {

			System.out.println("\n\nCarico i dati del file:" + fileName);
			URL res = getClass().getClassLoader().getResource(fileName + ".txt");
			File file = new File(res.getFile());
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line1 = br.readLine();
			StringTokenizer stoken = new StringTokenizer(line1);
			totContribs = Integer.parseInt(stoken.nextToken());
			totProj = Integer.parseInt(stoken.nextToken());

			for (int i = 0; i < totContribs; i++) {

				String lineTmp = br.readLine();
				StringTokenizer stoken2 = new StringTokenizer(lineTmp);
				String nameContrib = stoken2.nextToken();
				int totSkillsContrib = Integer.parseInt(stoken2.nextToken());
				Contributor contrib = new Contributor();
				contrib.setName(nameContrib);
				List<Competenza> elencoCompetenze = new ArrayList<Competenza>();

				for (int j = 0; j < totSkillsContrib; j++) {
					String lineTm = br.readLine();
					StringTokenizer stoken3 = new StringTokenizer(lineTm);
					String nameSkill = stoken3.nextToken();
					int levSkill = Integer.parseInt(stoken3.nextToken());
					Competenza comp = new Competenza();
					comp.setName(nameSkill);
					comp.setLevel(levSkill);
					elencoCompetenze.add(comp);

				}
				contrib.setElencoCompetenze(elencoCompetenze);
				contribs.add(contrib);

			}

			for (int i = 0; i < totProj; i++) {
				String lineTmp = br.readLine();
				StringTokenizer stoken2 = new StringTokenizer(lineTmp);
				String nameProj = stoken2.nextToken();
				int giornate = Integer.parseInt(stoken2.nextToken());
				int score = Integer.parseInt(stoken2.nextToken());
				int bestBefore = Integer.parseInt(stoken2.nextToken());
				int totRuoli = Integer.parseInt(stoken2.nextToken());
				List<Competenza> listaCompetenze = new ArrayList<Competenza>();

				Progetto proj = new Progetto();
				proj.setName(nameProj);
				proj.setGiornate(giornate);
				proj.setScore(score);
				proj.setBestBefore(bestBefore);
				proj.setTotRuoli(totRuoli);
				proj.setListaCompetenze(listaCompetenze);

				for (int j = 0; j < totRuoli; j++) {
					String lineTmp1 = br.readLine();
					StringTokenizer stoken3 = new StringTokenizer(lineTmp1);
					String nameSkill = stoken3.nextToken();
					int levSkill = Integer.parseInt(stoken3.nextToken());
					Competenza comp = new Competenza();
					comp.setName(nameSkill);
					comp.setLevel(levSkill);
					listaCompetenze.add(comp);
				}
				proj.setListaCompetenze(listaCompetenze);

				// System.out.println(proj.getName());
				// System.out.println(proj.getListaCompetenze().size());
				projs.add(proj);

			}

			/*
			 * Iterator it = mappaStrade.entrySet().iterator(); while (it.hasNext()) {
			 * Map.Entry pair = (Map.Entry) it.next(); Strada strada = (Strada)
			 * pair.getValue(); if (listaIntersezioni.containsKey(strada.getStop())) {
			 * Intersezione inters =
			 * listaIntersezioni.get(Integer.valueOf(strada.getStop()));
			 * inters.addStrada(strada.getNome()); } else { Intersezione inters = new
			 * Intersezione(); inters.addStrada(strada.getNome());
			 * listaIntersezioni.put(Integer.valueOf(strada.getStop()), inters); } }
			 * 
			 * br.close();
			 */
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("ERRORE");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("ERRORE");
		}
	}

}
