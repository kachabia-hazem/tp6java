public class Film {
    private String titre;
    private String realisateur;
    private String pays;
    private int duree;
    protected int nb;

 
    public int getNombrePlace() {
        return this.nb;
    }

  
    public void setNombrePlace(int n) {
        this.nb = n;
    }

  

    public String toString() {
        return "Le titre du film est " + this.titre + ", le réalisateur est " + this.realisateur + 
               ", le pays d'origine est " + this.pays + ", la durée du film est " + this.duree + 
               ", le nombre de places disponibles est " + this.nb;
    }

    public Film(String titre, String realisateur, String pays, int duree) {
        this.titre = titre;
        this.realisateur = realisateur;
        this.pays = pays;
        this.duree = duree;
    }

    // Méthode pour calculer le total des ventes de billets
    public float totalVenteBillets(int nb_etu) {
        int nb_public = this.nb - nb_etu;
        return (nb_etu * 2) + (nb_public * 3);
    }
}

class Documentaire extends Film {
    private String sujet;
    private float tarif;


    public Documentaire(String titre, String realisateur, String pays, String sujet, int duree, float tarif) {
        super(titre, realisateur, pays, duree);
        this.sujet = sujet;
        this.tarif = tarif;
    }

   
    public String toString() {
        return super.toString() + ", le sujet du documentaire est " + this.sujet + 
               " avec un tarif de " + this.tarif;
    }

   
    public float totalVenteBillets() {
        return super.nb * this.tarif;
    }
}

class Jcc {
    private Film[] competition;
    private int annee;
    private int nbmax;
    private int nbf = 0;  // Initialiser nbf à 0

    // Correction du constructeur de Jcc (syntaxe et types)
    public Jcc(int nbmax, int annee) {
        this.competition = new Film[nbmax];
        this.annee = annee;
        this.nbmax = nbmax;
    }

  
    public void ajoutFilm(Film f) {
        if (nbf < nbmax) {
            competition[nbf] = f;
            nbf++;
        } else {
            System.out.println("Le tableau est complet");
        }
    }

  
    public void listeFilm() {
        for (int i = 0; i < nbf; i++) {  
            System.out.println(competition[i] + "|");
        }
    }

    
    public float totalVenteBilletsJcc() {
        float total = 0;  

        for (int i = 0; i < nbf; i++) 
            if (competition[i] instanceof Documentaire) {  
                total += ((Documentaire) competition[i]).totalVenteBillets();
            } else {
                total += competition[i].totalVenteBillets(0);  
            }
        
        return total; 
    }

public class Main {
    public static void main(String[] args) {
        Jcc jcc2021 = new Jcc(10, 2021);

        Film film1 = new Documentaire("Le dernier refuge", "Ousman", "Mali", "La guerre civile", 86, 30);
        Film film2 = new Film("Insurrection", "Jilani Saadi", "Tunisie", 105);
        film2.setNombrePlace(45);

        jcc2021.ajoutFilm(film1);
        jcc2021.ajoutFilm(film2);

  
        System.out.println("Films qui concourent dans les JCC de l'année 2021 :");
        jcc2021.listeFilm();

        int nb_etu_film1 = 9;
        int nb_etu_film2 = 17;
        float totalVenteFilm1 = film1.totalVenteBillets(nb_etu_film1);
        float totalVenteFilm2 = film2.totalVenteBillets(nb_etu_film2);
        float totalVenteJcc = totalVenteFilm1 + totalVenteFilm2;


        System.out.println("Montant des ventes de billets pour les JCC : " + totalVenteJcc + " DT");
    }
}
}


