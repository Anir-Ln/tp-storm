package stormTP.core;


import org.apache.storm.shade.org.json.simple.JSONObject;

public class Runner {
	
	long id = -1;
	long top = -1;
	int cellule = -1;
	int nbDevant = -1;
	int tour = -1;
	
	String nom = "";
	
	
	public Runner(){
	}
	
	public Runner(long id, String name, int nbDevant, int tour, int cellule, long top){
		this.id = id;
		this.nom = name;
		this.nbDevant = nbDevant;
		this.tour = tour;
		this.cellule = cellule;
		this.top = top;
	}


	public String getJSON_V1(){
		JSONObject obj = new JSONObject();
		/* construction de l'objet JSON résultat */
		obj.put("id", this.id);
		obj.put("top", this.top);
		obj.put("nom", this.nom);
		obj.put("cellule", this.cellule);
        obj.put("nbDevant", this.nbDevant);
        obj.put("tour", this.tour);

		return obj.toJSONString();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTop() {
		return top;
	}

	public void setTop(long top) {
		this.top = top;
	}

	public int getCellule() {
		return cellule;
	}

	public void setCellule(int cellule) {
		this.cellule = cellule;
	}

	public int getNbDevant() {
		return nbDevant;
	}

	public void setNbDevant(int nbDevant) {
		this.nbDevant = nbDevant;
	}

	public int getTour() {
		return tour;
	}

	public void setTour(int tour) {
		this.tour = tour;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
