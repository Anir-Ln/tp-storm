package stormTP.core;


import org.apache.storm.shade.org.json.simple.JSONObject;

public class Runner {
	private int id = -1;
	private int top = -1;
	private int cellule = -1;
	private int nbDevant = -1;
	private int tour = -1;
	private String rang = "";
	private int score = 0;

	String nom = "";


	public Runner(){
	}

	public Runner(int id, String name, int nbDevant, int tour, int cellule, int top){
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
		if (!"".equals(rang)) obj.put("rang", this.rang);

		return obj.toJSONString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
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

	public String getRang() {
		return rang;
	}

	public void setRang(String rang) {
		this.rang = rang;
	}

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
